package com.example.productmanager.controller;

import com.example.productmanager.model.Category;
import com.example.productmanager.model.Product;
import com.example.productmanager.service.CategoryServiceMySql;
import com.example.productmanager.service.ProductServiceMySql;
import com.example.productmanager.utils.ValidateUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ProductServlet", value = {"/product","/"})
public class ProductServlet extends HttpServlet {
    private ProductServiceMySql productService = new ProductServiceMySql();
    private CategoryServiceMySql categoryServiceMySql = new CategoryServiceMySql();

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreate(request, response);
                break;
            case "edit":
                showEdit(request, response);
                break;
            case "delete":
                showDelete(request, response);
                break;
            case "view":
                showView(request, response);
                break;
            default:
                showList(request, response);
                break;
        }
    }

    private void showView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idView = Integer.parseInt(request.getParameter("id"));
        Product pView = productService.findById(idView);
        Map<Long, Category> categoryMap = categoryServiceMySql.getMapCategories();

        request.setAttribute("categoryMap",categoryMap);
        request.setAttribute("product",pView);
        request.getRequestDispatcher("/Customer/product-view.jsp").forward(request, response);
    }

    private void showDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idDelete = Integer.parseInt(request.getParameter("id"));
        Product pDelete = productService.findById(idDelete);
        Map<Long, Category> categoryMap = categoryServiceMySql.getMapCategories();

        request.setAttribute("categoryMap",categoryMap);
        request.setAttribute("product",pDelete);
        request.getRequestDispatcher("/Customer/product-delete.jsp").forward(request, response);
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idEdit = Integer.parseInt(request.getParameter("id"));
        Product pEdit = productService.findById(idEdit);
        Map<Long, Category> categoryMap = categoryServiceMySql.getMapCategories();

        request.setAttribute("categoryMap",categoryMap);
        request.setAttribute("product",pEdit);
        request.getRequestDispatcher("/Customer/product-edit.jsp").forward(request, response);
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Long, Category> categoryMap = categoryServiceMySql.getMapCategories();

        request.setAttribute("categoryMap",categoryMap);
        request.getRequestDispatcher("/Customer/product-create.jsp").forward(request, response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = productService.findAll();
        Map<Long, Category> categoryMap = categoryServiceMySql.getMapCategories();

        request.setAttribute("categoryMap",categoryMap);
        request.setAttribute("products", productList);
        request.getRequestDispatcher("/Customer/product-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                doCreate(req, resp);
                break;
            case "edit":
                doEdit(req, resp);
                break;
            case "delete":
                doDelete(req, resp);
                break;
            case "search":
                doSearch(req, resp);
                break;
            default:
                break;
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idDelete = Integer.parseInt(req.getParameter("id"));

        productService.remove(idDelete);
        resp.sendRedirect("/product");
    }

    private void doSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keySearch = req.getParameter("search-key");
        List<Product> productListByName = productService.findAllByName(keySearch);
        Map<Long, Category> categoryMap = categoryServiceMySql.getMapCategories();

        req.setAttribute("categoryMap",categoryMap);
        req.setAttribute("products", productListByName);
        req.getRequestDispatcher("/Customer/product-list.jsp").forward(req, resp);
    }

    private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int idEdit = Integer.parseInt(req.getParameter("id"));
        Map<String,String> errorsMap = new HashMap<>();

        String name = getValidatedName(req, errorsMap);
        String description = getValidatedDescript(req, errorsMap);
        float price = getValidatedPrice(req, errorsMap);
        long idCategory = getValidatedIdCate(req, errorsMap);

        Product productEdit = productService.findById(idEdit);
        System.out.println(productEdit);
        System.out.println(idEdit);

        productEdit.setName(name);
        productEdit.setDescription(description);
        productEdit.setPrice(price);
        productEdit.setIdCategory(idCategory);

        if (errorsMap.isEmpty()){
            productService.update(idEdit,productEdit);
            resp.sendRedirect("/product");
        }else {
            Map<Long, Category> categoryMap = categoryServiceMySql.getMapCategories();

            req.setAttribute("categoryMap",categoryMap);
            req.setAttribute("product",productEdit);
            req.setAttribute("errorsMap",errorsMap);

            req.getRequestDispatcher("/Customer/product-edit.jsp").forward(req,resp);
        }

    }

    private void doCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Map<String,String> errorsMap = new HashMap<>();

        String name = getValidatedName(req, errorsMap);
        String description = getValidatedDescript(req, errorsMap);
        float price = getValidatedPrice(req, errorsMap);
        long idCategory = getValidatedIdCate(req, errorsMap);

        Product newP = new Product(name,description,price,idCategory);

        if (errorsMap.isEmpty()){
            productService.save(newP);
            resp.sendRedirect("/product");
        }else {
            Map<Long, Category> categoryMap = categoryServiceMySql.getMapCategories();

            req.setAttribute("categoryMap",categoryMap);
            req.setAttribute("product",newP);
            req.setAttribute("errorsMap",errorsMap);

            req.getRequestDispatcher("/Customer/product-create.jsp").forward(req,resp);
        }
    }

    private long getValidatedIdCate(HttpServletRequest req, Map<String, String> errorsMap) {
        try {
            long idCategory = Long.parseLong(req.getParameter("idCategory"));
            Category category = categoryServiceMySql.findCategoryById(idCategory);
            if (category == null){
                errorsMap.put("idCategoryInvalid","ID category không hợp lệ. Category không tồn tại");
                return 1;
            }else {
                return idCategory;
            }
        }catch (NumberFormatException numberFormatException){
            errorsMap.put("idCategoryInvalid","ID category không hợp lệ. ID category phải là một số");
            return 1;
        }
    }

    private static float getValidatedPrice(HttpServletRequest req, Map<String, String> errorsMap) {
        try {
            float price = Float.parseFloat((req.getParameter("price")));
            if (price < 1000 || price >= 100000000){
                errorsMap.put("priceInvalid","Giá không hợp lệ. Giá sản phẩm phải từ 1000 đồng trở lên, thấp hơn 100.000.000 đồng");
            }
            return price;
        }catch (NumberFormatException numberFormatException){
            errorsMap.put("priceInvalid","Giá không hợp lệ. Giá sản phẩm phải là một sô");
            return 0;
        }
    }

    private static String getValidatedDescript(HttpServletRequest req, Map<String, String> errorsMap) {
        String description = req.getParameter("description");
        if (!ValidateUtils.isDescriptionValid(description)){
            errorsMap.put("descriptionInvalid","Mô tả sản phẩm không hợp lệ, phải có ít nhất 9 ký tự và phải bắt đầu bằng 1 chữ cái");
        }
        return description;
    }

    private static String getValidatedName(HttpServletRequest req, Map<String, String> errorsMap) {
        String name = req.getParameter("name");
        if (!ValidateUtils.isProductNameValid(name)){
            errorsMap.put("productNameInvalid","Tên sản phẩm không hợp lệ. Tên phải có ít nhất 7 ký tự và tối đa 25 ký tự");
        }
        return name;
    }

    public void destroy() {
    }
}
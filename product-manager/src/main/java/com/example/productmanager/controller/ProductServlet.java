package com.example.productmanager.controller;

import com.example.productmanager.model.Category;
import com.example.productmanager.model.Product;
import com.example.productmanager.service.CategoryServiceMySql;
import com.example.productmanager.service.ProductServiceMySql;

import java.io.*;
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

    private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idEdit = Integer.parseInt(req.getParameter("id"));

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        float price = Float.parseFloat((req.getParameter("price")));
        long idCategory = Long.parseLong(req.getParameter("idCategory"));

        Product productEdit = productService.findById(idEdit);
        System.out.println(productEdit);
        System.out.println(idEdit);

        productEdit.setName(name);
        productEdit.setDescription(description);
        productEdit.setPrice(price);
        productEdit.setIdCategory(idCategory);

        productService.update(idEdit,productEdit);
        resp.sendRedirect("/product");
    }

    private void doCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        float price = Float.parseFloat((req.getParameter("price")));
        long idCategory = Long.parseLong(req.getParameter("idCategory"));

        Product newP = new Product(name,description,price,idCategory);

        productService.save(newP);
        resp.sendRedirect("/product");
    }

    public void destroy() {
    }
}
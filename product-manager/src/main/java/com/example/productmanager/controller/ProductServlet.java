package com.example.productmanager.controller;

import com.example.productmanager.model.Product;
import com.example.productmanager.service.IProductService;
import com.example.productmanager.service.ProductService;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {
    private IProductService productService = new ProductService();

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                request.getRequestDispatcher("/product-create.jsp").forward(request,response);
                break;
            case "edit":
                int idEdit = Integer.parseInt(request.getParameter("id"));
                Product pEdit = productService.findById(idEdit);

                request.setAttribute("product",pEdit);
                request.getRequestDispatcher("/product-edit.jsp").forward(request,response);
                break;
            case "delete":
                int idDelete = Integer.parseInt(request.getParameter("id"));
                Product pDelete = productService.findById(idDelete);

                request.setAttribute("product",pDelete);
                request.getRequestDispatcher("/product-delete.jsp").forward(request,response);
                break;
            case "view":
                int idView = Integer.parseInt(request.getParameter("id"));
                Product pView = productService.findById(idView);

                request.setAttribute("product",pView);
                request.getRequestDispatcher("/product-view.jsp").forward(request,response);
                break;
            default:
                List<Product> productList = productService.findAll();

                request.setAttribute("products", productList);
                request.getRequestDispatcher("/product-list.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                List<Product> productList = productService.findAll();
                int maxid = productList.get(0).getId();
                for (Product c : productList
                ) {
                    if (maxid < c.getId()) {
                        maxid = c.getId();
                    }
                }
                int id = maxid + 1;
                String name = req.getParameter("name");
                double price = Double.parseDouble(req.getParameter("price"));
                String description = req.getParameter("description");
                String provider = req.getParameter("provider");

                Product newP = new Product(id,name,price,description,provider);

                productService.save(newP);
                resp.sendRedirect("/product");
                break;
            case "edit":
                int idEdit = Integer.parseInt(req.getParameter("id"));

                String nameEdit = req.getParameter("name");
                double priceEdit = Double.parseDouble(req.getParameter("price"));
                String descriptionEdit = req.getParameter("description");
                String providerEdit = req.getParameter("provider");

                Product productEdit = productService.findById(idEdit);
                productEdit.setName(nameEdit);
                productEdit.setPrice(priceEdit);
                productEdit.setDescription(descriptionEdit);
                productEdit.setProviderName(providerEdit);

                productService.update(idEdit,productEdit);
                resp.sendRedirect("/product");
                break;
            case "delete":
                int idDelete = Integer.parseInt(req.getParameter("id"));
                Product pDelete = productService.findById(idDelete);

                productService.remove(idDelete);
                resp.sendRedirect("/product");
            case "search":
                String keySearch = req.getParameter("search-key");
                List<Product> productListByName = productService.findAllByName(keySearch);

                req.setAttribute("products", productListByName);
                req.getRequestDispatcher("/product-list.jsp").forward(req, resp);
                break;
            default:
                break;
        }
    }

    public void destroy() {
    }
}
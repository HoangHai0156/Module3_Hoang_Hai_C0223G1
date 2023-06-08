package com.example.customermanager.controller;

import com.example.customermanager.model.Customer;
import com.example.customermanager.service.CustomerService;
import com.example.customermanager.service.CustomerServiceMySql;
import com.example.customermanager.service.ICustomerService;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "CustomerServlet", value ={"/customer","/"})
public class CustomerServlet extends HttpServlet {
    private ICustomerService customerService = new CustomerServiceMySql();

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                request.getRequestDispatcher("/create.jsp").forward(request, response);
                break;
            case "edit":
                int idEdit = Integer.parseInt(request.getParameter("id"));
                Customer cEdit = customerService.findById(idEdit);
                request.setAttribute("customer",cEdit);
                request.getRequestDispatcher("/edit.jsp").forward(request,response);
                break;
            case "delete":
                int idDelete = Integer.parseInt(request.getParameter("id"));
                Customer cDelete = customerService.findById(idDelete);
                request.setAttribute("customer",cDelete);
                request.getRequestDispatcher("/delete.jsp").forward(request,response);
                break;
            case "sort":
                List<Customer> customerListSort = customerService.sortByName();

                request.setAttribute("customers", customerListSort);
                request.getRequestDispatcher("/list.jsp").forward(request, response);
                break;
            default:
                List<Customer> customerList = customerService.findAll();

                request.setAttribute("customers", customerList);
                request.getRequestDispatcher("/list.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                List<Customer> customerList = customerService.findAll();
                int maxid = customerList.get(0).getId();
                for (Customer c : customerList
                ) {
                    if (maxid < c.getId()) {
                        maxid = c.getId();
                    }
                }
                int id = maxid + 1;
                String name = req.getParameter("name");
                String email = req.getParameter("email");
                String address = req.getParameter("address");

                Customer newC = new Customer(id,name,email,address);

                customerService.save(newC);
                resp.sendRedirect("/customer");
                break;
            case "edit":
                int idEdit = Integer.parseInt(req.getParameter("id"));
                String nameEdit = req.getParameter("name");
                String emailEdit = req.getParameter("email");
                String addressEdit = req.getParameter("address");
                Customer cEdit = customerService.findById(idEdit);
                cEdit.setName(nameEdit);
                cEdit.setAddress(addressEdit);
                cEdit.setEmail(emailEdit);
                customerService.update(idEdit,cEdit);
                resp.sendRedirect("/customer");
                break;
            case "delete":
                int idDelete = Integer.parseInt(req.getParameter("id"));
                customerService.remove(idDelete);
                resp.sendRedirect("/customer");
                break;
            case "search":
                String key = req.getParameter("key");
                System.out.println(key);
                List<Customer> customerListSearch = customerService.findAll(key);
                System.out.println(customerListSearch);

                req.setAttribute("customers", customerListSearch);
                req.getRequestDispatcher("/list.jsp").forward(req, resp);
                break;
            default:
                break;
        }
    }

    public void destroy() {
    }
}
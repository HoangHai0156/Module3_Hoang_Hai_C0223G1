package com.codegym.productdiscountcalculator;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ProductDiscountCalculator", value = "/product-discount-calculator")
public class ProductDiscountCalculator extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/product-discount-calculator.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double price = Double.parseDouble(req.getParameter("listprice"));
        double discountPercent = Double.parseDouble(req.getParameter("discountpercent"));
        String product = req.getParameter("description");

        double discount = discountPercent*price;
        double result = price - discount;

        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<h1>Product: "+product+"</h1>");
        writer.println("<h1>Price: "+price+"</h1>");
        writer.println("<h1>Discount Percent: "+discountPercent+"</h1>");
        writer.println("<h1>Discount price: "+discount+"</h1>");
        writer.println("<h1>Discounted Price: "+result+"</h1>");
        writer.println("</html>");
    }

    public void destroy() {
    }
}
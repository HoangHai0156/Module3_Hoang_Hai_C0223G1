package com.codegym.javawebcurrencyconverter;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ConverterServlet", value = "/java-web-currency-converter")
public class ConverterServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/java-web-currency-converter.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        float rate = Float.parseFloat(req.getParameter("rate"));
        float usd = Float.parseFloat(req.getParameter("usd"));

        float vnd = usd*rate;

        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<h1> Rate: "+rate+"</h1>");
        writer.println("<h1> USD: "+usd+"</h1>");
        writer.println("<h1> VND: "+vnd+"</h1>");
        writer.println("</html>");
    }

    public void destroy() {
    }
}
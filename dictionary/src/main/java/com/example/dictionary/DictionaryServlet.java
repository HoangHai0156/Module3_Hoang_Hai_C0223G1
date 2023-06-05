package com.example.dictionary;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "DictionaryServlet", value = "/dictionary")
public class DictionaryServlet extends HttpServlet {
    private String message;
    private Map<String,String> dictionary;

    public void init() {
        message = "Hello World!";
        dictionary = new HashMap<>();
        dictionary.put("hello", "Xin chào");
        dictionary.put("how", "Thế nào");
        dictionary.put("book", "Quyển vở");
        dictionary.put("computer", "Máy tính");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/dictionary.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("txtSearch");

        String meaning = dictionary.get(search);
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<h1>Search: "+search+"</h1>");
        writer.println("<h1>Meaning: "+meaning+"</h1>");
        writer.println("</html>");
    }

    public void destroy() {
    }
}
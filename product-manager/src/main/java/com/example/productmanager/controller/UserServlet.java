package com.example.productmanager.controller;

import com.example.productmanager.model.ERole;
import com.example.productmanager.model.User;
import com.example.productmanager.service.UserServiceMySql;
import com.example.productmanager.utils.ValidateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {
    private UserServiceMySql userServiceMySql = new UserServiceMySql();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreate(req, resp);
                break;
            case "delete":
                showDelete(req, resp);
                break;
            case "edit":
                showEdit(req, resp);
                break;
            case "view":
                showView(req, "/user/user-view.jsp", resp);
                break;
            default:
                showListUser(req, resp);
                break;
        }
    }

    private void showView(HttpServletRequest req, String s, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        User user = userServiceMySql.findById(id);
        ERole[] roles = ERole.values();

        req.setAttribute("user", user);
        req.setAttribute("roles", roles);
        req.getRequestDispatcher(s).forward(req, resp);
    }

    private void showDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showView(req, "/user/user-delete.jsp", resp);
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showView(req, "/user/user-edit.jsp", resp);
    }

    private void showListUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userServiceMySql.findAll();

        req.setAttribute("users",users);
        req.getRequestDispatcher("/user/user-list.jsp").forward(req, resp);
    }

    private static void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ERole[] roles = ERole.values();

        req.setAttribute("roles",roles);
        req.getRequestDispatcher("/user/user-create.jsp").forward(req, resp);
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
            case "delete":
                doRemove(req, resp);
                break;
            case "edit":
                doEdit(req, resp);
                break;
            default:
                break;
        }
    }

    private void doRemove(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(req.getParameter("id"));

        System.out.println(id);

        userServiceMySql.remove(id);
        resp.sendRedirect("/user");
    }

    private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Map<String,String> errorsMap = new HashMap<>();
        long id = Long.parseLong(req.getParameter("id"));
        User user = userServiceMySql.findById(id);

        String fullName = getValidatedFullName(req, errorsMap);

        String address = getValidatedAddress(req, errorsMap);

        LocalDate dobL = getValidatedLocalDOB(req, errorsMap);

        ERole eRole = getValidatedRole(req, errorsMap);

        user.setFullName(fullName);
        user.setAddress(address);
        user.setDob(dobL);
        user.setRole(eRole);

        if (errorsMap.isEmpty()){
            userServiceMySql.update(id,user);
            resp.sendRedirect("/user");
        }else {
            ERole[] roles = ERole.values();

            req.setAttribute("roles",roles);
            req.setAttribute("user",user);
            req.setAttribute("errorsMap",errorsMap);

            req.getRequestDispatcher("/user/user-edit.jsp").forward(req, resp);
        }
    }

    private void doCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Map<String,String> errorsMap = new HashMap<>();

        String fullName = getValidatedFullName(req, errorsMap);

        String address = getValidatedAddress(req, errorsMap);

        LocalDate dobL = getValidatedLocalDOB(req, errorsMap);

        ERole eRole = getValidatedRole(req, errorsMap);

        User user = new User(fullName,address,dobL,eRole);

        if (errorsMap.isEmpty()){
            userServiceMySql.save(user);
            resp.sendRedirect("/user");
        }else {
            ERole[] roles = ERole.values();

            req.setAttribute("roles",roles);
            req.setAttribute("user",user);
            req.setAttribute("errorsMap",errorsMap);

            req.getRequestDispatcher("/user/user-create.jsp").forward(req, resp);
        }
    }

    private static ERole getValidatedRole(HttpServletRequest req, Map<String, String> errorsMap) {
        String role = req.getParameter("role");
        ERole eRole = null;
        if (!ERole.isRoleExist(role)){
            errorsMap.put("eRoleInvalid","Role không tồn tại");
        }else {
            eRole = ERole.valueOf(role);
        }
        return eRole;
    }

    private static LocalDate getValidatedLocalDOB(HttpServletRequest req, Map<String, String> errorsMap) {
        String dob = req.getParameter("dob");
        LocalDate dobL = null;
        if (!ValidateUtils.isDOBValid(dob)){
            errorsMap.put("dobInvalid","Ngày không hợp lệ. Ngày phải theo định dạng YYYY-MM-DD");
        }else {
            dobL = LocalDate.parse(dob);
        }
        return dobL;
    }

    private static String getValidatedAddress(HttpServletRequest req, Map<String, String> errorsMap) {
        String address = req.getParameter("address");
        if (!ValidateUtils.isAddressValid(address)){
            errorsMap.put("addressInvalid","Địa chỉ không hợp lệ. Địa chỉ ít nhất 3 ký tự và tối đa 245 ký tự");
        }
        return address;
    }

    private static String getValidatedFullName(HttpServletRequest req, Map<String, String> errorsMap) {
        String fullName = req.getParameter("fullName");
        if (!ValidateUtils.isUserNameValid(fullName)){
            errorsMap.put("userNameInvalid","Tên user không hợp lệ. Tên chỉ được phép chứa cữ cái, ít nhất 10 ký tự, tối đa 245 ký tự");
        }
        return fullName;
    }
}

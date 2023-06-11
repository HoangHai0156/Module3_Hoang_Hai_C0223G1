package com.example.productmanager.service;

import com.example.productmanager.model.ERole;
import com.example.productmanager.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserServiceMySql extends DBContext{
    private static final String SELECT_ALL = "SELECT * FROM users";
    private static final String SELECT_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String INSERT_USER = "INSERT INTO `users` (`fullName`, `address`, `dob`, `role`) VALUES (?,?,?,?)";
    private static final String UPDATE = "UPDATE `users` SET `fullName` = ?, `address` = ?, `dob` = ?, `role` = ? WHERE (`id` = ?)";
    private static final String DELETE_USER = "UPDATE `users` SET `deleteAt` = ? WHERE (`id` = ?)";
    public List<User> findAll(){
        List<User> users = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                User user = getUserFromRs(rs);
                users.add(user);
            }

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return users;
    }

    private static User getUserFromRs(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String fullName = rs.getString("fullName");
        String address = rs.getString("address");
        LocalDate dob = rs.getDate("dob").toLocalDate();
        Date deleteAtSql = rs.getDate("deleteAt");
        String role = rs.getString("role");
        LocalDate deleteAt = null;
        if (deleteAtSql != null){
            deleteAt = deleteAtSql.toLocalDate();
        }

        return new User(id,fullName,address,dob,deleteAt, ERole.valueOf(role));
    }
    public void save(User user){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {

            preparedStatement.setString(1,user.getFullName());
            preparedStatement.setString(2,user.getAddress());
            preparedStatement.setDate(3, Date.valueOf(user.getDob()));
            preparedStatement.setString(4,user.getRole().name());

            preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }
    public User findById(long id){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {

            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                return getUserFromRs(rs);
            }

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return null;
    }
    public void update(long id, User user){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setString(1,user.getFullName());
            preparedStatement.setString(2,user.getAddress());
            preparedStatement.setDate(3, Date.valueOf(user.getDob()));
            preparedStatement.setString(4,user.getRole().name());
            preparedStatement.setLong(5,id);

            preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }
    public void remove(long id){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {

            java.util.Date deleteAt = new java.util.Date();
            preparedStatement.setDate(1, new Date(deleteAt.getTime()));
            preparedStatement.setLong(2,id);

            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }
}

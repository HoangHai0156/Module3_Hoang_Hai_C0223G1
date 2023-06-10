package com.example.productmanager.service;

import com.example.productmanager.model.Category;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryServiceMySql extends DBContext{
    private static final String SELECT_ALL = "select * from categories";
    private static final String SELECT_BY_ID = "select * from categories where id = ?";
    public List<Category> findAll(){
        List<Category> categories = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            ResultSet rs = preparedStatement.executeQuery();
            categories = getCategoriesFromRs(rs,categories);

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return categories;
    }
    public Map<Long,Category> getMapCategories(){
        Map<Long,Category> categoryMap = new HashMap<>();
        for (Category c: findAll()
             ) {
            categoryMap.put(c.getId(),c);
        }
        return categoryMap;
    }

    private List<Category> getCategoriesFromRs(ResultSet rs, List<Category> categories) throws SQLException {
        while (rs.next()){
            Category category = getCategorieFromRs(rs);
            categories.add(category);
        }
        return categories;
    }

    private Category getCategorieFromRs(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        Date deleteAtSql = rs.getDate("deleteAt");
        LocalDate deleteAt = null;
        if (deleteAtSql != null){
            deleteAt = deleteAtSql.toLocalDate();
        }

        return new Category(id,name,deleteAt);
    }
    public Category findCategoryById(long id){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {

            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                return getCategorieFromRs(rs);
            }
        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return null;
    }
}

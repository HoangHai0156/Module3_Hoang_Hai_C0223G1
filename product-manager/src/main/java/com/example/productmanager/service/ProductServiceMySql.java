package com.example.productmanager.service;

import com.example.productmanager.model.Product;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceMySql extends DBContext{
    private static final String SELECT_ALL = "select * from products";
    private static final String SELECT_BY_ID = "select * from products where (id = ?)";
    private static final String SELECT_BY_NAME = "select * from products where name like ?";
    private static final String INSERT_PRODUCT = "INSERT INTO `products` (`name`, `description`, `createAt`, `price`, `idCategory`) VALUES (?,?,?,?,?)";
    private static final String UPDATE_PRODUCT = "UPDATE `products` SET `name` = ?, `description` = ?, `price` = ?, `idCategory` = ? WHERE (`id` = ?)";
    private static final String DELETE_PRODUCT = "UPDATE `products` SET `deleteAt` = ? WHERE (`id` = ?)";
    private static final String SORT_BY_NAME_ASC = "select * from products order by name";
    public List<Product> findAll() {
        return getSelect(SELECT_ALL);
    }

    private List<Product> getSelect(String selectSql) {
        List<Product> products = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            getProductsFromRs(resultSet, products);

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return products;
    }

    private List<Product> getProductsFromRs(ResultSet resultSet, List<Product> products) throws SQLException {
        while (resultSet.next()){
            Product product = getProductFromRs(resultSet);
            products.add(product);
        }
        return products;
    }

    private Product getProductFromRs(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        LocalDate createAt = resultSet.getDate("createAt").toLocalDate();
        Date deleteAtSql = resultSet.getDate("deleteAt");
        LocalDate deleteAt = null;
        float price = resultSet.getFloat("price");
        long idCategoty = resultSet.getLong("idCategory");

        if (deleteAtSql != null){
            deleteAt = deleteAtSql.toLocalDate();
        }

        return new Product(id,name,description,createAt,deleteAt,price,idCategoty);
    }

    public List<Product> findAllByName(String name) {
        List<Product> products = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME)) {

            preparedStatement.setString(1,"%"+name+"%");
            ResultSet resultSet = preparedStatement.executeQuery();

            products = getProductsFromRs(resultSet,products);

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return products;
    }

    public void save(Product product) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT)) {

            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2,product.getDescription());
            java.util.Date createAt = new java.util.Date();
            preparedStatement.setDate(3, new Date(createAt.getTime()));
            preparedStatement.setFloat(4,product.getPrice());
            preparedStatement.setLong(5,product.getIdCategory());

            preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }

    public Product findById(long id) {

        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {

            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                return getProductFromRs(resultSet);
            }

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return null;
    }

    public void update(long id, Product product) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {

            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2,product.getDescription());
            preparedStatement.setFloat(3,product.getPrice());
            preparedStatement.setLong(4,product.getIdCategory());
            preparedStatement.setLong(5,id);

            preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }

    public void remove(long id) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {

            java.util.Date deleteAt = new java.util.Date();
            preparedStatement.setDate(1, new Date(deleteAt.getTime()));
            preparedStatement.setLong(2,id);

            preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }
}

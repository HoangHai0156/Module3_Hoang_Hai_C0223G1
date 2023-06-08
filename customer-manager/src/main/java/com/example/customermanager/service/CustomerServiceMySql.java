package com.example.customermanager.service;

import com.example.customermanager.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceMySql implements ICustomerService{
    private String jdbcURL = "jdbc:mysql://localhost:3306/customer_demo_jdbc?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Raisingthebar123@";
    private static final String SELECT_ALL = "select * from customers";
    private static final String SELECT_BY_ID = "select * from customers where (id = ?)";
    private static final String SELECT_BY_ADDRESS = "select * from customers where address like ?";
    private static final String INSERT_CUSTOMER = "INSERT INTO customers (name, email, address) values (?, ?, ?)";
    private static final String UPDATE_CUSTOMER = "UPDATE customers SET name = ?, email = ?, address = ? WHERE (id = ?)";
    private static final String DELETE_CUSTOMER = "DELETE FROM customers WHERE (id = ?)";
    private static final String SORT_BY_NAME_ASC = "select * from customers order by name";
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    @Override
    public List<Customer> findAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                Customer customer = new Customer(id,name,email,address);
                customers.add(customer);
            }

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return customers;
    }

    public List<Customer> findAll(String address) {
        ArrayList<Customer> customers = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ADDRESS)) {
            preparedStatement.setString(1,"%"+address+"%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String addressFind = resultSet.getString("address");

                Customer customer = new Customer(id,name,email,addressFind);
                customers.add(customer);
            }

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return customers;
    }

    @Override
    public List<Customer> sortByName() {
        ArrayList<Customer> customers = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SORT_BY_NAME_ASC)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                Customer customer = new Customer(id,name,email,address);
                customers.add(customer);
            }

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return customers;
    }

    @Override
    public void save(Customer customer) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER)) {

            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2,customer.getEmail());
            preparedStatement.setString(3,customer.getAddress());

            preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }

    @Override
    public Customer findById(int id) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {

            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                return new Customer(id,name,email,address);
            }
        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
        return null;
    }

    @Override
    public void update(int id, Customer customer) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER)) {

            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2,customer.getEmail());
            preparedStatement.setString(3,customer.getAddress());
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }

    @Override
    public void remove(int id) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER)) {

            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        }catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }
}

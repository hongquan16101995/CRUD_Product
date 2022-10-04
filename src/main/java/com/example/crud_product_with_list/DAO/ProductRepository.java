package com.example.crud_product_with_list.DAO;

import com.example.crud_product_with_list.model.Product;

import java.sql.*;
import java.util.ArrayList;

public class ProductRepository {
    private MyConnection myConnection = new MyConnection();
    private Connection connection;
    private final String INSERT_PRODUCT = "insert into product (name, price, image) value (?,?,?);";
    private final String UPDATE_PRODUCT_BY_ID = "update product set name = ?, price = ? , image = ? where id = ?;";
    private final String UPDATE_PRODUCT_BY_NAME = "update product set price = ? where id = ?;";

    private final String SELECT_ALL_PRODUCT = "select * from product;";
    private final String SELECT_PRODUCT_BY_ID = "select * from product where id = ?;";
    private final String SELECT_PRODUCT_BY_NAME = "select * from product where name like ?;";
    private final String DELETE_PRODUCT_BY_ID = "delete from product where id = ?;";
    private final String CALL_STORED = "{CALL change_price(?,?)};";

    public void addProduct(Product product) {
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getImage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Product> findAll() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String image = resultSet.getString("image");
                Product product = new Product(id, name, price, image);
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    public Product findProductById(int id) {
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
            preparedStatement.setString(1, String.valueOf(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String image = resultSet.getString("image");
                return new Product(id, name, price, image);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteProductById(int id) {
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProductById(Product product) {
        try {
            Connection connection = myConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_BY_ID);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getImage());
            preparedStatement.setInt(4, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changePrice(Long price, int id) {
        try {
            connection = myConnection.getConnection();
            CallableStatement callableStatement = connection.prepareCall(CALL_STORED);
            callableStatement.setLong(1, price);
            callableStatement.setInt(2, id);
            callableStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProductByName(String name) {
        try {
            connection = myConnection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_NAME);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_BY_NAME);
                //"update product set price = ? where id = ?;"
                if (rs.getInt("id") > 2) {
                    preparedStatement.setLong(2, 10000);
                } else {
                    preparedStatement.setLong(1, 10000);
                }
                preparedStatement.setInt(2, rs.getInt("id"));
                preparedStatement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.close();
                } catch (SQLException s) {
                    System.out.println(s.getMessage());
                }
            }
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException s) {
                System.out.println(s.getMessage());
            }
        }
    }
}

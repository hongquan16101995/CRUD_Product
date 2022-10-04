package com.example.crud_product_with_list.controller;

import com.example.crud_product_with_list.model.Product;
import com.example.crud_product_with_list.service.impl.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {

    private final ProductServiceImpl productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "detail":
                break;
            case "delete":
                deleteProductById(request, response);
                break;
            case "edit":
                updateProductById(request, response);
                break;
            case "name":
                updateProductByName(request, response);
                break;
            case "change":
                changePrice(request, response);
                break;
            default:
                displayAllProduct(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                addProduct(request, response);
                break;
            case "edit":
                editProduct(request, response);
                break;
        }
    }

    private void changePrice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long price = Long.parseLong(request.getParameter("price"));
        int id = Integer.parseInt(request.getParameter("id"));
        productService.change(id, price);
        response.sendRedirect("/product?action=");
    }

    private void updateProductByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name_change");
        productService.updateProductsByName(name);
        response.sendRedirect("/product?action=");
    }

    private Product createProduct(HttpServletRequest request) {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String image = request.getParameter("image");
        return new Product(name, price, image);
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Product product = createProduct(request);
        productService.addProduct(product);
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("display.jsp");
//        request.getSession().setAttribute("message", "test message");
//        response.sendRedirect(request.getHeader("Referer"));
        response.sendRedirect("/product?action=");
    }

    private void displayAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Product> products = productService.findAll();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/display.jsp");
        request.setAttribute("products", products);
        requestDispatcher.forward(request, response);
    }

    private void deleteProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.deleteProduct(id);
        response.sendRedirect("/product?action=");
    }

    private void updateProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findProductById(id);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/edit.jsp");
        request.setAttribute("product", product);
        requestDispatcher.forward(request, response);
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String image = request.getParameter("image");
        Product product = productService.findProductById(id);
        product.setName(name);
        product.setPrice(price);
        product.setImage(image);
        productService.updateProduct(product);
        response.sendRedirect("/product?action=");
    }
}

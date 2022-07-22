package com.example.crud_product_with_list.controller;

import com.example.product.servlet_jsp.model.Product;
import com.example.product.servlet_jsp.service.ProductServiceImpl;

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
        switch (action) {
            case "detail":
                break;
            case "delete":
                deleteProductById(request, response);
                break;
            case "edit":
                updateProductById(request, response);
                break;
            default:
                displayAllProduct(request, response);
        }
//        if (action.equals("1")) {
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("product-request.jsp");
//            ProductServiceImpl productService = new ProductServiceImpl();
//            ArrayList<Product> products = productService.findAll();
//            request.setAttribute("products", products);
//            requestDispatcher.forward(request, response);
//        } else {
//            response.sendRedirect("product-response.jsp");
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "create":
                addProduct(request, response);
                break;
            case "edit":
                editProductByForm(request, response);
                break;
        }
    }

    private Product createProduct(HttpServletRequest request) {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String image = request.getParameter("image");
        return new Product(name, price, image);
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = createProduct(request);
        productService.addProduct(product);
        response.sendRedirect("/product?action=");
    }

    private void displayAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Product> products = productService.findAll();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/display.jsp");
        request.setAttribute("products", products);
        requestDispatcher.forward(request, response);
    }

    private void displayProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findProductById(id);
    }

    private void deleteProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findProductById(id);
        productService.deleteProduct(product);
        displayAllProduct(request, response);
    }

    private void updateProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findProductById(id);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/create.jsp");
        request.setAttribute("product", product);
        requestDispatcher.forward(request, response);
    }

    private void editProductByForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        editProduct(request);
        response.sendRedirect("/product?action=");
    }

    private void editProduct(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String image = request.getParameter("image");
        Product product = productService.findProductById(id);
        product.setName(name);
        product.setPrice(price);
        product.setImage(image);
        productService.updateProduct(product);
    }
}

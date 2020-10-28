package ua.lviv.lgs.servlet;

import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.service.ProductService;
import ua.lviv.lgs.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/product")
public class ProductController extends HttpServlet {

    ProductService productService = ProductServiceImpl.getProductService();

//to create product
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");

        Product product  = new Product(name, description, getValidatedPrice(price));
        try {
            productService.create(product );
        } catch (SQLException e) {
            e.printStackTrace();

        }
        response.setContentType("text");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Success");
        // productService.create(new pr)

    }
    private double getValidatedPrice(String price){
        if(price== null|| price.isEmpty()){
            return 0;
        }
        return Double.parseDouble(price);
    }
    //to get product

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
//to  update product
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(request, resp);
    }
//to delete product
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(request, resp);
    }
}

package ua.lviv.lgs.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.service.BucketService;
import ua.lviv.lgs.service.ProductService;
import ua.lviv.lgs.service.UserService;
import ua.lviv.lgs.service.impl.BucketServiceImpl;
import ua.lviv.lgs.service.impl.ProductServiceImpl;
import ua.lviv.lgs.service.impl.UserServiceImpl;


@WebServlet("/bucket")
public class BucketController extends HttpServlet {

    private	BucketService bucketService = BucketServiceImpl.getBucketService();
    private ProductService productService = ProductServiceImpl.getProductService();
    private UserService userService = UserServiceImpl.getUserService();

    public BucketController() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("product_id");

        Product product = null;
        try {
            product = productService.read(Integer.parseInt(productId));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        Integer userId = (Integer)session.getAttribute("user_id");

        User user = null;
        try {
            user = userService.read(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Bucket bucket = new Bucket();
        bucket.setId(UUID.randomUUID().toString());
        bucket.setProduct(product);
        bucket.setUser(user);
        bucket.setPurchaseDate(new Date());

        try {
            bucketService.create(bucket);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        response.setContentType("text");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Success");
    }
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bucketId = request.getParameter("bucketId");
        try {
            bucketService.delete(Integer.parseInt(bucketId));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        response.setContentType("text");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Success");
    }
}
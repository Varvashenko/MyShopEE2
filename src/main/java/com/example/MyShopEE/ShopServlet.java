package com.example.MyShopEE;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//@WebServlet(name = "shopServlet", value = "/shop-servlet")
@WebServlet("/shop-servlet")
public class ShopServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Магазин..";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=UTF-8 />");
        out.println("</head>");

        out.println("<body>");
        out.println("<h1>dfgs dflkfmkd mglkdfm glkdfg kldsfmg kldsm</h1>");
        out.println("<h1>dfgs dflkfmkd mglkdfm glkdfg kldsfmg kldsm</h1>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.doPost(request, response);
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String name = request.getParameter("username");
        String age = request.getParameter("userage");

        try {
            writer.println("<p>Name: " + name + "</p>");
            writer.println("<p>Age: " + age + "</p>");
        } finally {
            writer.close();
        }
    }

    public void destroy() {
    }
}
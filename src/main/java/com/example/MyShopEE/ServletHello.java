package com.example.MyShopEE;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//@WebServlet(name = "ServletHello", value = "/ServletHello")
@WebServlet("")
public class ServletHello extends HttpServlet {
    private int age = 0;
    FruitsLoader fruitsLoader;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        fruitsLoader = new FruitsLoader();
        age = 40;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setAttribute("name", "Tom");
        String fruitName = request.getParameter("fruitname");
        if(fruitName!=null && !fruitName.isEmpty()) {
            if(fruitName.substring(0,1).equals("-")) {
                fruitName = fruitName.substring(1);
                request.setAttribute("fruit", fruitsLoader.deleteFruit(fruitName));
            } else {
                request.setAttribute("fruit", fruitsLoader.saveNewFruit(fruitName));
            }
        } else {
            request.setAttribute("fruit", "< empty >");
        }
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

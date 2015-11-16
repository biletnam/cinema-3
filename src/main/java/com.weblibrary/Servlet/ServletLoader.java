package com.weblibrary.Servlet;

import com.google.gson.Gson;
import com.weblibrary.dao.SeatDAO;
import com.weblibrary.entity.Seat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/load")
public class ServletLoader extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Gson gson = new Gson();
        SeatDAO seatDAO=(SeatDAO)getServletContext().getAttribute("seatDao");
        ArrayList<Seat> seats=(ArrayList<Seat>)seatDAO.findAll();

        try{
            response.setContentType("application/json");
            response.getOutputStream().print(gson.toJson(seats));
            response.getOutputStream().flush();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
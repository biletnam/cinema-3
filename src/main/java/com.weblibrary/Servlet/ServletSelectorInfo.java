package com.weblibrary.Servlet;
import com.google.gson.Gson;
import com.weblibrary.dao.SeatDAO;
import com.weblibrary.entity.Seat;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/select_info")
public class ServletSelectorInfo extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String json=request.getParameter("seatData");
        System.out.println("input data: " + json);
        JSONArray jsonArray = new JSONArray(json);
        Gson gson = new Gson();

        int status=1;
        SeatDAO seatDAO=(SeatDAO)getServletContext().getAttribute("seatDao");
        ArrayList<Seat> seats=new ArrayList<Seat>();
        for(int i=0;i<jsonArray.length();i++){
            JSONObject e = jsonArray.getJSONObject(i);
            String str=e.getString("value");
            int id = Integer.parseInt(str);
            Seat seat=seatDAO.findById(id);
            seats.add(seat);
        }

        try{
            response.setContentType("application/json");
            response.getOutputStream().print(gson.toJson(seats));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
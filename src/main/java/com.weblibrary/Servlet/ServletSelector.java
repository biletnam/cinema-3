package com.weblibrary.Servlet;
import com.google.gson.Gson;
import com.weblibrary.dao.SeatDAO;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/select")
public class ServletSelector extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String json=request.getParameter("seatData");
        System.out.println("input data: " + json);
        JSONArray jsonArray = new JSONArray(json);
        Gson gson = new Gson();

        int status=1;
        SeatDAO seatDAO=(SeatDAO)getServletContext().getAttribute("seatDao");
        for(int i=0;i<jsonArray.length();i++){
            JSONObject e = jsonArray.getJSONObject(i);
            String str=e.getString("value");
            int id = Integer.parseInt(str);

            try {
                seatDAO.update(id,status);
            }catch (NullPointerException ex){
               ex.printStackTrace();
            }
        }

        try{
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/load");
            requestDispatcher.forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

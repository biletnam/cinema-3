package com.weblibrary.Listener;

import com.weblibrary.dao.SeatDAO;
import com.weblibrary.dao.SeatDAOHibernateImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent){
        ServletContext sc = servletContextEvent.getServletContext();
        SeatDAO seatDao=new SeatDAOHibernateImpl();
        sc.setAttribute("seatDao",seatDao);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent){
        System.out.println("contextDestroyed...");
        ServletContext sc = servletContextEvent.getServletContext();
    }
}

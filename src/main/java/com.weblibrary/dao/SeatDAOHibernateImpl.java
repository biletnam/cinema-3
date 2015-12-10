package com.weblibrary.dao;
import com.weblibrary.entity.Seat;
import com.weblibrary.service.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class SeatDAOHibernateImpl implements SeatDAO {

    public List<Seat> findAll(){
        Session session = HibernateUtil.beginTransaction();
        Criteria c1=session.createCriteria(Seat.class);
        ArrayList<Seat> seats= (ArrayList<Seat>) c1.list();
        HibernateUtil.commitTransaction();
        return seats;
    }

    public Seat findById(int id){
        Session session= HibernateUtil.beginTransaction();
        Seat seat = (Seat) session.get(Seat.class, id);
        HibernateUtil.commitTransaction();
        return seat;
    }

    public Seat update(int id,int status) throws NullPointerException{
        Seat seat = findById(id);
        seat.setStatus(status);
        Session session = HibernateUtil.beginTransaction();
        session.saveOrUpdate(seat);
        HibernateUtil.commitTransaction();
        return seat;
    }

    public List<Seat> findReserved(int status){
        Session session = HibernateUtil.beginTransaction();
        Criteria c1=session.createCriteria(Seat.class);
        c1.add(Restrictions.like("status", 1));
        ArrayList<Seat> seats= (ArrayList<Seat>) c1.list();
        HibernateUtil.commitTransaction();
        return seats;
    }
}
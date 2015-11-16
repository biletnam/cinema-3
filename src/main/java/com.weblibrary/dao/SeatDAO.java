package com.weblibrary.dao;

import com.weblibrary.entity.Seat;

import java.util.List;

public interface SeatDAO {
    //public void addSeat(int raw, int place, int price,int status);
    public List<Seat> findAll();
    public List<Seat> findReserved(int status);
    public Seat findById(int id);
    public Seat update(int id, int status);
}
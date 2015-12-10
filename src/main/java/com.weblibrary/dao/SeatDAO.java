package com.weblibrary.dao;

import com.weblibrary.entity.Seat;

import java.util.List;

public interface SeatDAO {
    //public void addSeat(int raw, int place, int price,int status);
    List<Seat> findAll();
    Seat findById(int id);
    Seat update(int id, int status);
    List<Seat> findReserved(int status);
}
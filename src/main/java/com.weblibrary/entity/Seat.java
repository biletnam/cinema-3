package com.weblibrary.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seat")
public class Seat {
    int id;
    int raw;
    int place;
    int price;
    int status;

    public Seat(int raw, int place, int price, int status){
        this.raw = raw;
        this.place = place;
        this.price = price;
        this.status=status;
    }

    public Seat() {
    }

    @Id
    @GeneratedValue
    public int getId(){return id;}
    public void setId(int id){this.id=id;}

    public int getRaw(){return raw;}
    public void setRaw(int raw){this.raw=raw; }

    public int getPlace(){return place;}
    public void setPlace(int place){this.place=place; }

    public int getPrice(){return price;}
    public void setPrice(int price){this.price=price; }

    public int getStatus(){return status;}
    public void setStatus(int status){this.status=status; }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", raw='" + raw + '\'' + ", place='" + place + '\'' + ", price='" + price + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seat seat = (Seat) o;

        if (id != seat.id) return false;
        if (raw != seat.raw) return false;
        if (place != seat.place) return false;
        if (price != seat.price) return false;
        return status == seat.status;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + raw;
        result = 31 * result + place;
        result = 31 * result + price;
        result = 31 * result + status;
        return result;
    }
}


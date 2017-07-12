/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savonlinja;

import java.util.Comparator;

/**
 * Creates Ticket-objects and allows comparison operations.
 * @author markokos
 */
public class Ticket implements Comparable, Comparator<Ticket> {

    private float price;
    private int day, month, year;
    private int id;

    public int getId() {
        return id;
    }

    public Ticket(float price, int day, int month, int year, int id) {
        this.price = price;
        this.day = day;
        this.month = month;
        this.year = year;
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Float.floatToIntBits(this.price);
        hash = 67 * hash + this.day;
        hash = 67 * hash + this.month;
        hash = 67 * hash + this.year;
        return hash;
    }

    @Override
    public String toString() {
        return this.price + "€" + addSpaces(this.price) + this.day + "." + this.month + "." + this.year;
    }

    public String addSpaces(float money) {
        int length = String.valueOf(money).length();

        if (length == 3) {
            return "            ";
        }
        if (length == 4) {
            return "         ";
        }
        if (length == 5) {
            return "        ";
        }
        return "asdasdasd";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ticket other = (Ticket) obj;
        return true;
    }

    public float getPrice() {
        return this.price;
    }

    @Override
    public int compareTo(Object t) {
        Ticket other = (Ticket) t;

        if (getPrice() > other.getPrice()) {
            return 1;
        } else if (getPrice() < other.getPrice()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public int compare(Ticket t, Ticket t1) {
        if (t.price > t1.price) {
            return 1;
        } else if (t.price < t1.price) {
            return -1;
        } else {
            return 0;
        }
    }

}

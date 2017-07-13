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
    private String leaves;
    private String travelTimeString;
    private int travelhours, travelminutes;

    public int getId() {
        return id;
    }

    public Ticket(float price, int day, int month, int year, int id, String traveltime) {
        this.price = price;
        this.day = day;
        this.month = month;
        this.year = year;
        this.id = id;
        this.travelTimeString = traveltime;
        countTravelTimeToHours(traveltime);
    }
    
    public void countTravelTimeToHours(String traveltime){
        traveltime = traveltime.replaceAll(" ", "");
        char tunnit = traveltime.charAt(0);
        char minuutitEka = traveltime.charAt(2);
        char minuutitToka = traveltime.charAt(3);
        

        
        this.travelhours = Character.getNumericValue(tunnit);
        this.travelminutes = Character.getNumericValue(minuutitEka) + Character.getNumericValue(minuutitToka);
        
        
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
        String bonusSpaces = "";
//        if(this.day < 10){
//            bonusSpaces = " ";
//        }
        
        return this.price + "€" + addSpaces(this.price) + bonusSpaces + this.day + "." + this.month + "." + this.year + "   Traveltime: " + travelTimeString;
    }

    public String addSpaces(float money) {
        int length = String.valueOf(money).length();

        if (length == 3) {
            return "       ";
        }
        if (length == 4) {
            return "      ";
        }
        if (length == 5) {
            return "     ";
        }
        return "too long money value!";
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

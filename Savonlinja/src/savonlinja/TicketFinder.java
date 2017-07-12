/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savonlinja;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JTextArea;

/**
 * Communicates with the server by reading and processing data.
 * @author markokos
 */
public class TicketFinder {

    private int startDay, endDay, month, year, direction;
    private static ArrayList<Ticket> tickets = new ArrayList<>();
    private float saleMultiplier = 1;
    private JTextArea output;

    public void findTickets(int startDay, int endDay, int month, int year, float sale, int direction, JTextArea output) throws MalformedURLException, IOException {

        if (sale != 0) {
            System.out.println("juuh saat alennuksen :DDD");
            float value = sale / 100; // 0.2
            System.out.println(value);
            saleMultiplier = 1 - value;
            System.out.println(saleMultiplier);
        }

        int ticketId = 0;
        this.startDay = startDay;
        this.endDay = endDay;
        this.month = month;
        this.year = year;
        this.direction = direction;
        this.output = output;

        int dayschecked = 1;
        int daysleft = endDay - startDay + 1;

        //String date = "2017-07-18&";
        String date = this.year + "-" + month + "-" + startDay + "&";

        if (month <= 9) {
            date = year + "-0" + month + "-" + startDay + "&";
        }

        while (startDay <= endDay) {
            date = this.year + "-" + month + "-" + startDay + "&";

            URL url = null;

            if (this.direction == 0) { //kangasniemi-helsinki
                url = new URL("https://kauppa.savonlinja.fi/?departure_stop_group=486&arrival_stop_group=92&journey_type=1&date=" + date + "return_date=&adults=0&pensioners=0&students=1&childrenFrom12to16=0&childrenFrom4to11=0");
            } else if (this.direction == 1) {
                url = new URL("https://kauppa.savonlinja.fi/?departure_stop_group=92&arrival_stop_group=486&journey_type=1&date=" + date + "return_date=&adults=0&pensioners=0&students=1&childrenFrom12to16=0&childrenFrom4to11=0");
            } else if (this.direction == 2) {
                url = new URL("https://kauppa.savonlinja.fi/?departure_stop_group=486&arrival_stop_group=483&journey_type=1&date=" + date + "&return_date=&adults=0&pensioners=0&students=1&childrenFrom12to16=0&childrenFrom4to11=0");
            }

            // Get the input stream through URL Connection
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();

            // Once you have the Input Stream, it's just plain old Java IO stuff.
            // For this case, since you are interested in getting plain-text web page
            // I'll use a reader and output the text content to System.out.
            // For binary content, it's better to directly read the bytes from stream and write
            // to the target file.
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = null;
            String todaysPrices = ";";
            boolean alreadyexists = false;
            // read each line and write to System.out
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if (line.contains("€")) {
                    line = line.replace("€", "");
                    line = line.replace(",", ".");

                    Ticket newTicket = new Ticket(Float.parseFloat(line) * this.saleMultiplier, startDay, month, year, ticketId);
                    ticketId++;
                    //System.out.println(newTicket.hashCode());

                    for (Ticket ticket : tickets) {
                        if (ticket.hashCode() == newTicket.hashCode()) {
                            alreadyexists = true;
                            break;
                        }
                    }

                    if (alreadyexists == false) {
                        tickets.add(newTicket);
                    }

                    //System.out.println(line);
                }

            }
            startDay++;
            System.out.println("Analyzing date: " + dayschecked + "/" + daysleft);
            dayschecked++;
        }

        System.out.println("Found total of " + tickets.size() + " tickets.");
        output.append("Found total of " + tickets.size() + " tickets." + "\n");

        ArrayList<Float> prices = new ArrayList<>();
        for (Ticket ticket : tickets) {
            prices.add(ticket.getPrice());
        }

        ArrayList<Float> unsortedPrices = prices;
        Collections.sort(prices);
        System.out.println(unsortedPrices);

        Collections.sort(tickets);
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }

    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void clearTicketList() {
        tickets.clear();
    }
}

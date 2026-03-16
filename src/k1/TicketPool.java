package k1;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private String roomName;
    List<Ticket> tickets = new ArrayList<>();

    public TicketPool(String roomName, int total) {
        this.roomName = roomName;

        for (int i = 1; i <= total; i++) {
            Ticket t = new Ticket(roomName + "-" + i, roomName);
            tickets.add(t);
        }
    }

    public synchronized Ticket sellTicket(){
        for (Ticket t : tickets) {
            if (t.isSold() == false) {
                t.setSold(true);
                return t;
            }
        }

        return null;
    }

    public int remainingTickets() {

        int count = 0;

        for (Ticket t : tickets) {
            if (t.isSold() == false) {
                count++;
            }
        }

        return count;
    }
}

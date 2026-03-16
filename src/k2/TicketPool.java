package k2;

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

    public synchronized void addTickets(int count) {

        int start = tickets.size() + 1;

        for (int i = 0; i < count; i++) {
            Ticket t = new Ticket(roomName + "-" + (start + i), roomName);
            tickets.add(t);
        }
    }

    public synchronized Ticket sellTicket(){
        if (tickets.size() == 0) {
            return null;
        }
        return tickets.remove(0);
    }

    public int remainingTickets() {
        return tickets.size();
    }


}

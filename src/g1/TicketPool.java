package g1;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();
    private int ticketIndex = 1;

    public TicketPool(String roomName, int total) {

        this.roomName = roomName;

        for (int i = 0; i < total; i++) {
            tickets.add(new Ticket(roomName + "-" + ticketIndex++, roomName));
        }
    }

    public synchronized Ticket sellTicket() {

        while (tickets.size() == 0) {
            try {
                System.out.println(Thread.currentThread().getName()
                        + ": Hết vé phòng " + roomName + ", đang chờ...");
                wait();
            } catch (InterruptedException e) {
            }
        }

        Ticket t = tickets.remove(0);
        t.setSold(true);
        return t;
    }

    public synchronized void addTickets(int count) {

        for (int i = 0; i < count; i++) {
            tickets.add(new Ticket(roomName + "-" + ticketIndex++, roomName));
        }

        System.out.println("Nhà cung cấp: Đã thêm " + count + " vé vào phòng " + roomName);

        notifyAll();
    }

    public synchronized int remainingTickets() {
        return tickets.size();
    }
}

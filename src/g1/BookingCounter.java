package g1;

import java.util.Random;

public class BookingCounter implements Runnable {
    private String counterName;
    private TicketPool roomA;
    private TicketPool roomB;
    private int soldCount = 0;

    public BookingCounter(String counterName, TicketPool roomA, TicketPool roomB) {
        this.counterName = counterName;
        this.roomA = roomA;
        this.roomB = roomB;
    }

    public int getSoldCount() {
        return soldCount;
    }

    @Override
    public void run() {

        Random random = new Random();

        while (true) {

            Ticket ticket;

            if (random.nextBoolean()) {
                ticket = roomA.sellTicket();
            } else {
                ticket = roomB.sellTicket();
            }

            soldCount++;

            System.out.println(counterName + " đã bán vé " + ticket.getTicketId());

            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
    }
}

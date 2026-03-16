package k1;

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

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public TicketPool getRoomA() {
        return roomA;
    }

    public void setRoomA(TicketPool roomA) {
        this.roomA = roomA;
    }

    public TicketPool getRoomB() {
        return roomB;
    }

    public void setRoomB(TicketPool roomB) {
        this.roomB = roomB;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }

    @Override
    public void run() {
        while (roomA.remainingTickets() > 0 || roomB.remainingTickets() > 0) {

            Ticket t = roomA.sellTicket();

            if (t == null) {
                t = roomB.sellTicket();
            }

            if (t != null) {
                soldCount++;
                System.out.println(counterName + " đã bán vé " + t.getTicketId());
            }

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}

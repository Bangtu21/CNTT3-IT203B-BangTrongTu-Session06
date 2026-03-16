package g2;

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

    public void sellCombo() {

        synchronized (roomA) {
            synchronized (roomB) {

                Ticket a = roomA.sellTicket();

                if (a == null) {
                    System.out.println(counterName + ": Hết vé phòng A, bán combo thất bại");
                    return;
                }

                Ticket b = roomB.sellTicket();

                soldCount++;

                System.out.println(counterName +
                        " bán combo thành công: "
                        + a.getTicketId() + " & " + b.getTicketId());
            }
        }
    }

    @Override
    public void run() {

        while (true) {
            if (roomA.remainingTickets() == 0 || roomB.remainingTickets() == 0) {
                break;
            }
            sellCombo();

            try {
                Thread.sleep(500);
            } catch (Exception e) {}
        }
    }
}

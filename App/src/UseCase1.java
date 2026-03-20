import java.util.LinkedList;
import java.util.Queue;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println("Guest: " + guestName + ", Room Type: " + roomType);
    }
}

class BookingQueue {
    private Queue<Reservation> queue;

    BookingQueue() {
        queue = new LinkedList<>();
    }

    void addRequest(Reservation reservation) {
        queue.offer(reservation);
    }

    void displayQueue() {
        for (Reservation r : queue) {
            r.display();
        }
    }
}

public class UseCase5 {

    public static void main(String[] args) {

        BookingQueue bookingQueue = new BookingQueue();

        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));

        System.out.println("===== Book My Stay Application =====");
        System.out.println("Version: 5.0");
        System.out.println();

        System.out.println("Booking Requests (FIFO Order):");
        System.out.println();

        bookingQueue.displayQueue();
    }
}
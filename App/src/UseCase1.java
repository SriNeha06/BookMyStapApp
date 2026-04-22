import java.util.*;

class Reservation {
    private String bookingId;
    private String customerName;
    private String roomType;
    private boolean active;

    public Reservation(String bookingId, String customerName, String roomType) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.roomType = roomType;
        this.active = true;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isActive() {
        return active;
    }

    public void cancel() {
        this.active = false;
    }

    @Override
    public String toString() {
        return bookingId + " - " + customerName + " - " + roomType + " - " + (active ? "CONFIRMED" : "CANCELLED");
    }
}

class RoomInventory {
    private Map<String, Integer> rooms = new HashMap<>();

    public RoomInventory() {
        rooms.put("Standard", 2);
        rooms.put("Deluxe", 2);
        rooms.put("Suite", 1);
    }

    public boolean isAvailable(String roomType) {
        return rooms.containsKey(roomType) && rooms.get(roomType) > 0;
    }

    public void allocate(String roomType) {
        rooms.put(roomType, rooms.get(roomType) - 1);
    }

    public void release(String roomType) {
        rooms.put(roomType, rooms.get(roomType) + 1);
    }

    public void printInventory() {
        for (String type : rooms.keySet()) {
            System.out.println(type + " Available: " + rooms.get(type));
        }
    }
}

class BookingHistory {
    private Map<String, Reservation> bookings = new HashMap<>();

    public void add(Reservation r) {
        bookings.put(r.getBookingId(), r);
    }

    public Reservation get(String bookingId) {
        return bookings.get(bookingId);
    }

    public Collection<Reservation> getAll() {
        return bookings.values();
    }
}

class CancellationService {
    private RoomInventory inventory;
    private BookingHistory history;
    private Stack<String> rollbackStack = new Stack<>();

    public CancellationService(RoomInventory inventory, BookingHistory history) {
        this.inventory = inventory;
        this.history = history;
    }

    public void cancelBooking(String bookingId) {
        Reservation r = history.get(bookingId);

        if (r == null) {
            System.out.println("Cancellation failed: Booking not found");
            return;
        }

        if (!r.isActive()) {
            System.out.println("Cancellation failed: Booking already cancelled");
            return;
        }

        rollbackStack.push(r.getBookingId());
        inventory.release(r.getRoomType());
        r.cancel();

        System.out.println("Booking cancelled: " + bookingId);
    }

    public void printRollbackStack() {
        System.out.println("Rollback Stack: " + rollbackStack);
    }
}

public class UseCase10Booking{
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();

        Reservation r1 = new Reservation("B001", "Alice", "Deluxe");
        Reservation r2 = new Reservation("B002", "Bob", "Suite");

        if (inventory.isAvailable(r1.getRoomType())) {
            inventory.allocate(r1.getRoomType());
            history.add(r1);
        }

        if (inventory.isAvailable(r2.getRoomType())) {
            inventory.allocate(r2.getRoomType());
            history.add(r2);
        }

        CancellationService cancelService = new CancellationService(inventory, history);

        cancelService.cancelBooking("B001");
        cancelService.cancelBooking("B003");
        cancelService.cancelBooking("B001");

        System.out.println("\nCurrent Inventory:");
        inventory.printInventory();

        System.out.println("\nBooking History:");
        for (Reservation r : history.getAll()) {
            System.out.println(r);
        }

        System.out.println();
        cancelService.printRollbackStack();
    }
}
import java.io.*;
import java.util.*;

class Reservation implements Serializable {
    private String bookingId;
    private String customerName;
    private String roomType;

    public Reservation(String bookingId, String customerName, String roomType) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.roomType = roomType;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getRoomType() {
        return roomType;
    }

    public String toString() {
        return bookingId + " - " + customerName + " - " + roomType;
    }
}

class RoomInventory implements Serializable {
    private Map<String, Integer> rooms = new HashMap<>();

    public RoomInventory() {
        rooms.put("Standard", 2);
        rooms.put("Deluxe", 2);
        rooms.put("Suite", 1);
    }

    public void allocate(String roomType) {
        rooms.put(roomType, rooms.get(roomType) - 1);
    }

    public void printInventory() {
        for (String type : rooms.keySet()) {
            System.out.println(type + " Available: " + rooms.get(type));
        }
    }

    public Map<String, Integer> getRooms() {
        return rooms;
    }

    public void setRooms(Map<String, Integer> rooms) {
        this.rooms = rooms;
    }
}

class BookingHistory implements Serializable {
    private List<Reservation> reservations = new ArrayList<>();

    public void add(Reservation r) {
        reservations.add(r);
    }

    public List<Reservation> getAll() {
        return reservations;
    }
}

class SystemState implements Serializable {
    RoomInventory inventory;
    BookingHistory history;

    public SystemState(RoomInventory inventory, BookingHistory history) {
        this.inventory = inventory;
        this.history = history;
    }
}

class PersistenceService {
    private static final String FILE_NAME = "system_state.dat";

    public void save(SystemState state) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(state);
            System.out.println("State saved successfully");
        } catch (IOException e) {
            System.out.println("Error saving state");
        }
    }

    public SystemState load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            SystemState state = (SystemState) ois.readObject();
            System.out.println("State loaded successfully");
            return state;
        } catch (Exception e) {
            System.out.println("No previous state found, starting fresh");
            return null;
        }
    }
}

public class UseCase12Data {
    public static void main(String[] args) {
        PersistenceService persistence = new PersistenceService();

        SystemState state = persistence.load();

        RoomInventory inventory;
        BookingHistory history;

        if (state != null) {
            inventory = state.inventory;
            history = state.history;
        } else {
            inventory = new RoomInventory();
            history = new BookingHistory();
        }

        Reservation r1 = new Reservation("B001", "Alice", "Deluxe");
        Reservation r2 = new Reservation("B002", "Bob", "Suite");

        inventory.allocate(r1.getRoomType());
        history.add(r1);

        inventory.allocate(r2.getRoomType());
        history.add(r2);

        System.out.println("\nCurrent Bookings:");
        for (Reservation r : history.getAll()) {
            System.out.println(r);
        }

        System.out.println("\nCurrent Inventory:");
        inventory.printInventory();

        persistence.save(new SystemState(inventory, history));
    }
}
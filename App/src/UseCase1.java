import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingQueue {
    private Queue<Reservation> queue;

    BookingQueue() {
        queue = new LinkedList<>();
    }

    void addRequest(Reservation r) {
        queue.offer(r);
    }

    Reservation getNextRequest() {
        return queue.poll();
    }

    boolean isEmpty() {
        return queue.isEmpty();
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    void reduceAvailability(String type) {
        inventory.put(type, getAvailability(type) - 1);
    }
}

class RoomAllocationService {
    private RoomInventory inventory;
    private HashMap<String, Set<String>> allocatedRooms;
    private int counter = 1;

    RoomAllocationService(RoomInventory inventory) {
        this.inventory = inventory;
        allocatedRooms = new HashMap<>();
    }

    void processBooking(Reservation r) {
        int available = inventory.getAvailability(r.roomType);

        if (available > 0) {
            String roomId = r.roomType.replace(" ", "") + "-" + counter++;
            allocatedRooms.putIfAbsent(r.roomType, new HashSet<>());

            if (!allocatedRooms.get(r.roomType).contains(roomId)) {
                allocatedRooms.get(r.roomType).add(roomId);
                inventory.reduceAvailability(r.roomType);

                System.out.println("Booking Confirmed for " + r.guestName);
                System.out.println("Room Type: " + r.roomType);
                System.out.println("Room ID: " + roomId);
                System.out.println();
            }
        } else {
            System.out.println("Booking Failed for " + r.guestName + " (No Availability)");
            System.out.println();
        }
    }
}

public class UseCase6 {

    public static void main(String[] args) {

        BookingQueue queue = new BookingQueue();

        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Single Room"));
        queue.addRequest(new Reservation("David", "Suite Room"));

        RoomInventory inventory = new RoomInventory();
        RoomAllocationService service = new RoomAllocationService(inventory);

        System.out.println("===== Book My Stay Application =====");
        System.out.println("Version: 6.0");
        System.out.println();

        while (!queue.isEmpty()) {
            Reservation r = queue.getNextRequest();
            service.processBooking(r);
        }
    }
}
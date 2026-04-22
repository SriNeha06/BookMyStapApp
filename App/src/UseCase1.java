import java.util.*;

class BookingRequest {
    private String customerName;
    private String roomType;

    public BookingRequest(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class RoomInventory {
    private Map<String, Integer> rooms = new HashMap<>();

    public RoomInventory() {
        rooms.put("Standard", 2);
        rooms.put("Deluxe", 2);
        rooms.put("Suite", 1);
    }

    public synchronized boolean allocate(String roomType) {
        if (!rooms.containsKey(roomType)) return false;
        int available = rooms.get(roomType);
        if (available <= 0) return false;
        rooms.put(roomType, available - 1);
        return true;
    }

    public synchronized void printInventory() {
        for (String type : rooms.keySet()) {
            System.out.println(type + " Available: " + rooms.get(type));
        }
    }
}

class BookingQueue {
    private Queue<BookingRequest> queue = new LinkedList<>();

    public synchronized void addRequest(BookingRequest request) {
        queue.add(request);
    }

    public synchronized BookingRequest getRequest() {
        return queue.poll();
    }
}

class BookingProcessor extends Thread {
    private BookingQueue queue;
    private RoomInventory inventory;

    public BookingProcessor(BookingQueue queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {
        while (true) {
            BookingRequest request;
            synchronized (queue) {
                request = queue.getRequest();
            }

            if (request == null) break;

            boolean success = inventory.allocate(request.getRoomType());

            if (success) {
                System.out.println(Thread.currentThread().getName() + " booked " + request.getRoomType() + " for " + request.getCustomerName());
            } else {
                System.out.println(Thread.currentThread().getName() + " failed booking for " + request.getCustomerName());
            }
        }
    }
}

public class UseCase11Concurrent {
    public static void main(String[] args) {
        BookingQueue queue = new BookingQueue();
        RoomInventory inventory = new RoomInventory();

        queue.addRequest(new BookingRequest("Alice", "Deluxe"));
        queue.addRequest(new BookingRequest("Bob", "Deluxe"));
        queue.addRequest(new BookingRequest("Charlie", "Deluxe"));
        queue.addRequest(new BookingRequest("David", "Suite"));
        queue.addRequest(new BookingRequest("Eve", "Suite"));

        Thread t1 = new BookingProcessor(queue, inventory);
        Thread t2 = new BookingProcessor(queue, inventory);
        Thread t3 = new BookingProcessor(queue, inventory);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nFinal Inventory:");
        inventory.printInventory();
    }
}
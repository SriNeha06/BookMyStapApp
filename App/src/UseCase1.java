import java.util.HashMap;
import java.util.Map;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class RoomInventory {
    private Map<String, Integer> rooms = new HashMap<>();

    public RoomInventory() {
        rooms.put("Standard", 2);
        rooms.put("Deluxe", 2);
        rooms.put("Suite", 1);
    }

    public void validateRoomType(String roomType) throws InvalidBookingException {
        if (!rooms.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }
    }

    public void validateAvailability(String roomType) throws InvalidBookingException {
        int available = rooms.get(roomType);
        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for type: " + roomType);
        }
    }

    public void bookRoom(String roomType) throws InvalidBookingException {
        validateRoomType(roomType);
        validateAvailability(roomType);
        rooms.put(roomType, rooms.get(roomType) - 1);
    }

    public void printInventory() {
        for (String type : rooms.keySet()) {
            System.out.println(type + " Available: " + rooms.get(type));
        }
    }
}

class BookingService {
    private RoomInventory inventory;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void createBooking(String customerName, String roomType) {
        try {
            if (customerName == null || customerName.trim().isEmpty()) {
                throw new InvalidBookingException("Customer name cannot be empty");
            }

            inventory.bookRoom(roomType);

            System.out.println("Booking successful for " + customerName + " in " + roomType);
        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }
}

public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(inventory);

        service.createBooking("Alice", "Deluxe");
        service.createBooking("", "Standard");
        service.createBooking("Bob", "Premium");
        service.createBooking("Charlie", "Suite");
        service.createBooking("David", "Suite");

        System.out.println("\nCurrent Inventory:");
        inventory.printInventory();
    }
}
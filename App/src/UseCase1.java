import java.util.ArrayList;
import java.util.List;

class Reservation {
    private String bookingId;
    private String customerName;
    private String roomType;
    private int nights;
    private double pricePerNight;

    public Reservation(String bookingId, String customerName, String roomType, int nights, double pricePerNight) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.roomType = roomType;
        this.nights = nights;
        this.pricePerNight = pricePerNight;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNights() {
        return nights;
    }

    public double getTotalCost() {
        return nights * pricePerNight;
    }

    @Override
    public String toString() {
        return "BookingID: " + bookingId +
                ", Name: " + customerName +
                ", Room: " + roomType +
                ", Nights: " + nights +
                ", Total: " + getTotalCost();
    }
}

class BookingHistory {
    private List<Reservation> reservations;

    public BookingHistory() {
        reservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations);
    }
}

class BookingReportService {
    public void printAllBookings(List<Reservation> reservations) {
        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }

    public void printSummary(List<Reservation> reservations) {
        int totalBookings = reservations.size();
        double totalRevenue = 0;

        for (Reservation r : reservations) {
            totalRevenue += r.getTotalCost();
        }

        System.out.println("Total Bookings: " + totalBookings);
        System.out.println("Total Revenue: " + totalRevenue);
    }
}

public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {
        BookingHistory history = new BookingHistory();

        Reservation r1 = new Reservation("B001", "Alice", "Deluxe", 2, 3000);
        Reservation r2 = new Reservation("B002", "Bob", "Suite", 3, 5000);
        Reservation r3 = new Reservation("B003", "Charlie", "Standard", 1, 1500);

        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        BookingReportService reportService = new BookingReportService();

        System.out.println("All Bookings:");
        reportService.printAllBookings(history.getAllReservations());

        System.out.println("\nSummary Report:");
        reportService.printSummary(history.getAllReservations());
    }
}
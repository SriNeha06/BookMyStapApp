import java.util.*;

class Service {
    String name;
    double cost;

    Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

class AddOnServiceManager {
    private HashMap<String, List<Service>> serviceMap = new HashMap<>();

    void addService(String reservationId, Service service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    double getTotalCost(String reservationId) {
        double total = 0;
        List<Service> services = serviceMap.getOrDefault(reservationId, new ArrayList<>());
        for (Service s : services) {
            total += s.cost;
        }
        return total;
    }

    void displayServices(String reservationId) {
        List<Service> services = serviceMap.getOrDefault(reservationId, new ArrayList<>());
        if (services.isEmpty()) {
            System.out.println("No add-on services selected.");
        } else {
            for (Service s : services) {
                System.out.println(s.name + " - " + s.cost);
            }
        }
    }
}

public class UseCase7{

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId1 = "SingleRoom-1";
        String reservationId2 = "DoubleRoom-2";

        manager.addService(reservationId1, new Service("Breakfast", 200));
        manager.addService(reservationId1, new Service("WiFi", 100));
        manager.addService(reservationId2, new Service("Airport Pickup", 500));

        System.out.println("===== Book My Stay Application =====");
        System.out.println("Version: 7.0");
        System.out.println();

        System.out.println("Services for " + reservationId1 + ":");
        manager.displayServices(reservationId1);
        System.out.println("Total Cost: " + manager.getTotalCost(reservationId1));
        System.out.println();

        System.out.println("Services for " + reservationId2 + ":");
        manager.displayServices(reservationId2);
        System.out.println("Total Cost: " + manager.getTotalCost(reservationId2));
    }
}
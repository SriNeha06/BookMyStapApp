Project: Use Case 11 - Concurrent Booking Simulation

Description:
This program simulates concurrent booking requests using multiple threads. It ensures thread-safe access to shared resources like booking queue and room inventory.

Compilation:
javac UseCase11ConcurrentBookingSimulation.java

Execution:
java UseCase11ConcurrentBookingSimulation

Features:
- Simulates multiple users booking simultaneously
- Uses shared queue for booking requests
- Synchronizes inventory updates to prevent race conditions
- Prevents double booking
- Maintains consistent system state

Components:
- BookingRequest: Represents a booking request
- BookingQueue: Shared request queue
- RoomInventory: Shared inventory with synchronized access
- BookingProcessor: Multi-threaded booking handler

Sample Output:
Thread-0 booked Deluxe for Alice
Thread-1 booked Deluxe for Bob
Thread-2 failed booking for Charlie
Thread-0 booked Suite for David
Thread-1 failed booking for Eve

Final Inventory:
Standard Available: 2
Deluxe Available: 0
Suite Available: 0
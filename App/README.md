Project: Use Case 12 - Data Persistence & System Recovery

Description:
This program demonstrates saving and restoring application state using file-based persistence in Java. It ensures booking and inventory data survive application restarts.

Compilation:
javac UseCase12DataPersistenceRecovery.java

Execution:
java UseCase12DataPersistenceRecovery

Features:
- Serializes booking history and inventory to file
- Restores system state on restart
- Handles missing or corrupted files gracefully
- Maintains continuity across executions

Components:
- Reservation: Booking entity (Serializable)
- RoomInventory: Manages room data
- BookingHistory: Stores reservations
- SystemState: Wrapper for full system snapshot
- PersistenceService: Handles save/load operations

Behavior:
- On first run, system starts fresh
- On subsequent runs, previous state is restored
- New bookings are added and persisted again

Sample Output (First Run):
No previous state found, starting fresh

Current Bookings:
B001 - Alice - Deluxe
B002 - Bob - Suite

Current Inventory:
Standard Available: 2
Deluxe Available: 1
Suite Available: 0

State saved successfully

Sample Output (Second Run):
State loaded successfully
...
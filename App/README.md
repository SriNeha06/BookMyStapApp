Project: Use Case 10 - Booking Cancellation & Inventory Rollback

Description:
This program demonstrates booking cancellation with safe rollback using Core Java. It ensures inventory consistency by reversing previous booking operations.

Compilation:
javac UseCase10BookingCancellation.java

Execution:
java UseCase10BookingCancellation

Features:
- Cancels only valid and active bookings
- Restores room inventory after cancellation
- Prevents duplicate or invalid cancellations
- Uses stack for rollback tracking (LIFO behavior)
- Maintains consistent system state

Components:
- Reservation: Represents booking state
- RoomInventory: Manages room availability
- BookingHistory: Stores bookings
- CancellationService: Handles cancellation and rollback logic

Sample Output:
Booking cancelled: B001
Cancellation failed: Booking not found
Cancellation failed: Booking already cancelled

Current Inventory:
Standard Available: 2
Deluxe Available: 2
Suite Available: 0

Booking History:
B001 - Alice - Deluxe - CANCELLED
B002 - Bob - Suite - CONFIRMED

Rollback Stack: [B001]
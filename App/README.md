Project: Use Case 9 - Error Handling & Validation

Description:
This program demonstrates validation and error handling in a Hotel Booking System using Core Java. It ensures invalid inputs are detected early and handled gracefully without affecting system stability.

Compilation:
javac UseCase9ErrorHandlingValidation.java

Execution:
java UseCase9ErrorHandlingValidation

Features:
- Validates customer input
- Validates room types
- Prevents overbooking
- Uses custom exceptions for clear error handling
- Ensures system continues after failures

Components:
- InvalidBookingException: Custom exception for booking errors
- RoomInventory: Manages room availability and validation
- BookingService: Handles booking logic with validation

Sample Output:
Booking successful for Alice in Deluxe
Booking failed: Customer name cannot be empty
Booking failed: Invalid room type: Premium
Booking successful for Charlie in Suite
Booking failed: No rooms available for type: Suite

Current Inventory:
Standard Available: 2
Deluxe Available: 1
Suite Available: 0
Project: Use Case 8 - Booking History & Reporting

Description:
This program demonstrates a simple Hotel Booking History and Reporting system using Core Java. It stores confirmed reservations in memory and generates reports based on stored data.

Compilation:
javac UseCase8BookingHistoryReport.java

Execution:
java UseCase8BookingHistoryReport

Features:
- Stores confirmed bookings in insertion order
- Retrieves booking history without modification
- Generates detailed booking list
- Generates summary report with total bookings and revenue

Components:
- Reservation: Represents a booking
- BookingHistory: Stores all reservations
- BookingReportService: Generates reports

Sample Output:
All Bookings:
BookingID: B001, Name: Alice, Room: Deluxe, Nights: 2, Total: 6000.0
BookingID: B002, Name: Bob, Room: Suite, Nights: 3, Total: 15000.0
BookingID: B003, Name: Charlie, Room: Standard, Nights: 1, Total: 1500.0

Summary Report:
Total Bookings: 3
Total Revenue: 22500.0
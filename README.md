# Railway Reservation System

## 1. Project Overview

The Railway Reservation System is a web-based mini project developed to demonstrate the practical application of fundamental data structures, especially Queue and Linked List concepts.

The system allows users to book railway tickets, manage passenger reservations, cancel tickets, and maintain a waiting list. When all available seats are booked, new passengers are automatically added to the waiting list. When a confirmed reservation is cancelled, the first passenger in the waiting queue is promoted to a confirmed reservation.

The project provides a simple and user-friendly interface for demonstrating railway reservation operations.

## 2. Objectives

- To implement railway ticket booking and cancellation.
- To demonstrate the FIFO (First In, First Out) principle.
- To manage confirmed passengers and waiting-list entries.
- To display passenger information dynamically.
- To track seat availability.
- To understand data structure operations through a real-world application.

## 3. Technologies Used

- **HTML5:** Creates the structure of the web application.
- **CSS3:** Provides styling, layout, and responsive design.
- **JavaScript:** Implements booking, cancellation, queue management, and dynamic updates.
- **Data Structures:** Uses an array for confirmed reservations and a FIFO queue for waiting passengers.

## 4. Project Structure

RailwayReservationSystem/
│
├── index.html
├── style.css
├── script.js
└── README.md

## 5. Features

### Passenger Booking
Users can enter a passenger's name, age, and train name to book a ticket.

### Confirmed Reservations
Passengers receive confirmed tickets when seats are available. Each passenger is assigned a unique ID.

### Waiting List Management
When all seats are occupied, new passengers are placed in the waiting queue in arrival order.

### Ticket Cancellation
Users can cancel confirmed reservations or remove passengers from the waiting list using their passenger ID.

### Automatic Seat Allocation
When a confirmed reservation is cancelled, the first passenger in the waiting queue receives the available seat.

### Seat Availability
The dashboard displays the total number of seats, booked seats, available seats, and waiting passengers.

### Responsive Interface
The application adapts to desktop and mobile screen sizes.

## 6. Data Structures and Concepts

### Queue
The waiting list follows the FIFO principle.

- `push()` is represented by JavaScript's `push()` method to add a passenger to the end of the queue.
- `shift()` removes the first passenger from the queue.
- `length` checks the number of waiting passengers.

### Confirmed Reservation List
An array stores confirmed passenger records.

- `push()` adds a reservation.
- `findIndex()` locates a passenger using their ID.
- `splice()` removes a reservation.
- `forEach()` traverses passenger records.

**Note:** The JavaScript version uses an array for confirmed reservations rather than implementing an actual linked list. The Java version can use Java's `LinkedList` class for that purpose.

## 7. Installation and Execution

### Requirements
- A computer or smartphone with a modern web browser.
- A text editor such as Visual Studio Code or Notepad.

### Steps

1. Create a folder named `RailwayReservationSystem`.
2. Save the HTML code as `index.html`.
3. Save the CSS code as `style.css`.
4. Save the JavaScript code as `script.js`.
5. Ensure all three files are in the same folder.
6. Open `index.html` in a web browser.
7. Start booking tickets and testing the available features.

No server or database installation is required for this demonstration.

## 8. How to Use

1. Open the application in your browser.
2. Enter the passenger's name, age, and train name.
3. Click **Book Ticket**.
4. View the passenger in the confirmed reservations list or waiting list.
5. Note the passenger ID displayed by the application.
6. Enter the ID in the cancellation section to cancel a ticket.
7. Check the dashboard to see updated seat availability.

The application uses three seats by default. This can be changed by modifying the `TOTAL_SEATS` constant in `script.js`.

## 9. Limitations

- Passenger information is stored only in browser memory.
- Data is lost when the page is refreshed or closed.
- The application does not connect to a real railway reservation database.
- Train schedules, fares, payments, and seat selection are not implemented.
- The application is intended for academic demonstration rather than actual railway bookings.

## 10. Future Enhancements

- Integrate a database to store passenger records permanently.
- Add login and user authentication.
- Implement train search and schedule management.
- Add seat selection and fare calculation.
- Generate downloadable tickets.
- Connect the frontend to a Java backend using APIs.
- Implement a custom linked list in JavaScript for confirmed reservations.

## 11. Conclusion

The Railway Reservation System demonstrates how fundamental data structures can be applied to a practical reservation scenario. Queue operations help manage waiting passengers fairly using FIFO order, while reservation-list operations support passenger insertion, deletion, searching, and display.

The project provides a foundation for understanding data structures, JavaScript programming, and web development through a simple and interactive application.
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Passenger {
    int passengerId;
    String name;
    int age;
    String trainName;

    Passenger(int passengerId, String name, int age,
              String trainName) {
        this.passengerId = passengerId;
        this.name = name;
        this.age = age;
        this.trainName = trainName;
    }

    @Override
    public String toString() {
        return "Passenger ID: " + passengerId
                + ", Name: " + name
                + ", Age: " + age
                + ", Train: " + trainName;
    }
}

public class RailwayReservationSystem {

    private static final int TOTAL_SEATS = 3;

    // Linked List stores confirmed reservations
    private static LinkedList<Passenger> reservations =
            new LinkedList<>();

    // Queue stores passengers waiting for seats
    private static Queue<Passenger> waitingList =
            new LinkedList<>();

    private static Scanner sc = new Scanner(System.in);
    private static int nextId = 1;

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== RAILWAY RESERVATION SYSTEM =====");
            System.out.println("1. Add Passenger / Book Ticket");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. Display Confirmed Passengers");
            System.out.println("4. Display Waiting List");
            System.out.println("5. Check Seat Availability");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    addPassenger();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    displayReservations();
                    break;
                case 4:
                    displayWaitingList();
                    break;
                case 5:
                    checkAvailability();
                    break;
                case 6:
                    System.out.println(
                            "Thank you for using the system!");
                    break;
                default:
                    System.out.println(
                            "Invalid choice. Try again.");
            }
        } while (choice != 6);

        sc.close();
    }

    private static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    // Add passenger using Queue and Linked List
    private static void addPassenger() {
        System.out.print("Enter passenger name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter passenger age: ");
        int age = readInt();

        if (age <= 0 || age > 120) {
            System.out.println("Enter a valid age.");
            return;
        }

        System.out.print("Enter train name: ");
        String trainName = sc.nextLine().trim();

        if (trainName.isEmpty()) {
            System.out.println("Train name cannot be empty.");
            return;
        }

        Passenger passenger = new Passenger(
                nextId++, name, age, trainName);

        if (reservations.size() < TOTAL_SEATS) {
            reservations.add(passenger);
            System.out.println(
                    "Ticket confirmed successfully!");
            System.out.println(passenger);
        } else {
            waitingList.offer(passenger);
            System.out.println(
                    "All seats are booked.");
            System.out.println(
                    "Passenger added to waiting list.");
            System.out.println(passenger);
        }
    }

    // Cancel confirmed ticket and promote first waiting passenger
    private static void cancelReservation() {
        System.out.print("Enter Passenger ID to cancel: ");
        int id = readInt();

        Passenger found = null;

        for (Passenger passenger : reservations) {
            if (passenger.passengerId == id) {
                found = passenger;
                break;
            }
        }

        if (found != null) {
            reservations.remove(found);
            System.out.println(
                    "Reservation cancelled successfully.");

            if (!waitingList.isEmpty()) {
                Passenger promoted = waitingList.poll();
                reservations.add(promoted);

                System.out.println(
                        "A waiting-list ticket is now confirmed:");
                System.out.println(promoted);
            }
            return;
        }

        // Also allow cancellation from the waiting list
        Passenger waitingPassenger = null;

        for (Passenger passenger : waitingList) {
            if (passenger.passengerId == id) {
                waitingPassenger = passenger;
                break;
            }
        }

        if (waitingPassenger != null) {
            waitingList.remove(waitingPassenger);
            System.out.println(
                    "Waiting-list entry cancelled.");
        } else {
            System.out.println(
                    "Passenger ID not found.");
        }
    }

    // Display confirmed reservations
    private static void displayReservations() {
        System.out.println("\n--- Confirmed Reservations ---");

        if (reservations.isEmpty()) {
            System.out.println(
                    "No confirmed reservations.");
            return;
        }

        for (Passenger passenger : reservations) {
            System.out.println(passenger);
        }

        System.out.println("Seats booked: "
                + reservations.size() + "/" + TOTAL_SEATS);
    }

    // Display passengers in FIFO order
    private static void displayWaitingList() {
        System.out.println("\n--- Waiting List ---");

        if (waitingList.isEmpty()) {
            System.out.println("Waiting list is empty.");
            return;
        }

        int position = 1;

        for (Passenger passenger : waitingList) {
            System.out.println(
                    position + ". " + passenger);
            position++;
        }
    }

    // Check available seats and queue status
    private static void checkAvailability() {
        int availableSeats =
                TOTAL_SEATS - reservations.size();

        System.out.println("\n--- Seat Availability ---");
        System.out.println("Total seats: " + TOTAL_SEATS);
        System.out.println("Booked seats: "
                + reservations.size());
        System.out.println("Available seats: "
                + availableSeats);
        System.out.println("Waiting passengers: "
                + waitingList.size());

        if (waitingList.isEmpty()) {
            System.out.println("Waiting queue is empty.");
        } else {
            System.out.println("Waiting queue is not empty.");
        }

        if (availableSeats == 0) {
            System.out.println("All seats are booked.");
        } else {
            System.out.println("Seats are available.");
        }
    }
}
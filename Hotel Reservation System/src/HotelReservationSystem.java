import java.util.*;

class Room {
    int roomNumber;
    String category;
    boolean isBooked;
    boolean hasAC;
    double pricePerNight;

    public Room(int roomNumber, String category, boolean hasAC, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.hasAC = hasAC;
        this.pricePerNight = pricePerNight;
        this.isBooked = false;
    }
}

class Booking {
    String customerName;
    int roomNumber;
    String checkInDate;
    String checkOutDate;
    double advancePaid;

    public Booking(String customerName, int roomNumber, String checkInDate, String checkOutDate, double advancePaid) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.advancePaid = advancePaid;
    }
}

public class HotelReservationSystem {
    private static List<Room> rooms = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeRooms();
        int choice;

        while (true) {
            System.out.println("\n==== Hotel Reservation System ====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View All Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    cancelBooking();
                    break;
                case 4:
                    viewBookings();
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Standard", false, 1000));
        rooms.add(new Room(102, "Standard", true, 1200));
        rooms.add(new Room(201, "Deluxe", false, 1800));
        rooms.add(new Room(202, "Deluxe", true, 2000));
        rooms.add(new Room(301, "Suite", true, 3000));
    }

    private static void viewAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");
        for (Room room : rooms) {
            if (!room.isBooked) {
                String acStatus = room.hasAC ? "AC" : "Non-AC";
                System.out.println("Room " + room.roomNumber + " - " + room.category + " (" + acStatus + ") - Rs. " + room.pricePerNight + " per night");
            }
        }
    }

    private static void bookRoom() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        viewAvailableRooms();

        Room selectedRoom = null;
        while (true) {
            System.out.print("Enter room number to book: ");
            if (scanner.hasNextInt()) {
                int roomNum = scanner.nextInt();
                scanner.nextLine();
                for (Room room : rooms) {
                    if (room.roomNumber == roomNum && !room.isBooked) {
                        selectedRoom = room;
                        break;
                    }
                }
                if (selectedRoom != null) break;
                else System.out.println("Invalid or already booked room number. Try again.");
            } else {
                System.out.println("Invalid input. Please enter a valid room number.");
                scanner.nextLine();
            }
        }

        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        String checkIn = scanner.nextLine();
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        String checkOut = scanner.nextLine();

        double advance;
        while (true) {
            System.out.print("Enter advance amount (min 500): ");
            if (scanner.hasNextDouble()) {
                advance = scanner.nextDouble();
                scanner.nextLine();
                if (advance >= 500) break;
                else System.out.println("Advance amount must be at least Rs. 500.");
            } else {
                System.out.println("Invalid input. Please enter a valid amount.");
                scanner.nextLine();
            }
        }

        selectedRoom.isBooked = true;
        bookings.add(new Booking(name, selectedRoom.roomNumber, checkIn, checkOut, advance));
        System.out.println("Room " + selectedRoom.roomNumber + " successfully booked for " + name);
    }

    private static void cancelBooking() {
        System.out.print("Enter your name to cancel booking: ");
        String name = scanner.nextLine();

        Booking toRemove = null;
        for (Booking b : bookings) {
            if (b.customerName.equalsIgnoreCase(name)) {
                toRemove = b;
                break;
            }
        }

        if (toRemove != null) {
            bookings.remove(toRemove);
            for (Room room : rooms) {
                if (room.roomNumber == toRemove.roomNumber) {
                    room.isBooked = false;
                    break;
                }
            }
            System.out.println("Booking for " + name + " has been canceled.");
        } else {
            System.out.println("No booking found under that name.");
        }
    }

    private static void viewBookings() {
        System.out.println("\n--- All Bookings ---");
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }
        for (Booking b : bookings) {
            System.out.println("Name: " + b.customerName + ", Room: " + b.roomNumber + ", Check-in: " + b.checkInDate + ", Check-out: " + b.checkOutDate + ", Advance: Rs. " + b.advancePaid);
        }
    }
}

package org.learning.events;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Event event = null;

        System.out.println();
        System.out.println("----------------------------");
        System.out.println("|        EVENT MENU        |");
        System.out.println("----------------------------");
        System.out.println("1. Create event");
        System.out.println("2. Exit");
        System.out.println("----------------------------");
        System.out.println();

        String choice = scanner.nextLine();

        while (event == null) {
            switch (choice) {
                case "1":
                    event = createEvent(scanner);
                    eventMenu(scanner, event);
                case "2":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

        scanner.close();
    }

    public static Event createEvent(Scanner scanner) {
        Event event = null;
        boolean valid = false;

        while (event == null) {

            String title = null;
            LocalDate date = null;
            Integer totalSeats = null;

            while (valid == false) {
                System.out.print("Enter a title: ");
                title = scanner.nextLine();
                valid = title != null && !title.isEmpty();
            }

            valid = false;

            while (valid == false) {
                System.out.print("Enter a date: ");
                try {
                    date = LocalDate.parse(scanner.nextLine(), Event.FORMATTER);
                    Event.checkDate(date);
                    valid = true;
                } catch (DateTimeParseException e) {
                    System.out.println("Enter a valid date!");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            valid = false;

            while (valid == false) {
                System.out.print("Enter total seats: ");
                try {
                    totalSeats = Integer.parseInt(scanner.nextLine());
                    Event.checkSeats(totalSeats);
                    valid = true;
                } catch (NumberFormatException e) {
                    System.out.println("Enter a valid number!");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            try {
                event = new Event(title, date, totalSeats);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return event;
    }

    public static void eventMenu(Scanner scanner, Event event) {

        while (true) {
            System.out.println();
            System.out.println("----------------------------");
            System.out.println("|        EVENT MENU        |");
            System.out.println("----------------------------");
            System.out.println("1. Add reserved seats");
            System.out.println("2. Cancel reserved seats");
            System.out.println("3. View seats");
            System.out.println("4. Exit");
            System.out.println("----------------------------");
            System.out.println();

            String choice = scanner.nextLine();
            System.out.println();

            switch (choice) {
                case "1":
                    while (true) {
                        if (event.getReservedSeats() == event.getTotalSeats()) {
                            System.out.println("All seats has been reserved.");
                            break;
                        }

                        System.out.print("Enter number of seats to reserve: ");
                        try {
                            int seats = Integer.parseInt(scanner.nextLine());
                            event.reserve(seats);
                            System.out.println("Seats reserved.");
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Enter a valid number!");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case "2":
                    while (true) {
                        if (event.getReservedSeats() == 0) {
                            System.out.println("You have no reserved seats.");
                            break;
                        }

                        System.out.print("Enter number of seats to cancel: ");
                        try {
                            int seats = Integer.parseInt(scanner.nextLine());
                            event.cancel(seats);
                            System.out.println("Seats cancelled.");
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Enter a valid number!");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case "3":
                    System.out.println("Reserved Seats: " + event.getReservedSeats());
                    System.out.println("Total Seats: " + event.getTotalSeats());
                    System.out.println("Remaining Seats: " + (event.getTotalSeats() - event.getReservedSeats()));
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}

package org.learning.events;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    private String title;
    private LocalDate date;
    private int totalSeats;
    private int reservedSeats = 0;

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Event(String title, LocalDate date, int totalSeats) throws IllegalArgumentException {
        this.title = title;
        setDate(date);
        setTotalSeats(totalSeats);
    }

    // Getter and Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) throws IllegalArgumentException {
        this.date = checkDate(date);
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    private void setTotalSeats(int totalSeats) throws IllegalArgumentException {
        this.totalSeats = checkSeats(totalSeats);
    }

    public int getReservedSeats() {
        return reservedSeats;
    }

    @Override
    public String toString() {
        return date.format(FORMATTER) + "-" + title;
    }

    public void reserve(int seats) throws IllegalArgumentException {
        if (reservedSeats + seats > totalSeats) {
            throw new IllegalArgumentException("Not enough seats avaible. Remaining Seats: " + (totalSeats - reservedSeats));
        }
        checkSeats(seats);
        checkDate(date);
        reservedSeats += seats;
    }

    public void cancel(int seats) throws IllegalArgumentException {
        if (reservedSeats - seats < 0) {
            throw new IllegalArgumentException("Not enough reserved seats avaible. Reserved Seats: " + reservedSeats);
        }
        checkSeats(seats);
        checkDate(date);
        reservedSeats -= seats;
    }

    // Utility methods
    public static LocalDate checkDate(LocalDate date) throws IllegalArgumentException {
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("The event date has passed");
        }
        return date;
    }

    public static int checkSeats(int seats) throws IllegalArgumentException {
        if (seats < 0) {
            throw new IllegalArgumentException("Seats must be greater than 0!");
        }
        return seats;
    }
}

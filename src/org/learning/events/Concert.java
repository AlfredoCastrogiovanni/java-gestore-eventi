package org.learning.events;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concert extends Event {
    private LocalTime time;
    private BigDecimal price;

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");


    public Concert(String title, LocalDate date, int totalSeats, LocalTime time, BigDecimal price) {
        super(title, date, totalSeats);
        this.time = time;
        this.price = price;
    }

    // Getter and Setter
    public LocalTime getTime() {
        return time;
    }

    public String getFormattedDateTime() {
        return String.format("%s %s", getDate().format(DATE_FORMATTER), getTime().format(TIME_FORMATTER));
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getFormattedPrice() {
        return String.format("%.2f$", price);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return getFormattedDateTime() + " - " + title + " - " + getFormattedPrice();
    }

    public static void checkPrice(BigDecimal price) {
        if (price.compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalArgumentException("Price must be greater than 0!");
        }
    }
}

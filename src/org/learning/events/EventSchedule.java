package org.learning.events;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventSchedule {
    private String title;
    private List<Event> events;

    public EventSchedule(String title, List<Event> events) {
        this.title = title;
        this.events = new ArrayList<>(events);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEventsByDate(LocalDate date) {
        List<Event> events = new ArrayList<>();

        this.events.forEach(e -> {
            if (e.getDate().isEqual(date)) {
                events.add(e);
            }
        });
        return events;
    }

    public int getEventsLength() {
        return events.size();
    }

    public void clearEvents() {
        events.clear();
    }

    @Override
    public String toString() {
        String s = title + "\n" ;

        events.sort(new EventDateComparator());

        for (Event e : events) {
            s += e.getDate().format(Event.DATE_FORMATTER) + " - " + e.getTitle() + "\n";
        }

        return s;
    }
}

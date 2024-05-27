package org.learning.events;

import java.util.Comparator;

public class EventDateComparator implements Comparator<Event> {
    @Override
    public int compare(Event o1, Event o2) {
        return o1.getDate().compareTo(o1.getDate());
    }
}

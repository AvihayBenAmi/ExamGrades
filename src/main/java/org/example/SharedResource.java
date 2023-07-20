package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class SharedResource<T> {
    private final List<T> list = new ArrayList<>();
    private final Random random = new Random();
    private final Object lock = new Object(); //מנעול לסנכרון תהליכונים

    public void addToList(T element) {
        synchronized (lock) { //מנעול
            if (!list.contains(element)) {
                list.add(element);
            }
        }
    }

    public T removeFromList() {
        synchronized (lock) { //מנעול
            if (!list.isEmpty()) {
                return list.remove(random.nextInt(list.size()));
            }
            return null; //אין צורך להחזיר את האלמנט שנמחק
        }
    }

    public List<T> getList() { //פונקציה המאפשרת לקבל את הרשימה
        synchronized (lock) { //מנעול
            return new ArrayList<>(list);
        }
    }

    @Override
    public String toString() {
        return "SharedResource{" +
                "list=" + list +
                ", random=" + random +
                '}';
    }
}
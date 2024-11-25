// You are given a struct Block in C++ that represents the time during which a person is busy, with attributes: personId, startTime, and endTime. You are also given an integer totalTime which represents the total duration. The task is to find the time intervals during which all the persons are free.

import java.util.*;

class Person {
    int id;
    int start;
    int end;

    public Person(int id, int start, int end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }
}

public class FindFreeTime {
    public static List<int[]> findFreeIntervals(List<Person> persons) {
        TreeMap<Integer, Integer> mp = new TreeMap<>();

        // Build the difference array using TreeMap
        for (Person person : persons) {
            mp.put(person.start, mp.getOrDefault(person.start, 0) + 1);
            mp.put(person.end + 1, mp.getOrDefault(person.end + 1, 0) - 1);
        }

        // Find free intervals
        List<int[]> freeIntervals = new ArrayList<>();
        int current = 0;
        int previousTime = 0;

        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            int time = entry.getKey();
            int value = entry.getValue();

            if (current == 0 && time > previousTime) {
                // Add a free interval
                freeIntervals.add(new int[]{previousTime, time - 1});
            }

            current += value;
            previousTime = time;
        }

        return freeIntervals;
    }

    public static void main(String[] args) {
        // Test cases
        List<Person> persons1 = Arrays.asList(
                new Person(1, 2, 3),
                new Person(2, 0, 2),
                new Person(3, 5, 6),
                new Person(4, 9, 12)
        );

        List<Person> persons2 = Arrays.asList(
                new Person(1, 2, 3),
                new Person(2, 2, 2),
                new Person(3, 6, 6),
                new Person(4, 9, 12)
        );

        System.out.println("Free intervals for persons1:");
        for (int[] interval : findFreeIntervals(persons1)) {
            System.out.println(Arrays.toString(interval));
        }

        System.out.println("Free intervals for persons2:");
        for (int[] interval : findFreeIntervals(persons2)) {
            System.out.println(Arrays.toString(interval));
        }
    }
}

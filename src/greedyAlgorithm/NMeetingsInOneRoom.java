package greedyAlgorithm;

import java.util.*;

public class NMeetingsInOneRoom {

    static class Pair {
        int start, end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    int maxMeetings(int[] start, int[] end, int n) {
        Vector<Pair> meetings = new Vector<>();

        for (int i = 0; i < n; i++) {
            meetings.add(new Pair(start[i], end[i]));
        }

        // Sort meetings by end time
        meetings.sort(new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return a.end - b.end;
            }
        });

        int count = 1; // First meeting always selected
        int lastEnd = meetings.get(0).end;

        for (int i = 1; i < n; i++) {
            if (meetings.get(i).start > lastEnd) {
                count++;
                lastEnd = meetings.get(i).end;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        NMeetingsInOneRoom obj = new NMeetingsInOneRoom();

        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end =   {2, 4, 6, 7, 9, 9};
        int n = start.length;

        System.out.println("Max meetings that can be scheduled: " + obj.maxMeetings(start, end, n));
    }
}

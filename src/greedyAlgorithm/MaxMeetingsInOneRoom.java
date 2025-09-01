package greedyAlgorithm;

import java.util.*;

public class MaxMeetingsInOneRoom {

    static class Meeting {
        int start;
        int end;
        int index;

        public Meeting(int start, int end, int index) {
            this.start = start;
            this.end = end;
            this.index = index;
        }
    }

    public static List<Integer> maxMeetings(int[] start, int[] end) {
        int n = start.length;
        List<Meeting> meetings = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            meetings.add(new Meeting(start[i], end[i], i + 1)); // store 1-based index
        }

        // Sort by end time (greedy)
        meetings.sort((a, b) -> {
            if (a.end == b.end) return a.index - b.index; // tie-breaker by index
            return a.end - b.end;
        });

        List<Integer> selected = new ArrayList<>();
        int lastEndTime = 0;

        for (Meeting meeting : meetings) {
            if (meeting.start > lastEndTime) {
                selected.add(meeting.index);
                lastEndTime = meeting.end;
            }
        }

        return selected;
    }

    public static void main(String[] args) {
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end =   {2, 4, 6, 7, 9, 9};

        List<Integer> result = maxMeetings(start, end);

        System.out.println("Meetings that can be scheduled: " + result);
    }
}

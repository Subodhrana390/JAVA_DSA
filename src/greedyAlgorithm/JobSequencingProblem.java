package greedyAlgorithm;

import java.util.Arrays;
import java.util.Vector;

public class JobSequencingProblem {

    static class Job {
        int id;
        int dead;
        int profit;

        public Job(int id, int dead, int profit) {
            this.id = id;
            this.dead = dead;
            this.profit = profit;
        }
    }

    Vector<Integer> JobScheduling(Job[] arr, int n) {
        // Sort jobs by descending profit
        Arrays.sort(arr, (o1, o2) -> o2.profit - o1.profit);

        // Find max deadline to size the slot array
        int maxDeadline = 0;
        for (int i = 0; i < n; i++) {
            maxDeadline = Math.max(maxDeadline, arr[i].dead);
        }

        // Create slot array: each index represents a time slot
        boolean[] slot = new boolean[maxDeadline + 1]; // index 0 unused
        int countJobs = 0, totalProfit = 0;

        for (int i = 0; i < n; i++) {
            // Find a free slot for this job, starting from its deadline
            for (int j = arr[i].dead; j > 0; j--) {
                if (!slot[j]) {
                    slot[j] = true;
                    countJobs++;
                    totalProfit += arr[i].profit;
                    break;
                }
            }
        }

        Vector<Integer> result = new Vector<>();
        result.add(countJobs);
        result.add(totalProfit);
        return result;
    }

    public static void main(String[] args) {
        JobSequencingProblem obj = new JobSequencingProblem();

        Job[] jobs = {
                new Job(1, 4, 20),
                new Job(2, 1, 10),
                new Job(3, 1, 40),
                new Job(4, 1, 30)
        };

        Vector<Integer> result = obj.JobScheduling(jobs, jobs.length);
        System.out.println("Max number of jobs done: " + result.get(0));
        System.out.println("Total profit: " + result.get(1));
    }
}

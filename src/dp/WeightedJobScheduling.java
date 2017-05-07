package dp;

import java.util.*;

public class WeightedJobScheduling {
	public static void main(String[] args) {
		WeightedJobScheduling w = new WeightedJobScheduling();
		Job j1 = new Job(1, 2, 50);
		Job j2 = new Job(3, 5, 20);
		Job j3 = new Job(2, 100, 200);
		Job j4 = new Job(6, 19, 100);
		ArrayList <Job> a = new ArrayList<Job>();
		a.add(j1);
		a.add(j2);
		a.add(j3);
		a.add(j4);
		//System.out.println("Array list size " + a.size());
		w.jobSchedule(a);
	}
	
	private void jobSchedule(ArrayList<Job> a){
		// Sort the jobs based on end time
		Collections.sort(a, new JobComparator());
		System.out.println("Profit for Job scheduled using recursive implementation " + jobScheduleRecursive(a, a.size()));
		System.out.println("Profit for Job scheduled using DP implementation " + jobScheduleDP(a, a.size()));
		System.out.println("Profit for Job scheduled using nlogn implementation " + jobScheduleOptimal(a, a.size()));
	}
	
	
	private int jobScheduleRecursive(ArrayList<Job> a, int n){
		if (n == 1)
			return a.get(n-1).profit;
		int inclProfit = a.get(n-1).profit;
		int k = latestNonConflictingJob(a, n);
		if (k != -1)
			inclProfit += jobScheduleRecursive(a, k+1);
		int exclProfit = jobScheduleRecursive(a, n-1);
		
		return Math.max(inclProfit, exclProfit);
		
	}
	
	private int jobScheduleDP(ArrayList<Job> a, int n){
		int table[] = new int[n];
		table[0] = a.get(0).profit;
		
		for (int i=1; i<n ; i++){
			int inclProfit = a.get(i).profit;
			int k = latestNonConflictingJob(a, i);
			if (k != -1)
				inclProfit += table[k];
			table[i] = Math.max(inclProfit, table[i-1]);
		}
		
		return table[n-1];
	}
	
	private int jobScheduleOptimal(ArrayList<Job> a, int n){
		int table[] = new int[n];
		table[0] = a.get(0).profit;
		
		for (int i=1; i<n ; i++){	
			int inclProfit = a.get(i).profit;
			int k = binaryLatestNonConflictingJob(a, i);
			if (k != -1)
				inclProfit += table[k];
			table[i] = Math.max(inclProfit, table[i-1]);
		}
		
		return table[n-1];
	}
	
	private int latestNonConflictingJob(ArrayList<Job> a, int i){
		for(int j=i-1; j>=0; j--){
			if (a.get(j).endTime <= a.get(i-1).startTime)
				return j;
		}
		return -1;
	}
	
	private int binaryLatestNonConflictingJob(ArrayList<Job> a, int i){
		int lo =0;
		int hi = i-1;
		
		while (lo <= hi){
			int mid = (lo + hi)/2;
			if (a.get(mid).endTime <= a.get(hi).startTime){
				if (a.get(mid+1).endTime <= a.get(hi).startTime)
					lo = mid +1;
				else
					return mid;
			}
			else 
				hi = mid - 1;
		}
		return -1;
	}
}


class Job {
	int startTime, endTime, profit;
	
	public Job (int startTime, int endTime, int profit){
		this.startTime = startTime;
		this.endTime = endTime;
		this.profit = profit;
	}
}

class JobComparator implements Comparator<Job>{
	public int compare (Job a, Job b){
		return a.endTime < b.endTime ? -1 : a.endTime == b.endTime ? 0 : 1;
	}
}



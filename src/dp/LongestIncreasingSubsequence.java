// Given a list of elements find the longest increasing subsequence of the list
// Given A, find LIS[0..m] such that m is maximum for all LIS[i] < LIS[i+1]

package dp;

public class LongestIncreasingSubsequence {
	public static void main(String[] args) {
		//int A[] = {2,5,3,7,11,8};
		int A[] = {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15};
		LongestIncreasingSubsequence l = new LongestIncreasingSubsequence();
		System.out.println("longest Increasing subsequence of array A");
		System.out.println("Using DP is  " + l.findUsingDP(A));
		System.out.println("Using optimized algo " + l.findUsingOptimizedAlgo(A));
		
	}
	
	private int findUsingDP(int []A){
		// Using DP find a recursive solution O(n^2) time complexity
		// LIS[i] = Max(LIS[j] + 1, forall j belongs to (0, i-1), such that A[i] > A[j] && 1<j<i
		// LIS[i] = 1 if there is no such j
		
		// Initializing LIS[i] forall i to 1
		
		int LIS[] = new int[A.length];
		for (int i=0; i<LIS.length; i++)
			LIS[i] = 1;
		
		// Using DP, bottom up approach 
		for (int i=1; i<A.length; i++){
			for (int j=0; j<i; j++){
				if (A[i] > A[j] && LIS[i] < LIS[j]+1)
					LIS[i] = LIS[j] + 1;
			}
		}
		
		int max = 0;
		for (int i=0; i<A.length; i++){
			if (LIS[i] > max)
				max = LIS[i];
		}
		
		return max;
	}
	
	
	//[Geeks for geeks nlogn](http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/)
	private int findUsingOptimizedAlgo(int []A){
		// Using optimized algo O(NlogN)
		// If A[i] is the current element to be considered
		// Add A[i] to the active lists of sequences based on the following three conditions
		// 1. If A[i] is the smallest among all end candidates of active lists, create a new active list with A[i]
		// 2. If A[i] is the largest among all end candidates of active lists, clone the largest active list and append A[i] to it
		// 3. If A[i] is in between, clone the active list with end candidate less than A[i] and append A[i] to it. Discard any active lists of the same length
		
		int size = A.length;
		int len;
		int []activeListsTable = new int[size];
		
		// Initialize 
		activeListsTable[0] = A[0];
		len = 1;
		
		for (int i=1; i<size; i++){
			if (A[i] < activeListsTable[0])
				activeListsTable[0] = A[i];
			else if (A[i] > activeListsTable[len-1])
				activeListsTable[len++] = A[i];
			else{
				//Get the index of the element which is less than A[i] but not the largest 
				int index = getMidIndex(activeListsTable, 0, len-1, A[i]);
				activeListsTable[index] = A[i];
			}
		}
		System.out.println("Longest Increasing subsequence is ");
		for (int i: activeListsTable)
			System.out.print(i + " ");
		return len;
		
	}
	
	private int getMidIndex(int[]table, int l, int r, int ele){
		while(l < r-1){
			int m = (r+l)/2;
			if (table[m] >= ele)
				r = m;
			else
				l = m;			
		}
		return r;
	}
}

// Given a value n, and infinite number of coins available with values s= {s1, s2, s3, .... sm},
// what is the total count(number of ways) in which we can make change to n.

// Using Dynamic programming
// Optimal Substructure property 
//	count = count(s[], m-1, n) + count(s[], m, n-sm)


// Input format
// n m
// s[] of int
package dp;
import java.util.*;

public class CoinChange {
	public static void main(String[] args) {
		Scanner sin = new Scanner(System.in);
		System.out.println("Enter the amount: ");
		int n = sin.nextInt();
		System.out.println("Enter the number of coin types: ");
		int m = sin.nextInt();
		System.out.println("Enter the set of coins: ");
		int s[] = new int[m];
		for (int i=0; i<m; i++)
			s[i] = sin.nextInt();
		sin.close();
		CoinChange c = new CoinChange();
		int sol = c.countCoinChange(n, m, s);
		System.out.println("The number of ways of exchanging the coin is " + sol);
	}
	
	public int countCoinChange(int n, int m, int[]s){
		int solution[][] = new int[m+1][n+1];
		// Set base cases
		// Base case number 1: When the coin set is 0, 0 ways to solution
		for (int i=0; i<=n; i++)
			solution[0][i] = 0;
			
		// Base case number 2: When the amount is 0, the solution is 1
		for (int j=0; j<=m; j++)
			solution[j][0] = 1;
		
		// Apply dynamic programming to solve the rest
		// The solution can be obtained by including and excluding the coin value for every coin
		// When include the coin, subtract it from the total amount
		// When exclue the coin, find the solution for the same amount
		
		for (int i=1; i<=m; i++){
			for (int j=1; j<=n; j++){
				if(s[i-1] <= j){
					solution[i][j] = solution[i-1][j] + solution[i][j-s[i-1]];
				}
				else
					solution[i][j] = solution[i-1][j];
				
			}
		}
		
		return solution[m][n];	
	}
}

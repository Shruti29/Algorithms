package dataStructures;

// BFS, DFS
import java.util.*;
import java.io.*;

public class GraphTraversal {
	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 4);
		g.addEdge(2, 5);
		
		System.out.println("BFS traversal starting from vertex 1");
		g.bfs(1);
		
		System.out.println("DFS traversal starting from vertex 1");
		g.dfs(1);
	}
	
}

class Graph {
	private int V; // Number of vertices
	private LinkedList<Integer> adj[];
	
	Graph(int v){
		V = v;
		adj = new LinkedList[v];
		
		for (int i=0; i<v ;i++)
			adj[i] = new LinkedList();
	}
	
	void addEdge(int v, int w){
		adj[v].add(w);
	}
	
	void bfs(int v){
		boolean visited[] = new boolean[V];
		LinkedList <Integer> queue = new LinkedList<Integer>();
		
		visited[v] = true;
		queue.add(v);
		
		while (queue.size() != 0){
			v = queue.poll();
			System.out.println(v + " ");
			
			Iterator <Integer> i = adj[v].listIterator(); 
			while (i.hasNext()){
				int n = i.next();
				if (!visited[n])
				{
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}
	
	void dfs(int v){
		boolean visited[] = new boolean[V];
		for (int i=0; i<v; i++){
			if (!visited[i])
				dfsUtil(i, visited);
		}
	}
	
	void dfsUtil(int v, boolean visited[]){
		visited[v] = true;
		System.out.println(v + " ");
		
		Iterator<Integer> i = adj[v].listIterator();
		while (i.hasNext()){
			int n = i.next();
			if (!visited[n])
				dfsUtil(n, visited);
			
		}
		
	}
	
}
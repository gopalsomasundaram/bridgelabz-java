/**
 * This program performs BFS traversal of an undirected graph using a queue.
 */

package datastructures.treesandgraphs.graphs;

import java.util.*;

public class BFSTraversal {

    int V;
    List<List<Integer>> adj;

    BFSTraversal(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
    }

    void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    void bfs(int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new LinkedList<>();

        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");

            for (int nei : adj.get(node)) {
                if (!visited[nei]) {
                    visited[nei] = true;
                    q.add(nei);
                }
            }
        }
    }

    public static void main(String[] args) {
        BFSTraversal g = new BFSTraversal(5);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(1,3);
        g.addEdge(2,4);

        g.bfs(0);
    }
}

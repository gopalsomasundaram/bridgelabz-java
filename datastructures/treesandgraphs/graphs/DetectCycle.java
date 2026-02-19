/**
 * Detects cycle in an undirected graph using DFS with parent tracking.
 */

package datastructures.treesandgraphs.graphs;

import java.util.*;

public class DetectCycle {

    int V;
    List<List<Integer>> adj;

    DetectCycle(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
    }

    void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    boolean dfs(int node, boolean[] visited, int parent) {
        visited[node] = true;

        for (int nei : adj.get(node)) {
            if (!visited[nei]) {
                if (dfs(nei, visited, node))
                    return true;
            } else if (nei != parent)
                return true;
        }
        return false;
    }

    boolean hasCycle() {
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++)
            if (!visited[i] && dfs(i, visited, -1))
                return true;

        return false;
    }

    public static void main(String[] args) {
        DetectCycle g = new DetectCycle(3);
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(2,0);

        System.out.println("Has Cycle = " + g.hasCycle());
    }
}

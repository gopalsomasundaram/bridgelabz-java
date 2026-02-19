/**
 * This program counts connected components using DFS traversal.
 */

package datastructures.treesandgraphs.graphs;

import java.util.*;

public class ConnectedComponents {

    int V;
    List<List<Integer>> adj;

    ConnectedComponents(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
    }

    void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    void dfs(int node, boolean[] visited) {
        visited[node] = true;

        for (int nei : adj.get(node))
            if (!visited[nei])
                dfs(nei, visited);
    }

    int countComponents() {
        boolean[] visited = new boolean[V];
        int count = 0;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, visited);
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        ConnectedComponents g = new ConnectedComponents(5);
        g.addEdge(0,1);
        g.addEdge(2,3);

        System.out.println("Components = " + g.countComponents());
    }
}

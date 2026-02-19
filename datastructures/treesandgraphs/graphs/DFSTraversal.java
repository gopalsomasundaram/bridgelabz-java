/**
 * This program performs DFS traversal of a graph using recursion.
 */

package datastructures.treesandgraphs.graphs;

import java.util.*;

public class DFSTraversal {

    int V;
    List<List<Integer>> adj;

    DFSTraversal(int V) {
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
        System.out.print(node + " ");

        for (int nei : adj.get(node))
            if (!visited[nei])
                dfs(nei, visited);
    }

    public static void main(String[] args) {
        DFSTraversal g = new DFSTraversal(5);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(1,3);
        g.addEdge(2,4);

        g.dfs(0, new boolean[g.V]);
    }
}


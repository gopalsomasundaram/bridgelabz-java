/**
 * Finds shortest path from a source node in a weighted graph
 * using Dijkstra's algorithm with a priority queue.
 */

package datastructures.treesandgraphs.graphs;

import java.util.*;

public class Dijkstra {

    int V;
    List<List<int[]>> adj;

    Dijkstra(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
    }

    void addEdge(int u, int v, int w) {
        adj.get(u).add(new int[]{v,w});
        adj.get(v).add(new int[]{u,w});
    }

    void dijkstra(int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);

        dist[src] = 0;
        pq.add(new int[]{src,0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];

            for (int[] nei : adj.get(node)) {
                int v = nei[0], w = nei[1];

                if (dist[node] + w < dist[v]) {
                    dist[v] = dist[node] + w;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        System.out.println(Arrays.toString(dist));
    }

    public static void main(String[] args) {
        Dijkstra g = new Dijkstra(5);

        g.addEdge(0,1,4);
        g.addEdge(0,2,1);
        g.addEdge(2,1,2);
        g.addEdge(1,3,1);
        g.addEdge(3,4,3);

        g.dijkstra(0);
    }
}

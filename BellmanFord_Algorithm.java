import java.util.*;

public class BellmanFord_Algorithm {
    static class Edge{
        int dest;
        int weight;
        Edge(int dest,int weight){
            this.dest = dest;
            this.weight = weight;
        }
    }

    static LinkedList<Edge>[] list;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int nodes = input.nextInt();
        list = new LinkedList[nodes];
        for (int i = 0; i < nodes; i++) {
            list[i] = new LinkedList<Edge>();
        }
        while (true) {
            int src = input.nextInt();
            int dest = input.nextInt();
            int weight = input.nextInt();
            if (src == -1 && dest == -1 && weight == -1) {
                break;
            }
            list[src].add(new Edge(dest, weight));
        }

        int[] distance = new int[nodes];
        int[] parent = new int[nodes];
        for (int i = 0; i < nodes; i++) {
            distance[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }

        int start = 0; //  source
        distance[start] = 0;

        for (int i = 1; i < nodes; i++) // v-1 times
        {
            for (int j = 0; j < list.length; j++) {
                for (Edge e : list[j]) {
                    if (distance[e.dest] > distance[j] + e.weight) {
                        distance[e.dest] = distance[j] + e.weight;
                        parent[e.dest] = j;
                    }
                }
            }
        }

        // Check if there is negative cycle
        boolean con = false;
        for (int j = 0; j < list.length; j++) {
            for (Edge e : list[j]) {
                if (distance[e.dest] > distance[j] + e.weight) {
                    con = true;
                    break;
                }
            }
        }
        if (con) {
            System.out.println("Negative weight cycle detected");
        } else {
            System.out.println("Node              Distance               Parent");
            for (int i = 0; i < nodes; i++) {
                System.out.println(i + "                   " + distance[i] + "                     " + parent[i]);
            }
        }
    }
}

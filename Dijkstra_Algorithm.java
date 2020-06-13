import java.util.*;
/*
Sample Test Case

6
0 1 5
1 2 2
2 3 3
0 3 9
0 4 2
4 5 3
3 5 2
-1 -1 -1
0
 */

public class Dijkstra_Algorithm {
    static class Edge{
        int dest;
        int cost;
        Edge(int dest,int cost){
            this.dest = dest;
            this.cost = cost;
        }
    }

    static class Node implements Comparable<Node>{
        int id;
        int cost;

        public int compareTo(Node other){
            if(this.cost == other.cost){
                return 0;
            }
            else if(this.cost > other.cost){
                return 1;
            }
            else{
                return -1;
            }
        }
    }

    static LinkedList<Edge>[] list;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int nodes = input.nextInt();
        list = new LinkedList[nodes];
        for(int i = 0;i < nodes;i++){
            list[i] = new LinkedList<Edge>();
        }
        while (true){
            int src = input.nextInt();
            int dest = input.nextInt();
            int cost = input.nextInt();
            if(src == -1 && dest == -1 && cost == -1){
                break;
            }
            list[src].add(new Edge(dest,cost));
            list[dest].add(new Edge(src,cost));
        }

        int start = input.nextInt();

        Node[] node_Distance = new Node[nodes];
        int[] parent = new int[nodes];
        boolean[] visited = new boolean[nodes];
        for(int i = 0;i < nodes;i++){
            node_Distance[i] = new Node();
            node_Distance[i].id = i;
            node_Distance[i].cost = Integer.MAX_VALUE;
            parent[i] = -1;
        }

        TreeSet<Node> queue = new TreeSet<Node>();
        node_Distance[start].cost = 0;

        for(int i = 0;i < nodes;i++){
            queue.add(node_Distance[i]);
        }

        while (!queue.isEmpty()){
            Node curr = queue.pollFirst();
            visited[curr.id] = true;
            System.out.println("For .." + curr.id);
            for(Edge e : list[curr.id]){
                if(!visited[e.dest]){
                    if(node_Distance[e.dest].cost > (curr.cost + e.cost)){
                        queue.remove(node_Distance[e.dest]);
                        node_Distance[e.dest].cost = node_Distance[curr.id].cost + e.cost;
                        System.out.println("Updated.." + e.dest + "   " + "cost " + node_Distance[e.dest].cost);
                        queue.add(node_Distance[e.dest]);
                        parent[e.dest] = curr.id;
                    }
                }
            }
        }

        System.out.println();

        System.out.println("Node                Distance");
        for(int i = 0;i < nodes;i++){
            System.out.println(node_Distance[i].id + "                        " + node_Distance[i].cost);
        }

    }
}

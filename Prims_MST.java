import java.util.LinkedList;
import java.util.*;

/*
9
0 1 4
0 7 8
1 2 8
1 7 11
2 3 7
2 8 2
2 5 4
3 4 9
3 5 14
4 5 10
5 6 2
6 7 1
6 8 6
7 8 7
-1 -1 -1
 */
public class Prims_MST {
    static class Edge{
        int dest;
        int cost;
        Edge(int dest,int cost){
            this.dest = dest;
            this.cost = cost;
        }
    }

    static class Node{
        int id;
        int cost;
    }

    static class comparator implements Comparator<Node>{
        public int compare(Node n1,Node n2){
            if(n1.cost == n2.cost){
                return 0;
            }
            else if(n1.cost > n2.cost){
                return 1;
            }
            else{
                return -1;
            }
        }
    }

    static LinkedList<Edge> list[];
    public static void main(String[] args) {

        // create graph starts

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

        // create graph ends

        boolean[] mst_set = new boolean[nodes]; // contains nodes that are already included in mst
        int[] parent = new int[nodes]; // contains the result edges
        TreeSet<Node> queue = new TreeSet<Node>(new comparator()); // pq based on cost. Treeset is used cause in pq, remove operation is o(n)
        Node[] k = new Node[nodes]; // contains info of node id and its cost value
        for(int i = 0;i < nodes;i++){
            k[i] = new Node();
        }
        for(int i = 0;i < nodes;i++){
            parent[i] = -1;
            k[i].id = i;
            k[i].cost = Integer.MAX_VALUE;
        }
        k[0].id = 0;   // we could've taken any node,here we took node 0 as starting point
        k[0].cost = 0;
        for(int i = 0;i < nodes;i++){
            queue.add(k[i]);
        }

        while (!queue.isEmpty()){
            Node top = queue.pollFirst();
            mst_set[top.id] = true;
            for(Edge e : list[top.id]){
                if(mst_set[e.dest] == false){
                    if(k[e.dest].cost > e.cost){
                        queue.remove(k[e.dest]);
                        k[e.dest].cost = e.cost;
                        queue.add(k[e.dest]);
                        parent[k[e.dest].id] = top.id;
                    }
                }
            }
        }

        for(int i = 1;i < nodes;i++){
            System.out.println(parent[i] + " " + i);
        }
    }

}

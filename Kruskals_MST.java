import com.sun.org.apache.regexp.internal.RE;
/*
Test Case :
5
0 1 3
1 3 7
3 2 2
0 4 5
2 4 1
-1 -1 -1
 */
import java.util.*;
import java.util.ArrayList;
public class Kruskals_MST {
    static class Edge implements Comparable<Edge>{
        int src;
        int dest;
        int cost;

        Edge(int src,int dest,int cost){
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }

        public int compareTo(Edge other){
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

    static int find(int[] parent,int x){
        if(parent[x] == x){
            return x;
        }
        else {
            parent[x] = find(parent,parent[x]);
            return parent[x];
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of nodes : ");
        int nodes = input.nextInt();
        int[] parent = new int[nodes];
        int[] rank = new int[nodes];

        for(int i = 0;i < nodes;i++){
            parent[i] = i;
            rank[i] = 0;
        }


        ArrayList<Edge> edges = new ArrayList<Edge>();
        while (true){
            int src = input.nextInt();
            int dest = input.nextInt();
            int cost = input.nextInt();
            if(src == -1 && dest == -1 && cost == -1){
                break;
            }
            edges.add(new Edge(src,dest,cost));
        }
        Collections.sort(edges);
        ArrayList<Edge> mst = new ArrayList<Edge>();
        for(Edge e : edges){
            int u = e.src;
            int v = e.dest;

            int parent_u = find(parent,u);
            int parent_v = find(parent,v);

            if(parent_u == parent_v){
                continue;
            }
            else{
                if(rank[parent_u] > rank[parent_v]){
                    parent[parent_v] = parent_u;
                }
                else if(rank[parent_u] < rank[parent_v]){
                    parent[parent_u] = parent_v;
                }
                else{
                    parent[parent_v] = parent_u;
                    rank[parent_u]++;
                }
                mst.add(e);
            }
        }

        for(Edge e : mst){
            System.out.println(e.src + " " + e.dest + " " + e.cost);
        }
    }
}

import java.util.*;
public class Disjoint_Set_Union {
    static int[] parent;
    static int[] rank;
    static int find(int x){
        if(parent[x] == x){
            return x;
        }
        else{
            parent[x] = find(parent[x]);  // path compression
            return parent[x];
        }
    }

    static void union(int u,int v){
        int rootX = find(u);
        int rootY = find(v);
        if(rootX == rootY){
            return;
        }
        else{
            if(rank[rootX] < rank[rootY]){
                parent[rootX] = rootY;
            }
            else if(rank[rootY] < rank[rootX]){
                parent[rootY] = rootX;
            }
            else{
                parent[rootX] = rootY;
                rank[rootY]++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of nodes : ");
        int n = input.nextInt();
        parent = new int[n+1];
        rank = new int[n+1];


        for(int i = 1;i <= n;i++){
            parent[i] = i;
            rank[i] = 0;
        }
        System.out.println("Enter the number of edges : ");
        int e = input.nextInt();
        for(int i = 0;i < e;i++){
            int src = input.nextInt();
            int dest = input.nextInt();
            union(src,dest);
        }
        for(int i = 1;i <= n;i++){
            System.out.println(i + "    " + find(parent[i]));
        }
    }
}

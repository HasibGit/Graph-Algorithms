import java.util.*;
/*
Test Case
5
0 1
1 2
1 3
3 4
-1 -1
 */


public class Depth_First_Search {
    static LinkedList<Integer>[] list;

    static void dfs(int node,boolean[] visited){
        if(!visited[node]){
            visited[node] = true;
            System.out.print(node + " ");
        }
        for(int i : list[node]){
            if(!visited[i]){
                dfs(i,visited);
            }
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int nodes = input.nextInt();
        list = new LinkedList[nodes];
        for(int i = 0;i < nodes;i++){
            list[i] = new LinkedList<Integer>();
        }

        while (true){
            int src = input.nextInt();
            int dest = input.nextInt();
            if(src == -1 && dest == -1){
                break;
            }
            list[src].add(dest);
        }

        boolean[] visited = new boolean[nodes];
        dfs(0,visited);
    }
}

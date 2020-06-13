/*
8
0 1
0 2
1 2
2 3
3 4
4 5
4 6
5 6
6 7
-1 -1
 */


import java.util.*;
public class ArticulationPoints {
    static LinkedList<Integer>[] list;
    static int time = 0;

    static void dfs(int curr,boolean[] visited,int[] discoveryTime,int[] lowTime,int[] parent,ArrayList<Integer> articulationPoints){
       visited[curr] = true;
       discoveryTime[curr] = time;
       lowTime[curr] = time;
       time++;

       boolean ap = false;
       int childCount = 0;
       for(int adj : list[curr]){
           if(parent[curr] == adj){
               continue;
           }

           if(visited[adj] == false){
               parent[adj] = curr;
               childCount++;
               dfs(adj,visited,discoveryTime,lowTime,parent,articulationPoints);
               if(discoveryTime[curr] <= lowTime[adj]){
                   ap = true;
               }
               else{
                   lowTime[curr] = Math.min(lowTime[curr],lowTime[adj]);
               }
           }
           else{
               lowTime[curr] = Math.min(lowTime[curr],lowTime[adj]);
           }
       }

       if( (parent[curr] == -1 && childCount >= 2) || (parent[curr] != -1 && ap == true)){
           articulationPoints.add(curr);
       }
    }



    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int nodes = input.nextInt();
        list = new LinkedList[nodes];
        boolean[] visited = new boolean[nodes];
        int[] discoveryTime = new int[nodes];
        int[] lowTime = new int[nodes];
        int[] parent = new int[nodes];
        for(int i = 0; i < nodes;i++){
            list[i] = new LinkedList<Integer>();
            parent[i] = -1;
        }

        while (true){
            int src = input.nextInt();
            int dest = input.nextInt();
            if(src == -1 && dest == -1){
                break;
            }
            list[src].add(dest);
            list[dest].add(src);
        }

        int start = 3;
        ArrayList<Integer> articulationPoints = new ArrayList<Integer>();
        dfs(start,visited,discoveryTime,lowTime,parent,articulationPoints);

        System.out.print("Articulation Points are : ");

        for(int i : articulationPoints){
            System.out.print(i + " ");
        }


    }
}

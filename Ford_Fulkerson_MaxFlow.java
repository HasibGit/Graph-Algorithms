import java.util.*;

/*
Test 1 :
7
0 1 3
0 3 3
1 2 4
2 0 3
2 3 1
2 4 2
3 4 2
3 5 6
4 1 1
4 6 1
5 6 9
-1 -1 -1
0 6
 */
public class Ford_Fulkerson_MaxFlow {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of nodes : ");
       int nodes = input.nextInt();

       int[][] capacity = new int[nodes][nodes];

        while (true){
          int src = input.nextInt();
          int dest = input.nextInt();
          int cost = input.nextInt();
          if(src == -1 && dest == -1 && cost == -1){
              break;
          }
          capacity[src][dest] = cost;
        }

        int[][] residualCapacity = new int[nodes][nodes];
        for(int i = 0;i < nodes;i++){
            for(int j = 0;j < nodes;j++){
                residualCapacity[i][j] = capacity[i][j];
            }
        }

        int src = input.nextInt();
        int sink = input.nextInt();

        ArrayList<ArrayList<Integer>> augmentedPaths = new ArrayList<>();

        int maxFlow = 0;

        while (true){

            boolean pathFound = false;

            LinkedList<Integer> queue = new LinkedList<Integer>();

            int[] parent = new int[nodes];

            boolean[] visited = new boolean[nodes];

            queue.add(src);
            visited[src] = true;

            while (!queue.isEmpty()){
                int u = queue.poll();
                boolean con = false;
                for(int v = 0;v < nodes;v++){
                    if(!visited[v] && residualCapacity[u][v] > 0){
                        visited[v] = true;
                        queue.add(v);
                        parent[v] = u;
                        if(v == sink){
                            con = true;
                            break;
                        }
                    }
                }
                if(con){
                    pathFound = true;
                    break;
                }
            }
            if(!pathFound){
                break;
            }
            else{
             int flow = Integer.MAX_VALUE;
             ArrayList<Integer> augmentedPath = new ArrayList<Integer>();
             int v = sink;
             augmentedPath.add(v);
             while (true){
                 int u = parent[v];
                 if(residualCapacity[u][v] < flow){
                     flow = residualCapacity[u][v];
                 }

                 augmentedPath.add(u);
                 if(u == src){
                     break;
                 }
                 v = u;
             }
             Collections.reverse(augmentedPath);
             augmentedPaths.add(augmentedPath);
             maxFlow += flow;

             v = sink;
             while (true){
                 int u = parent[v];
                 residualCapacity[u][v] -= flow;
                 residualCapacity[v][u] += flow;
                 if(u == src){
                     break;
                 }
                 v = u;
             }
            }
        }

        System.out.println("MaxFlow : " + maxFlow);
        System.out.println("Augmented Paths : ");
        for(ArrayList<Integer> list : augmentedPaths){
            for(int i : list){
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }
}

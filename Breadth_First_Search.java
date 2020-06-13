/*
8
0 1
0 5
1 2
1 3
5 6
3 4
4 7
6 8
 */


import java.util.*;
public class Breadth_First_Search {

    static LinkedList<Integer> list[];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of nodes : ");
        int n = input.nextInt();
        list = new LinkedList[n];
        for(int i = 0;i < n;i++){
            list[i] = new LinkedList<Integer>();
        }
        System.out.println("Enter the number of edges : ");
        int edges = input.nextInt();
        int[] parent = new int[n];
        Arrays.fill(parent,-1);
        for(int i = 0;i < edges;i++){
            int u = input.nextInt();
            int v = input.nextInt();
            list[u].add(v);
            list[v].add(u);
        }
        System.out.println("Enter destination node : ");
        int dest = input.nextInt();

        int start = 0;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[n];
        queue.add(start);
        visited[start] = true;
        boolean found = false;
        if(start == dest){
            System.out.println("path : 0");
            return;
        }

        else{
            while (!queue.isEmpty())
            {
                int current = queue.poll();
                for(int i : list[current]){
                    if(!visited[i]){
                        visited[i] = true;
                        queue.add(i);
                        parent[i] = current;
                    }
                    if(i == dest){
                        found = true;
                        break;
                    }

                }

            }
            if(found){
                System.out.print("Path :");
                int t = dest;
                ArrayList<Integer> path = new ArrayList<Integer>();
                while (t >= 0){
                    path.add(t);
                    if(t == 0){
                        break;
                    }
                    t = parent[t];
                }
                Collections.reverse(path);
                for(int i : path){
                    System.out.print(i + " ");
                }
                System.out.println();
            }
            else{
                System.out.println("Not Found");
            }
        }

        // we can also find level of a node by having a level array

    }
}

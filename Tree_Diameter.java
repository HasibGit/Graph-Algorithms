/*
12
0 1
0 2
1 4
1 3
3 7
7 8
2 5
2 6
5 11
6 9
9 10
-1 -1
 */


import java.util.*;
public class Tree_Diameter {
    static LinkedList<Integer>[] list;


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
            list[dest].add(src);
        }

        int[] level = new int[nodes];

        boolean[] visited = new boolean[nodes];
        LinkedList<Integer> queue = new LinkedList<Integer>();

        int start = 0;
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()){
            int curr = queue.poll();
            for(int i : list[curr]){
                if(!visited[i]){
                    visited[i] = true;
                    level[i] = level[curr] + 1;
                    queue.add(i);
                }
            }
        }

        int furthest = -1;
        int depth = Integer.MIN_VALUE;

        for(int i = 0;i < nodes;i++){
            if(level[i] > depth){
                depth = level[i];
                furthest = i;
            }
        }


        int[] level2 = new int[nodes];
        boolean[] visited2 = new boolean[nodes];
        LinkedList<Integer> queue2 = new LinkedList<Integer>();

        level2[furthest] = 0;
        visited2[furthest] = true;
        queue2.add(furthest);

        while (!queue2.isEmpty()){
            int curr = queue2.poll();
            for(int i : list[curr]){
                if(!visited2[i]){
                    visited2[i] = true;
                    level2[i] = level2[curr] + 1;
                    queue2.add(i);
                }
            }
        }

        int furthest2 = -1;
        int depth2 = Integer.MIN_VALUE;

        for(int i = 0;i < nodes;i++){
            if(level2[i] > depth2){
                depth2 = level2[i];
                furthest2 = i;
            }
        }

        System.out.println("Diameter of the tree : " + depth2);

    }
}

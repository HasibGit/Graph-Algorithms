import java.util.*;

/*
Test Case :

7
1 2
1 3
4 1
4 5
5 6
7 5
-1 -1
 */


public class Topolagial_Sort {
    static LinkedList<Integer>[] list;

    static void topSort(int node,boolean[] visited,Stack<Integer> stack){
        visited[node] = true;
        for(int i : list[node]){
            if(!visited[i]){
                topSort(i,visited,stack);
            }
        }
        stack.add(node);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int nodes = input.nextInt();
        list = new LinkedList[nodes+1];
        for(int i = 1;i <= nodes;i++){
            list[i] = new LinkedList<Integer>();
        }
        while (true){
            int src = input.nextInt();
            int dest = input.nextInt();
            if(src == -1 && dest == -1){
                break;
            }
            list[src].add(dest); // directed graph
        }
        boolean[] visited = new boolean[nodes+1];
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 1;i <= nodes;i++){
            if(!visited[i]){
               topSort(i,visited,stack);
            }
        }

        // Topologically sorted order

        while (!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }


    }
}

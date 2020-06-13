import java.util.*;
public class Strongly_Connected_Components {

    static LinkedList<Integer>[] list;
    static LinkedList<Integer>[] reversedList;

    static void dfs(int curr,boolean[] visited,Stack<Integer> stack){
        visited[curr] = true;
        for(int adj : list[curr]){
            if(!visited[adj]){
                dfs(adj,visited,stack);
            }
        }
        stack.add(curr);
    }


    static void dfs2(int curr,boolean[] visited,StringBuilder sb){
        visited[curr] = true;
        sb.append(Integer.toString(curr));
        for(int adj : reversedList[curr]){
            if(!visited[adj]){
                dfs2(adj,visited,sb);
            }
        }
    }





    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int nodes = input.nextInt();

        list = new LinkedList[nodes];
        reversedList = new LinkedList[nodes];

        for(int i = 0;i < nodes;i++){
            list[i] = new LinkedList<Integer>();
            reversedList[i] = new LinkedList<Integer>();
        }

        while (true){
            int src = input.nextInt();
            int dest = input.nextInt();
            if(src == -1 && dest == -1)
                break;
            list[src].add(dest);
            reversedList[dest].add(src);
        }

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[nodes];
        for(int i = 0;i < nodes;i++){
            if(!visited[i]){
                dfs(i,visited,stack);
            }
        }
        boolean[] visited2 = new boolean[nodes];

        ArrayList<String> scc = new ArrayList<String>();


        while (!stack.isEmpty()){
            int top = stack.pop();
            StringBuilder sb = new StringBuilder();
            if(!visited2[top]){
                dfs2(top,visited2,sb);
                scc.add(sb.toString());
            }
        }

        System.out.println("Strongly connected components of this graph are : ");

        for(String s : scc){
            System.out.println(s);
        }
    }
}

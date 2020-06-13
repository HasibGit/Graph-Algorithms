import org.omg.CORBA.INTERNAL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class Floyd_Warshall {

    static void path(int src, int dest, int[][] path){
        Stack<Integer> stack = new Stack<Integer>();
        stack.add(dest);
        int destination = dest;
        while (destination != src){
            destination = path[src][destination];
            stack.add(destination);
        }
        System.out.print("Path : ");
        while (!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
    }


    public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      int nodes = input.nextInt();
      int[][] distance = new int[nodes][nodes];
      int[][] path = new int[nodes][nodes];

      for(int i = 0;i < nodes;i++){
          for(int j = 0;j < nodes;j++){
              distance[i][j] = Integer.MAX_VALUE;
              path[i][j] = 0;
          }
      }

      while (true){
          int src = input.nextInt();
          int dest = input.nextInt();
          int weight = input.nextInt();
          if(src == -1 && dest == -1 && weight == -1){
              break;
          }
          distance[src][dest] = weight;
          distance[dest][src] = weight;
      }

      for(int i = 0;i < nodes;i++){
          for(int j = 0;j < nodes;j++){
              if(distance[i][j] != Integer.MAX_VALUE && i != j){
                  path[i][j] = i;
              }
              else{
                  path[i][j] = -1;
              }
          }
      }

      for(int intermediate = 0; intermediate < nodes; intermediate++){
          for(int src = 0; src < nodes; src++){
              for(int dest = 0; dest < nodes;dest++){
                  if(distance[src][intermediate] == Integer.MAX_VALUE || distance[intermediate][dest] == Integer.MAX_VALUE){
                      continue;
                  }

                  if(distance[src][dest] > (distance[src][intermediate] + distance[intermediate][dest])){


                      distance[src][dest] = distance[src][intermediate] + distance[intermediate][dest];
                      path[src][dest] = path[intermediate][dest];
                  }
              }
          }
      }

      // distance matrix

        for(int i = 0;i < nodes;i++){
            for(int j = 0;j < nodes;j++){
                System.out.print(distance[i][j] + " ");
            }
            System.out.println();
        }

        System.out.printf("\n\n");

      // Path matrix
        for(int i = 0;i < nodes;i++){
            for(int j = 0;j < nodes;j++){
                System.out.print(path[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        // path print
        path(0,1,path);

    }
    static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;

        public FastReader()
        {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt()
        {
            return Integer.parseInt(next());
        }

        long nextLong()
        {
            return Long.parseLong(next());
        }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }
}

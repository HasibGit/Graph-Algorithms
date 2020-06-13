/*
https://www.youtube.com/watch?v=HeeyUZmaZg0
 */


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class LCA_Using_SparseTable {
    static LinkedList<Integer>[] list;
    public static void makeGraph(int vertices){
        list = new LinkedList[vertices];
        for(int i = 0;i < vertices;i++){
            list[i] = new LinkedList<Integer>();
        }
    }
    public static void addEdge(int u,int v){
        list[u].add(v);
        list[v].add(u);
    }
    static ArrayList<Integer> newToOld = new ArrayList<Integer>();
    static int[] firstEncounter;
    static ArrayList<Integer> eularTour = new ArrayList<Integer>();
    public static void dfs_Eular_Tour(int vertex,int parent){
        int newIndex = newToOld.size();
        newToOld.add(vertex);
        try{
            firstEncounter[vertex] = eularTour.size();
        }
        catch (Exception e){
            System.out.println("Index : " + vertex + " " + " value " + eularTour.size());
            e.printStackTrace();
        }
        eularTour.add(newIndex);
        for(int adj : list[vertex]){
            if(adj == parent){
                continue;
            }
            dfs_Eular_Tour(adj,vertex);
            eularTour.add(newIndex);
        }
    }
    static int[][] sparseTable;
    static void buildSparseTable(ArrayList<Integer> list, int n){
        sparseTable = new int[n][(int)(Math.log(n)/Math.log(2)) + 1];
        for(int i = 0;i < n;i++){
            sparseTable[i][0] = i;
        }
        /*
        starting from j,we are looking for next 2^j elements
        2^0 = 1
        so previously amra first column e minimum index i e hobe
        karon minimum search hocce 1 ta element er moddhe
         */
        for(int j = 1;1 << j <= n;j++){
            for(int i = 0;i + (1 << j) - 1 < n;i++){
                if(list.get(sparseTable[i][j-1]) < list.get(sparseTable[i + (1 << j-1)][j-1])){
                    sparseTable[i][j] = sparseTable[i][j-1];
                }
                else{
                    sparseTable[i][j] = sparseTable[i + (1 << j-1)][j-1];
                }
            }
        }
    }
    public static int rangeMinimumQuery(ArrayList<Integer> list,int low, int high) {
        int l = high - low + 1; // number of elements in range
        int k =(int)(Math.log(l) / Math.log(2));
        /*
        first starting from low, we are looking for next 2^k elements,which is alreay there in table

         */
        int num1 = list.get(sparseTable[low][k]);
        int left = l - (1 << k);// jotogulo baki ase oi range er 1st index e move
        int num2 = list.get(sparseTable[low + left][k]);
        if (num1 <= num2) {
            return num1;
        } else {
            return num2;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numOfNodes = 10;
        int numOfEdges = numOfNodes -1;
        makeGraph(numOfNodes);
        addEdge(0,5);
        addEdge(5,7);
        addEdge(7,2);
        addEdge(5,1);
        addEdge(1,3);
        addEdge(1,6);
        addEdge(5,8);
        addEdge(0,9);
        addEdge(9,4);
        firstEncounter = new int[numOfNodes];
        dfs_Eular_Tour(0,-1);
        buildSparseTable(eularTour,eularTour.size());
        int u = input.nextInt();
        int v = input.nextInt();
        int fe1 = firstEncounter[u];
        int fe2 = firstEncounter[v];
        if(fe1 > fe2){
            int temp = fe1;
            fe1 = fe2;
            fe2 = temp;
        }
        int lca_newIndex = rangeMinimumQuery(eularTour,fe1,fe2);
        int lca_OldIndex = newToOld.get(lca_newIndex);
        System.out.println("Lca New Index : " + lca_newIndex);
        System.out.println("Lca Old Index : " + lca_OldIndex);
    }
}

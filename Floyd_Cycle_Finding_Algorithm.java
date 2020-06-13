import java.util.*;
public class Floyd_Cycle_Finding_Algorithm {
    static class Node{
        int id;
        Node next;
        Node(int id){
            this.id = id;
            this.next = null;
        }
    }
    static Node cycleDetect(Node start){
        Node hare = start;
        Node tortoise = start;
        Node meetingPoint = null;
        while (hare.next != null && tortoise.next != null){
            hare = hare.next;
            hare = hare.next;
            tortoise = tortoise.next;

            if(hare.id == tortoise.id){
                meetingPoint = hare;
                return meetingPoint;
            }
        }
        return meetingPoint;
    }

    static int findM(Node start,Node meetingPoint){
        Node tortoise = meetingPoint;
        Node tortoise2 = start;
        while (tortoise.id != tortoise2.id){
            tortoise = tortoise.next;
            tortoise2 = tortoise2.next;
        }
        return tortoise.id;
    }
    public static void main(String[] args) {
     Scanner input = new Scanner(System.in);
     boolean[] created = new boolean[101];
     Node start = null;
     Node prev = null;
     int p = 0;
     while (true){
         Node a = new Node(input.nextInt());
         if(a.id == -1)
             break;
         if(created[a.id] == true){
             Node cyclePoint = start;
             while (cyclePoint.id != a.id){
                 cyclePoint = cyclePoint.next;
             }
             prev.next = cyclePoint;
             break;
         }
         else if(p == 0){
             start = a;
             created[a.id] = true;
             prev = a;
         }
         else{
             prev.next = a;
             prev = a;
             created[a.id] = true;
         }
         p++;
     }
     Node meetingPoint = cycleDetect(start);
     if(meetingPoint != null){
         System.out.println("True");
         int id = findM(start,meetingPoint);
         System.out.println("Start of the cyclic part : " + id);
     }
     else{
         System.out.println("False");
     }
     // find length from start to the point of the start of the cycle



    }
}

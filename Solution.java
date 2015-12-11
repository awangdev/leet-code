import java.util.*;
public class Solution {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
          this.val = val;
          this.next = null;
      }
  }

    public static boolean hasCycle(ListNode head) {  
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            if (slow.val == fast.val) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    public static void main(String[] args){
    	System.out.println("START");
        ListNode dummy = new ListNode(-1);
    	ListNode node = new ListNode(0);
        dummy.next = node;
        node.next = new ListNode(0);
        node = node.next;
        node.next = new ListNode(2);
        node = node.next;
        node.next = new ListNode(0);
        node = node.next;
        node.next = null;
        boolean rst = hasCycle(dummy.next);
        System.out.println("END " + rst);
    }
}











import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
 
 //BFS. store as string, separated by ','
class Solution {
    class TreeNode {
       public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
         this.val = val;
         this.left = this.right = null;
     }
    }

    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        String rst = "";
        if (root == null) {
            return rst;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int size = 0;
        while (!queue.isEmpty()) {
            size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                rst += node.val + ",";
                if (node.left == null) {
                    rst += "#,";
                } else {
                    queue.offer(node.left);
                }
                if (node.right == null) {
                    rst += "#,";
                } else {
                    queue.offer(node.right);
                }
                
            }
        }//end while
        System.out.println("here rst: " + rst);
        return rst;
    }
    
    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        TreeNode root = new TreeNode(0);
        root.val = Integer.parseInt(data.substring(0, data.indexOf(",")));
        data = data.substring(data.indexOf(",") + 1);
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int size = 0;
        while (!queue.isEmpty()) {
            size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                String temp = data.substring(0, data.indexOf(","));
                if (!temp.equals("#")) {
                    node.left = new TreeNode(Integer.parseInt(temp));
                }                    
                data = data.substring(data.indexOf(",") + 1);
                
                temp = data.substring(0, data.indexOf(","));
                if (!temp.equals("#")) {
                    node.right = new TreeNode(Integer.parseInt(temp));
                }
                data = data.substring(data.indexOf(",") + 1);
            }
        }
        
        return root;
    }

    public TreeNode init() {
        TreeNode head = new TreeNode(1);
       head.left = new TreeNode(2);
       head.right = new TreeNode(3);
       return head;
    }
    public static void main(String[] args) {
       System.out.println("test");
       Solution test = new Solution();

       

       String str = test.serialize(test.init());
       System.out.println();
    }

}






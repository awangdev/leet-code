H
tags: Tree, Design, DFS, BFS, Divide and Conquer, Deque
time: O(n)
space: O(n)

Serialize and Deserialize Binary Tree

#### DFS, Divide and Conquer, Preorder
- inorder and postorder does NOT work: it is hard to find mid point, since the tree is not balanced or complete
- Serilize: Divide and conquer, Pre-order traversal to link all nodes together
    - build the string data: use '#' to represent null child. 
    - the preorder string, can be parsed apart by `split(',')`    
- Deserialize
    - Use a queue to process 1 node at a time. dfs on remaining of the queue
    - first node from the list is always the head
    - '#' will be a null child: this should break & return dfs
    - queue is shared, so dfs(right child) will happen after dfs(left child) completes
- Note:
    - Append multiple stirngs with `sb.append(x).append(y)`
    - If want to process 1 item at a time from head of the list: make it a queue and poll()

#### BFS, Non-recursive
- serialize: preorder using queue:
    - start with root
    - process curr node, then: queue.offer(leftNode),queue.offer(rightNode)
    - while(!queue.isEmpty())
- deserialize:
    - split into str[] to process
    - since serialization ensures 2 children added (including null), we assume:
        - the sequence of parent, left child, right child.
        - use queue to reproduce the preorder sequence as we process each index of str[]
    - Queue will not be empty until all index reaches end of str[], so no need to worry about queue emptiness

```
/**
LeetCode
Serialization is the process of converting a data structure or object into a sequence of bits 
so that it can be stored in a file or memory buffer, 
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. 
There is no restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that a binary tree can be serialized to a string 
and this string can be deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. 
You do not necessarily need to follow this format, 
so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. 
Your serialize and deserialize algorithms should be stateless.

*/

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
 // Just use queue to parse pre-order seralized string
 // Trick: use '#' to mark null node, so recursive function know to stop.
 public class Codec {
    private final String DELI = ",";
    private final String NULL = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        appendString(root, sb);
        return sb.toString();
    }
    private void appendString(TreeNode node, StringBuffer sb) {
        if (node == null) {
            sb.append(NULL).append(DELI);
        } else {
            sb.append(node.val).append(DELI);
            appendString(node.left, sb);
            appendString(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(DELI)));
        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue) {
        String val = queue.poll();
        if (val.equals(NULL)) return null;
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = buildTree(queue);
        node.right = buildTree(queue);
        return node;
    }
}

//BFS
public class Codec {
    private final String DELI = ",";
    private final String NULL = "#";

    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuffer sb = new StringBuffer();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append(NULL).append(DELI);
                continue;
            }
            sb.append(node.val).append(DELI);
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        String[] strs = data.split(DELI);
        TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
        queue.offer(root);
        int i = 1;
        while (i < strs.length) {
            TreeNode node = queue.poll();
            String s = strs[i++];
            if (!s.equals(NULL)) {
                node.left = new TreeNode(Integer.parseInt(s));
                queue.offer(node.left);
            }
            s = strs[i++];
            if (!s.equals(NULL)) {
                node.right = new TreeNode(Integer.parseInt(s));
                queue.offer(node.right);
            }
        }
        return root;
    }
}




// DFS: Use StringBuffer instead of queue.
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "#,";
        String mid = root.val + ",";
        String left = serialize(root.left);
        String right = serialize(root.right);
        return mid + left + right;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        StringBuffer sb = new StringBuffer(data);
        return dfs(sb);
    }
    
    private TreeNode dfs(StringBuffer sb) {
        if (sb.indexOf("#,") == 0) {
            truncateData(sb);
            return null;
        }

        int val = Integer.parseInt(sb.substring(0, sb.indexOf(",")));
        TreeNode mid = new TreeNode(val);
        truncateData(sb);

        // each dfs works on global data string, and will end untill it hits leaf node
        mid.left = dfs(sb);
        mid.right = dfs(sb);
        return mid;
    }
    
    private void truncateData(StringBuffer sb) {
        sb.delete(0, sb.indexOf(",") + 1);
    }
}
// Previous notes:
/**
1. Carefully turn the binary tree into a string: use pre-order in this example.
2. Use a global variable to track the data(data string will be cut in different levels of recursion).
The concept is very easy tho, just need to carefully code it up.
*/
class Solution {
    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "#,";
        }
        String mid = root.val + ",";
        String left = serialize(root.left);
        String right = serialize(root.right);
        mid += left + right;
        return mid;
    }
    
    private String data = "";
    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        this.data = data;
        return desHelper();
    }
    
    public TreeNode desHelper() {
        if (this.data.indexOf("#,") == 0) {
            this.data = this.data.substring(this.data.indexOf(",") + 1);
            return null;
        }
        String midVal = this.data.substring(0, this.data.indexOf(","));
        TreeNode mid = new TreeNode(Integer.parseInt(midVal));
        this.data = this.data.substring(this.data.indexOf(",") + 1);
        TreeNode left = desHelper();
        TreeNode right = desHelper();
        mid.left = left;
        mid.right = right;
        return mid;
    }
}


```
H
1527781232
tags: Tree, Design, DFS, BFS, Divide and Conquer, Deque

Serialize and Deserialize Binary Tree

#### DFS, Divide and Conquer
##### Serilize
- Divide and conquer: Pre-order traversal to link all nodes together
- build the string data: use '#' to represent null child. 
- the preorder string, can be parsed apart by `split(',')`

##### Deserialize
- Use a list (here we use `Deque` for the ease of get/remove in 1 function: remove()) 
- to take all parts of the parsed sring data: dfs on the Deque
- first node from the list is always the head
- '#' will be a null child: this should break dfs
- Deque is a global variable, so dfs(right child) will happen after dfs(left child) completes

#### DFS, Recursive [previous note]
- serilize: divide and conquer, pre-order traversal
- deserialize: 稍微复杂, 用dfs. 每次要truncate input string: 
- 一直dfs找left child, 接着right child until leaf is found.
- 用一个StringBuffer来hold string, 因为string 是primitive, 我们这里需要pass reference

#### BFS, Non-recursive
- using queue. 想法直观。level-order traversal. save到一个string里面就好。
- 遇到null child, 不是直接忽略, 而是assign一个Integer.MIN_VALUE, 然后 mark as '#'
- BFS需要track queue size, 每一次只process特定数量的nodes

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


/*
Design an algorithm and write code to serialize and deserialize a binary tree. 
Writing the tree to a file is called 'serialization' and 
reading back from the file to reconstruct the exact same binary tree is 'deserialization'.

There is no limit of how you deserialize or serialize a binary tree, 
you only need to make sure you can serialize a binary tree to a string and 
deserialize this string to the original structure.

Example
An example of testdata: Binary tree {3,9,20,#,#,15,7},  denote the following structure:

    3
   / \
  9  20
    /  \
   15   7
Our data serialization use bfs traversal. 
This is just for when you got wrong answer and want to debug the input.

You can use other method to do serializaiton and deserialization.

Tags Expand 
Binary Tree
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
// DFS, Recursive
public class Codec {
    private final String DELI = ",";
    private final String NULL = "#";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        buildString(root, sb);
        return sb.toString();
    }
    private void buildString(TreeNode node, StringBuffer sb) {
        if (node == null) {
            sb.append(NULL).append(DELI);
        } else {
            sb.append(node.val).append(DELI);
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(DELI)));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals(NULL)) return null;
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = buildTree(nodes);
        node.right = buildTree(nodes);
        return node;
    }
}

public class Codec {
    // Encodes a tree to a single string.
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



//BFS. store as string, separated by ','
//Note: need to record null node as well. but be careful don't push null into queue
public class Codec {
    public String serialize(TreeNode root) {
        String rst = "";
        if (root == null) {
            return rst;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 0;
        while (!queue.isEmpty()) {
            size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.val == Integer.MIN_VALUE) {
                    rst += "#,";
                } else {
                    rst += node.val + ",";
                    TreeNode left = node.left == null ? 
                        new TreeNode(Integer.MIN_VALUE) : node.left;
                    queue.offer(left);
                    TreeNode right = node.right == null ? 
                        new TreeNode(Integer.MIN_VALUE) : node.right;
                    queue.offer(right);
                }
            }
        }
        return rst;
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer(data);
        TreeNode root = new TreeNode(0);
        root.val = Integer.parseInt(findValue(sb));
        truncateData(sb);
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 0;
        while (!queue.isEmpty()) {
            size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                String nextVal = findValue(sb);
                if (!nextVal.equals("#")) {
                    node.left = new TreeNode(Integer.parseInt(nextVal));
                    queue.offer(node.left);
                }                    
                truncateData(sb);
                
                nextVal = findValue(sb);
                if (!nextVal.equals("#")) {
                    node.right = new TreeNode(Integer.parseInt(nextVal));
                    queue.offer(node.right);
                }
                truncateData(sb);
            }
        }
        
        return root;
    }
    
    private void truncateData(StringBuffer sb) {
        sb.delete(0, sb.indexOf(",") + 1);
    }
    
    private String findValue(StringBuffer sb) {
        return sb.substring(0, sb.indexOf(","));
    }
}

// Previous notes:

// DFS with global data string
public class Codec {

    // Encodes a tree to a single string.
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

    // Decodes your encoded data to tree.
    private String data = "";

    public TreeNode deserialize(String data) {
        this.data = data;
        return dfs();
    }
    
    private TreeNode dfs() {
        if (checkLeaf()) {
            truncateData();
            return null;
        }

        TreeNode mid = new TreeNode(findValue());
        truncateData();

        // each dfs works on global data string, and will end untill it hits leaf node
        mid.left = dfs();
        mid.right = dfs();
        return mid;
    }
    
    private void truncateData() {
        this.data = this.data.substring(this.data.indexOf(",") + 1);
    }
    
    private int findValue() {
        return Integer.parseInt(this.data.substring(0, this.data.indexOf(",")));
    }
    
    private boolean checkLeaf() {
        return this.data.indexOf("#,") == 0;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

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
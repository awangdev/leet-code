M
tags: Tree
time: O(n)
space: O(n)

#### DFS, Divide and Conquer, Preorder (utilizing BST)
- with BST, we can:
  - skip adding the null nodes into the serialized string: `String NULL = "#"`
  - In deserialization: use min/max boundary to check if queue.peek() can be added:
    - if not meeting BST condition, skip this dfs and let other call to consume the queue
- Faster because it shortens the serialized string
  

#### DFS, Divide and Conquer, Preorder (w/o using BST)
- Take reference in Serialize and Deserialize Binary Tree
- The approach works but does not utilize Binary Search Tree properties

```

/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
*/
// DFS utilizing binary search tree
public class Codec {
    private final String DELI = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        appendString(root, sb);
        return sb.toString();
    }
    private void appendString(TreeNode node, StringBuffer sb) {
        if (node == null) return;
        sb.append(node.val).append(DELI);
        appendString(node.left, sb);
        appendString(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(DELI)));
        return buildTree(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode buildTree(Queue<String> queue, int min, int max) {
        if (queue.isEmpty()) return null;
        String s = queue.peek();
        int currVal = Integer.parseInt(queue.peek());
        if (currVal < min || currVal > max) return null;
        queue.poll();
        TreeNode node = new TreeNode(currVal);
        node.left = buildTree(queue, min, currVal);
        node.right = buildTree(queue, currVal, max);
        return node;
    }
}

// DFS w/o utilizing Binary Search Tree
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
```
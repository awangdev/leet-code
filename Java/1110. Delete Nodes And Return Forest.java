M
tags: Tree, DFS, Divide and Conquer
time: O(n)
space: O(logn)

#### Method1: DFS, divide and conquer
- dfs function: have toDelete set, and a result list
- dive deep into child node FIRST, and test if a removal is needed at bottom of tree
- if remove, add orphan and return null; otherwise, return itself
- time: O(n), visit all nodes
- space: O(logn), height of the tree

#### Method2: HashMap, DFS.
- traverse tree and create `map <val, parent>` to fast O(1) removal. O(n)
- set root into a rootSet
- after deleting a node A, the children of the node becomes 2 forests root
    - children should be marked in rootSet
    - also remove node A from rootSet (if appears)
- output: find all root in root set, traverse and output.
- This approach requires a dfs build of parentMap
    - it is same amount of efforts to do the regular dfs removal.
    - not a good solution
- time: O(n)
- space: O(n)

```

/*
Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order.

 

Example 1:



Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
 

Constraints:

The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.


*/



/*
#### Method1: DFS, divide and conquer
- dfs function: have toDelete set, and a result list
- dive deep into child node FIRST, and test if a removal is needed at bottom of tree
- if remove, add orphan and return null; otherwise, return itself
*/
class Solution {
   
    public List<TreeNode> delNodes(TreeNode root, int[] toDelete) {
        Set<Integer> toDeleteSet = new HashSet<>();
        for (int val : toDelete) toDeleteSet.add(val);
        List<TreeNode> rst = new ArrayList<>();
        root = dfs(root, toDeleteSet, rst);
        if (root != null) rst.add(root);
        return rst;
    }
   
    private TreeNode dfs(TreeNode node, Set<Integer> toDeleteSet, List<TreeNode> rst) {
        if (node == null) return null;
        node.left = dfs(node.left, toDeleteSet, rst);
        node.right = dfs(node.right, toDeleteSet, rst);
       
        if (toDeleteSet.contains(node.val)) {
            if (node.left != null) rst.add(node.left);    
            if (node.right != null) rst.add(node.right);    
            return null;
        }
        return node;
    }
}


/*
#### Method2: HashMap, DFS
- traverse tree and create `map <val, parent>` to fast removal. O(n)
- set root into a rootSet
- after deleting a node A, the children of the node becomes 2 forests root
    - children should be marked in rootSet
    - also remove node A from rootSet (if appears)
- output: find all root in root set, traverse and output.
- time: O(n)
- space: O(n)
*/
class Solution {
   
    public List<TreeNode> delNodes(TreeNode root, int[] toDelete) {
   
        Map<Integer, TreeNode> rootMap = new HashMap<>();
        Map<Integer, TreeNode> parentMap = new HashMap<>();
        buildMap(parentMap, root);

        rootMap.put(root.val, root);
       
        for (int val : toDelete) {
            TreeNode nodeToDelete;
            if (rootMap.containsKey(val)) {
                nodeToDelete = rootMap.get(val);
                rootMap.remove(val);
            } else {
                nodeToDelete = remove(parentMap.get(val), val); // node = 3, 5
                parentMap.remove(val);
            }
            if (nodeToDelete.left != null) rootMap.put(nodeToDelete.left.val, nodeToDelete.left); // add: 6, null
            if (nodeToDelete.right != null) rootMap.put(nodeToDelete.right.val, nodeToDelete.right); // add: 7, null
        }
       
        return new ArrayList<>(rootMap.values());
    }
   
    private TreeNode remove(TreeNode parent, int val) {
        TreeNode node;
        if (parent.left != null && parent.left.val == val) {
            node = parent.left;
            parent.left = null;
        } else {
            node = parent.right;
            parent.right = null;
        }
        return node;
    }
   
    private void buildMap(Map<Integer, TreeNode> map, TreeNode node) {
        if (node == null) return;
        if (node.left != null) {
            map.put(node.left.val, node);
            buildMap(map, node.left);
        }
        if (node.right != null) {
            map.put(node.right.val, node);
            buildMap(map, node.right);
        }
    }
}

```
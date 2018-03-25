M
1522013628
tags: BST

给一个unsorted array, 问, 是否有两个element, value相差最大为t,  而两个element的index 相差最大为k.

Note: 虽然题目名字是Contains Duplicate, 但其实要找的两个element不是duplicate, 而是Math.abs(value1 - value2) <= t.

#### TreeSet
- TreeSet还是一个set, 我们用来装已经visit过得item
- 如果window大小超过K, 那么把nums[i - k - 1] 去掉, 并且加上新的element
- 这里有个公式推算: (Math.abs(A-B) <= t) =>>>>> (-t <= A - B <= t) =>>>>>> A >= B - t, A <= B + t
- 也就是说, 如果对于 B = nums[i], 来说, 能找到一个target A, 满足上面的公式, 那么就可以 return true.
- Time O(nLogk), treeSet的大小不会超过k,  而 treeSet.ceiling(), treeSet.add(), treeSet.remove() 都是 O(logK)
- Space O(k)

#### Note
- 与Contains Duplicate II 类似概念. TreeSet有BST 因此可以直接用, 而不用自己构建BST
- 简化题目里面的重要条件 Math.abs(A-B) <= t 而推断出 A >= B - t, A <= B + t
- 并且需要需要用 TreeSet.ceiling(x): return number greater or equal to x. 这个用法要记住吧, 没别的捷径.

```

/*
Given an array of integers, find out whether there are two distinct indices i and j
in the array such that the absolute difference between nums[i] and nums[j]
is at most t and the absolute difference between i and j is at most k.
*/

/*
Thoughts:
Given Math.abs(A-B) <= t ->>> -t <= A - B <= t. Therefor, we want find A such as:
A >= B - t
A <= B + t
1. Need to find an element A thats greater to equal to (B-t), and also use the A needs to less or equal to (B+t)
For this, we can use a binary search tree, where to can quickly find the (B-t) and hence A.
Utilize TreeSet.ceiling() to find A
2. This binary search tree needs to be flexible in terms of removing item: 
Exact same idea as in 'Contains Duplicate II': once i pass k, we need to remove any value that's in range [0, i-k) from the set, because they are out of range.
*/
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long target = treeSet.ceiling((long)nums[i] - t);
            if (target != null && target <= (long)nums[i] + t) {
                return true;
            }
            if (i >= k) {
                treeSet.remove((long)nums[i - k]);
            }
            treeSet.add((long)nums[i]);
        }
        return false;
    }
}

/*
Incorrect: the index-based BST won't work. If item appears on both size of the tree, it's no longer possible to trace. Ex: [1, 3, 1], k=2, t=1

Also, if want to use BST, we can directly use TreeSet.
Thoughts:
1. Create binary search tree based on index, and store value/index in the node.
2. Traverse over each node of the tree, and compare the root against all children possible, to find abs(value diff) < t.
3. When abs(index diff) > k, stop diving deep.
*/
class Solution {
    class Node {
        int value;
        int index;
        Node left;
        Node right;
        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        final Node root = buildBinarySearchTree(nums, 0, nums.length - 1);
        return findMatch(null, root, k, t);
    }
    
    private boolean findMatch(Node parent, Node node, int k, int t) {
        if (node == null || (parent != null && Math.abs(parent.index - node.index) > k)) {
            return false;
        }
        return validateDiff(node, node.left, k, t) || validateDiff(node, node.right, k, t)
            || findMatch(node, node.left, k, t) || findMatch(node, node.right, k, t);
    }
    
    private boolean validateDiff(Node nodeA, Node nodeB, int k, int t) {
        if (nodeA == null || nodeB == null) {
            return false;
        }
        return Math.abs(nodeA.value - nodeB.value) <= t && Math.abs(nodeA.index - nodeB.index) <= k;
    }

    private Node buildBinarySearchTree(int[] nums, int start, int end) {
        if (start > end || start < 0 || end >= nums.length ) {
            return null;
        }
        if (start == end) {
            return new Node(nums[start], start);
        }
        int mid = start + (end - start) / 2;
        Node node = new Node(nums[mid], mid);
        node.left = buildBinarySearchTree(nums, start, mid - 1);
        node.right = buildBinarySearchTree(nums, mid + 1, end);
        return node;
    }
}
```
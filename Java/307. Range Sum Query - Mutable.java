M
tags: Segment Tree, Binary Indexed Tree
time: build O(n), query (logn +k), update O(logn)
space: O(n)

#### Segment Tree, devide and conquer
- sample problem for segment tree
- build(), update(), rangeQuery()
    - build and update are standard
    - rangeQuery: handle the range split check
- Null leaf node handling: NO, ideally it will not encounter null leaf.
    - in update/rangeQuery: when final state (`start==end`) is reached, the recursive call ends
    - there is no way for any node to dive futher into null child.
- Range Query concept:
    - Using the input range, sum up everything in the range
    - sometimes the input range cover multiple segments, then dive into the segments (still use original range)
    - once we found a bounded segment (completely surrounded by input range), return segment value.

```

/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.

Example:

Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:

The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.

*/

class NumArray {
    SegTreeNode segTree;
    public NumArray(int[] nums) {
        if (nums == null || nums.length == 0) return;
        segTree = build(nums, 0, nums.length - 1);
    }
    
    public void update(int i, int val) {
        updateNode(segTree, i, val);
    }
    
    public int sumRange(int i, int j) {
        return rangeQuery(segTree, i, j);
    }
    
    class SegTreeNode {
        int start, end, sum;
        SegTreeNode left, right;
        public SegTreeNode(int start, int end, int sum, SegTreeNode left, SegTreeNode right) {
            this.start = start;
            this.end = end;
            this.sum = sum;
            this.left = left;
            this.right = right;
        }
    }
    
    private SegTreeNode build(int[] nums, int start, int end) {
        if (start == end) return new SegTreeNode(start, end, nums[start], null, null);
        int mid = start + (end - start) / 2;
        SegTreeNode left = build(nums, start, mid);
        SegTreeNode right = build(nums, mid + 1, end);
        return new SegTreeNode(start, end, left.sum + right.sum, left, right);
    }
    
    private void updateNode(SegTreeNode node, int i, int val) {
        if (node.start == i && node.end == i) {
            node.sum = val;
            return;
        }
        if (i <= node.left.end) updateNode(node.left, i, val);
        else updateNode(node.right, i, val);
        node.sum = node.left.sum + node.right.sum;
    }
    
    private int rangeQuery(SegTreeNode node, int i, int j) {
        if (node.start == i && node.end == j) return node.sum;
        if (j <= node.left.end) return rangeQuery(node.left, i, j);
        else if (i >= node.right.start) return rangeQuery(node.right, i, j);
        int mid = node.start + (node.end - node.start) / 2;
        return rangeQuery(node.left, i, mid) + rangeQuery(node.right, mid + 1, j);
    }
    
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
```
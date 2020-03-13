E
tags: Array, Bucket Sort
time: O(n)
space: O(1)

#### Method1: Bucket Sort concept, set val to its correct position
- Given: values are [1,n], so val can represent index. Therefore, set val to its correct position
- 小心handle i:
    - value是 1-based
    - 每次换位, 需要`i--`, 重新省察`nums[i]`

#### Method2: 做标记 (negative number, or super large number)
- Option1: use negative number to mark visited:
    - 很巧妙地运用了标记的方法, 标记成负数，证明visit过。
    - Preserve原数的负数，这样可以继续用此负数的绝对值来寻找原数所该被定的位置。非常巧妙！
- Option2: use large number (larger than n)
    - 跟方法2类似，也是做标记，这一次是加上一个大于n的数（因为题目给了n的border），最后check一下就好。为不超Integer.MAX_VALUE, 每次加n前，取余数。
    - 做标记的方法固然快，但是相对来说比较hacky，在常规的代码中，估计不会用到.


```
/*
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
*/

/*
Method1: 
Move it to where it should be at. 
Important: if we keep traverse for loop, once it swap once and i + 1, the nums[i] may have a incorrect value.
Therefore, the solution should handle (i - 1) after each swap, until there is no swap to do on nums[i]. 
In the background, the rumtime may accumulate up to 2n: n for all sways on index i = 0; and another n for the rest traverse. Overall, it's still O(n)

*/
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        final List<Integer> rst = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) return rst;
        
        for (int i = 0; i < nums.length; i++) {
            int desiredIndex = nums[i] - 1;
            if (nums[desiredIndex] != nums[i]) swap(nums, desiredIndex, i--); // i-- to revisit the new nums[i]
        }

        // find missing elements
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) rst.add(i + 1);
        }
        return rst;
    }
    
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}


/*
Method2: Marking it with negative number
1. We want mark the num at it's desired index: num - 1.
2. The non-marked positions are missing the desired value: index + 1.

One approach is to sort the the items into correct position and remove redundant, but that'll take nLogN. So, don't sort.

A better approach is to mark the desired position to negative. 
Note, we can't just set to -1, because the desired position might be further down in the traversed array, where we'd need its abs value again.
*/
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        final List<Integer> rst = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) return rst;
            
        for (int i = 0; i < nums.length; i++) {
            int desiredIndex = Math.abs(nums[i]) - 1;
            nums[desiredIndex] = -Math.abs(nums[desiredIndex]); // mark negative
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) rst.add(i + 1);
        }
        
        return rst;
    }
}
```
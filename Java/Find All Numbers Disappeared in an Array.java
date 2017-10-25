E

方法1:
换到正确的位置。
需要小心handle i, 因为不知道换到nums[i]上的是什么，所以必须原地清理干净 方能move on.

方法2:
做标记！
很巧妙地运用了标记的方法, 标记成负数，证明visit过。
Preserve原数的负数，这样可以继续用此负数的绝对值来寻找原数所该被定的位置。非常巧妙！

方法3:
做标记！
跟方法2类似，也是做标记，这一次是加上一个大于n的数（因为题目给了n的border），最后check一下就好。为不超Integer.MAX_VALUE, 每次加n前，取余数。

做标记的方法固然快，但是相对来说比较hacky，在常规的代码中，估计不会用到.


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
Thought1:
Move it to where it should be at. 
Important: if we keep traverse for loop, once it swap once and i + 1, the nums[i] may have a incorrect value.
Therefore, the solution should handle (i - 1) after each swap, until there is no swap to do on nums[i]. 
In the background, the rumtime may accumulate up to 2n: n for all sways on index i = 0; and another n for the rest traverse. Overall, it's still O(n)

*/
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        final List<Integer> resultList = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return resultList;
        }
        for (int i = 0; i < nums.length; i++) {
            int desiredIndex = nums[i] - 1;
            if (nums[desiredIndex] != nums[i]) {
                int temp = nums[desiredIndex];
                nums[desiredIndex] = nums[i];
                nums[i] = temp;
                i--;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                resultList.add(i + 1);
            }
        }
        return resultList;
    }
}


/*
Thought2:
1. We want mark the num at it's desired index: num - 1.
2. The non-marked positions are missing the desired value: index + 1.

One approach is to sort the the items into correct position and remove redundant, but that'll take nLogN. So, don't sort.

A better approach is to mark the desired position to negative. 
Note, we can't just set to -1, because the desired position might be further down in the traversed array, where we'd need its abs value again.
*/
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        final List<Integer> resultList = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return resultList;
        }

        for (int i = 0; i < nums.length; i++) {
            int desiredIndex = Math.abs(nums[i]) - 1;
            nums[desiredIndex] = nums[desiredIndex] < 0 ? nums[desiredIndex] : -nums[desiredIndex];
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                resultList.add(i + 1);
            }
        }
        
        return resultList;
    }
}
```
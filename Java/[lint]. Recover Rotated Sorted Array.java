E
tags: Array, Lint

rotate的意思，是有个点断开，把一边的array节选出来放在另外一边。
Rotate三步：
rotate前半
rotate后半
rotate全部

注意先找到断点。
```
/*
Given a rotated sorted array, recover it to sorted array in-place.

Example
[4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]

Challenge
In-place, O(1) extra space and O(n) time.

Clarification
What is rotated array:

    - For example, the orginal array is [1,2,3,4], The rotated array of it can be [1,2,3,4], [2,3,4,1], [3,4,1,2], [4,1,2,3]

Tags Expand 
Array Sorted Array


*/

/*
    Thougths: 12.08.2015.
    Same idea as previous solution. Just re-write to practice
    1. reverse function.
    2. find break point.
    3. reverse!
        - reverse 1st part
        - reverse 2nd part
        - reverse all
*/
public class Solution {
    public void recoverRotatedSortedArray(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return;
        }
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) > nums.get(i + 1)) {
                reverse(nums, 0, i);
                reverse(nums, i + 1, nums.size() - 1);
                reverse(nums, 0, nums.size() - 1);
                return;
            }
        }

    }
    //reverse certain range
    public void reverse(ArrayList<Integer> nums, int start, int end){
        for (int i = start, j = end; i < j; i++,j--) {
            int temp = nums.get(i);
            nums.set(i, nums.get(j));
            nums.set(j, temp);
        }
    }
}

/*
Thinking process:
    Create a reverse function
    When there is a wrong position, start 3 steps:
    1. Reverse 1st part.
    2. Reverse 2nd part.
    3. Reverse them all.
*/

```
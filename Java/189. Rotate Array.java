E
tags: Array, Rotation

#### Rotate array in place
- rotate all
- rotate 2 sides: < k or >= 


#### Rotate by buffer the k array

```
/*
Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
Note:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?

 */

/*
1. rotate all
1. rotate 2 sides: < k or >= k
*/
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        if (k == 0) return;
        // rotate all
        rotateByRange(nums, 0, n - 1);
        // rotate side
        rotateByRange(nums, 0, k - 1);
        rotateByRange(nums, k, n - 1);
    }
    
    private void rotateByRange(int[] nums, int x, int y) {
        while (x >= 0 && x < y) {
            int temp = nums[x];
            nums[x] = nums[y];
            nums[y] = temp;
            x++;
            y--;
        }
    }
}
```
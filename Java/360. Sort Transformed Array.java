M
tags: Math, Two Pointers
time: O(n)
space: O(n) store result

#### Two Pointers, Math
- Being able to analys the a*x^2 + b*x graph and find the `peak` or `valley`
- Math basics: x^2 dominates the overall curve so it is up to a to determine:
    - `valley`: if a < 0, both sides will be small and center will be large. Prioritize larger value.
    - `peak`: if a > 0, center will be small and both sides will be large. Prioritize smaller value.
    - starting index being 0 or n-1, is driven by `a`

```
/*
Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

Example 1:

Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
Output: [3,9,15,33]
Example 2:

Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
Output: [-23,-5,1,7]
*/

/*
- Math basics: x^2 dominates the overall curve so it is up to a to determine:
    - if a < 0, both sides will be small and center will be large. Prioritize larger value.
    - if a > 0, center will be small and both sides will be large. Prioritize smaller value.
    - starting index being 0 or n-1, is driven by a
*/
class Solution {
    int a, b, c;
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        int n = nums.length, i = 0, j = n - 1;
        int[] rst = new int[n];
        int index = a >= 0 ? n - 1 : 0;
        
        while (i <= j) {
            int left = calc(nums[i]), right = calc(nums[j]);
            if (a >= 0) {
                rst[index--] = left >= right ? calc(nums[i++]) : calc(nums[j--]);
            } else { // a < 0
                rst[index++] = left >= right ? calc(nums[j--]) : calc(nums[i++]);
            }
        }

        return rst;
    }
    
    private int calc(int x) {
        return a * x * x + b * x + c;
    }
}
```
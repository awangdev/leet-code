M

3Sum 的一种简单形式, 并且都没有找index, value, 而只是找个sum罢了.

double for loop。 2Sum只能用土办法 left/right 2 pointers。 O(n^2)

注意：check closest时候用long, 以免int不够用

```
/*
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. 
Note
You may assume that each input would have exactly one solution.
Example
For example, given array S = {-1 2 1 -4}, and target = 1. The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
Tags Expand 
Two Pointers Sort Array

*/

/*
Thoughts:
    Similar to 3 SUM.
    Starting from the left-element, assume it's the solution. Move the 2 pointers in the right-side-array.
    Using the two pointers, trying to find ele1 + ele2 + ele3 = closest number to target.
    Note: for comparing closet, use initial value Integer.MAX_VALUE. Be aware of the overflow of integer, use long to handle.

*/
public class Solution {

    public int threeSumClosest(int[] num, int target) {
        if (num == null || num.length < 3) {
            return Integer.MAX_VALUE;
        }
        Arrays.sort(num);
        long closest = (long) Integer.MAX_VALUE;
        for (int i = 0; i < num.length - 2; i++) {
            int left = i + 1;
            int right = num.length - 1;
            while (left < right) {
                int sum = num[i] + num[left] + num[right];
                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
                closest = Math.abs(sum - target) < Math.abs(closest - target) 
                            ? (long) sum : closest;
            }//while
        }//for
        return (int) closest;
    }
}

```
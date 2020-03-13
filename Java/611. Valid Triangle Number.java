M
tags: Array, Two Pointers
time: O(n^2)
space: O(logn), sorting space

#### Method1: Fix max and backward counting
- Sort nums: O(nlogn)
- Set max value fixed on right side at k
    - set 2nd value from right index j
    - set last value at min index i
    - if `nums[i] + nums[j] > nums[k]`: with fixed j, i can pick from [i, j-1] combinations
        - then j--, to pick another j candidate
    - maintain a window [i,j]; if invalid, move i++
- time: O(n^2)
- Note: very similar to 3-sum, fixing 1 index and use 2 pointers to move window

#### Method2: Fix min and forward counting
- Sort nums: O(nlogn)
- Set min value at i
    - set 2nd value at j=i+1; and 3rd value at k=i+2
    - find max of k that fits into triangle
    - count all possible k candidates from [j+1, k]
    - then move j to a new candidate
- O(n^2)

```
/*
Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].
*/

/*
Method1: Fix max and backward counting
- Sort nums: O(nlogn)
- Set max value fixed on right side at k
    - set 2nd value from right index j
    - set last value at min index i
    - maintain a window [i,j] that `nums[i] + nums[j] > nums[k]`
- O(n^2)
*/
class Solution {
    public int triangleNumber(int[] nums) {
        
        Arrays.sort(nums);
        int n = nums.length, count = 0;
        for (int k = n - 1; k >= 2; k--) { // k starting at max, decreasing
            for (int i = 0, j = k - 1; i < j;) { // j starting at max, to make (nums[j] + nums[k]) at max
                if (nums[i] + nums[j] > nums[k]) {
                    count += j - i;
                    j--; // move j down for new possibilities
                } else i++; // when invalid, need to increase min value to make it valid
            }
        }
        return count;
    }
}

/*
Method2: Fix minimum and forward counting
- Sort nums: O(nlogn)
- Set min value at i
    - set 2nd value at j=i+1; and 3rd value at k=i+2
    - find max of k that fits into triangle
    - count all possible k candidates from [j+1, k]
    - then move j to a new candidate
- O(n^2)
*/
class Solution {
    public int triangleNumber(int[] nums) {
        
        Arrays.sort(nums);
        int n = nums.length, count = 0;
        for (int i = 0; i <= n - 3; i++) { // set min value at i
            if (nums[i] == 0) continue; // pointless, skip
            int k = i + 2; // pick k
            for (int j = i + 1; j <= n - 2; j++) { // fix j
                while (k < n && nums[i] + nums[j] > nums[k]) k++; // find k max
                count += k - j - 1; // count all possible k candidates from [j+1, k]
            }
        }
        return count;
    }
}
```
M
1531731969
tags: Reservior Sampling
time: O(n)
space: O(n) for input int[], O(1) extra space used

#### Reservior sampling
- Random choose: think about reservoir sampling. https://www.youtube.com/watch?v=A1iwzSew5QY
- Use random generator rd.nextInt(x) pick integer between [0, x)
- try all numbers, when target is met, we want to model reservoir sampling:
- item was chosen out of i samples, and all other samples are failed.
- where we can use 'count' to represent the denominator/base to choose.
- **HAVE TO finish all samples** to make sure equal opportunity
- we can pick that last matched item as result
- `rd.nextInt(count++) == 0` make sure we are always picking num == 0 to meet definition of reservoir sampling.

#### Knowledge
- If multiply these probablities together to get the probability of one item being chosen with reservior sampling:
- probability = 1/i * (1 - 1/i+1) * (1 - 1/i+2) ....(1 - 1/n) = 1/n


```
/*
Given an array of integers with possible duplicates, randomly output the index of a given target number. 
You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. 
Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);
*/

/*
Random choose: think about reservoir sampling.
Use random generator rd.nextInt(x) pick integer between [0, x)
Here we can try all numbers, when target is met, we want to model reservoir sampling:
item was chosen out of i samples, and all later samples are failed; multiply these probablities together we'll get the probability in reservior sampling:
1/i * (1 - 1/i+1) * (1 - 1/i+2) ....(1 - 1/n), where we can use 'count' to represent the denominator
we'll pick that one particular number as result
*/
class Solution {
    Random rd;
    int[] nums;
    public Solution(int[] nums) {
        this.rd = new Random();
        this.nums = nums;
    }
    
    public int pick(int target) {
        int count = 1, result = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) {
                continue;
            }
            if (rd.nextInt(count++) == 0) {
                result = i;
            }
        }
        return result;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
```
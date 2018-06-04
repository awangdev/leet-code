M
1528045137
tags: Array

#### Sort + count
- O(nlogN)

#### Two counters
- O(n), count and track valueA, valueB
- count overall apperance at the end for the two items
- save to result
- 注意: 按照if statement的顺序, valA&&countA 比valB&&countB有优先性

```

/*
LeetCode
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]
Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]

Note: different from LintCode Majority Number(Element) II, where it returns integer.
*/

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        } else if (nums.length == 1) {
            result.add(nums[0]);
            return result;
        }
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        int prev = nums[0];
        // count
        for (int i = 0; i < n; i++) {
            count += nums[i] == prev ? 1 : 0;
            addResult(result, n, prev, count);
            // Reset prev and count if different
            if (nums[i] != prev) {
                prev = nums[i];
                count = 1;
            }
        }
        addResult(result, n, prev, count);
        return result;
    }

    private void addResult(List<Integer> result, int n, int value, int count) {
        if (!result.contains(value) && count > n / 3) {
            result.add(value);
        }
    }
}


class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int valA = 0; 
        int valB = 0;
        int countA = 0;
        int countB = 0;
        for (int num : nums) {
            if (num == valA) {
                countA++;
            } else if (num == valB) {
                countB++;
            } else if (countA == 0) {
                valA = num;
                countA++;
            } else if (countB == 0){
                valB = num;
                countB++;
            } else {//None of a || b matches
                countA--;
                countB--;
            }
        }//For
        
        countA = 0; 
        countB = 0;
        for (int num : nums) {
            if (num == valA) {
                countA++;
            } else if (num == valB) {
                countB++;
            }
        }
        if (countA > nums.length / 3) result.add(valA);
        if (countB > nums.length / 3) result.add(valB);
        return result;
    }
}

```
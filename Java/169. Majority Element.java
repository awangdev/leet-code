E
tags: Array, Divide and Conquer, Bit Manipulation, Moore Voting, Sort
time: O(n)
space: O(1)

#### HashMap count occurance
- Time, Space: O(n)


#### Moore Voting Algorithm 投票消减
- 前提: input必须valid, 比较罕见少用. Moore Voting Algorithm: https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
- 与当下candidate相同, vote++. 与之不同, vote--.
- Majority Number是指超半数, 多1个就行: 消减至最后, 会至少有vote>=1.
- 那么: vote++, vote--到最后剩下的就是winner. 
- 这个方法比较greedy, 前提是: valid input, 是一定有一个majority number的。否则此法不成。[1,1,1,2,2,2,3]是个invalid input,结果是3，当然也错了。
- time: O(n), space O(1)

#### Sort
- sort entire nums array
- assume there is a solution, then nums[n/2] must be that majority num
- time O(nlogn)

#### Divide and Conquer
1. Recursive approach
1. For ange rangeA & rangeB, rangeA has majorElementA and rangeB has majorElementB
    - majorElementA = majorElementB, of course this element will be the major number for whole range
    - if majorElementA != majorElementB, then need to count both elements in whole range
    - of course the larger occurance will be the major num

#### Bit manipulation
- TODO

#### Related Problems
- Majority Number II，超1/3, 那么就分三份处理，countA, countB来计算最多出现的两个。
- Majority Number III, 超1/k, 那么自然分k份。这里用到 HashMap。

```
/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2

*/


/*
HashMap
Save the element into hashmap and count. O(n) space, O(n) time
*/
class Solution {
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 0);
            }
            map.put(num, map.get(num) + 1);
        }
        
        int halfLen = nums.length / 2;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > halfLen) {
                return entry.getKey();
            }
        }
        return -1;
    }
}

/*
Sort
*/
class Solution {
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}

/**
Divide an conquer
 */
class Solution {
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return subMajorElement(nums, 0, nums.length - 1);
    }
    
    private int subMajorElement(int[] nums, int lo, int hi) {
        if (lo == hi) return nums[lo];
        int mid = lo + (hi - lo) / 2;
        int left = subMajorElement(nums, lo, mid);
        int right = subMajorElement(nums, mid + 1, hi);
        
        // if same, that ele is definitely the major element in range
        if (left == right) return left;
        
        // if not same, compare
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);
        
        return leftCount > rightCount ? left : right;
    }
    
    private int countInRange(int[] nums, int value, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == value) count++;
        }
        return count;
    }
}

/*
Moore Voting
Vote each element and record majorityNumber.
Whenever not matching majorityNumber, vote--.
Whoever holds the majorityNumber in the end, will be the majority number.

Reverse thinking:
Imagine the majority number does exist, and has n/2 + 1 occurances.
If let all numbers vote++ or vote--, in the end the vote will equal to 1.
*/
class Solution {
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int majorityNum = nums[0];
        int vote = 0;
        for (int num : nums) {
            vote += num == majorityNum ? 1 : -1;
            if (vote == 0) {
                majorityNum = num;
                vote = 1;
            }
        }
        return majorityNum;
    }
}


/*
Given an array of integers, the majority number is the number that occurs more than half of the size of the array. Find it.

Example
For [1, 1, 1, 1, 2, 2, 2], return 1

Challenge
O(n) time and O(1) space

Tag: Enumeration


*/

/*
Thinking process:
Natural thinking process: count how many you have for 1st element, if next one is the same, count++, if next one is not the same, count- -. 
When count ==0, that means other types of element has same amount as the 1st majority number, they are even.
From this point, count the value at current position as the majority number, keep the loop rolling.
Note: this solutions works only when the given array has a valid solution.
CounterCase:[111223], with actually return 3 as the majority number. But again, this is not a valid input in this case.
*/
public class Solution {
    /**
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return -1;
        }
        int majorNum = nums.get(0);
        int count = 1;
        for (int i = 1; i < nums.size(); i++) {
            if (majorNum == nums.get(i)) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                majorNum = nums.get(i);
                count = 1;
            }
        }
        return majorNum;
    }
}


```
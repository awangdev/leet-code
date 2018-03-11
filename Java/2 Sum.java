E
1516438794
tags: Array, Hash Table

tutorial:https://www.youtube.com/watch?v=P8zBxoVY1oI&feature=youtu.be

解法1：相对暴力简洁, HashMap<value, index>，找到一个value, 存一个; 若在HashMap里面 match 到结果, 就return HashMap里存的index. O(n) space && time.

解法2：Sort array, two pointer 前后++,--搜索。Sort 用时O(nlogn).     
1. 第一步 two pointer 找 value.       
2. 注意，要利用额外的空间保留original array， 用来时候找index. (此处不能用HashMap，因为以value 为key，但value可能重复)      
O(n) space, O(nlogn) time.    


```

/*
Given an array of integers, find two numbers such that they add up to a specific target number.
The function twoSum should return indices of the two numbers such that they add up to the target, 
where index1 must be less than index2. 
Please note that your returned answers (both index1 and index2) are NOT zero-based.
Example
numbers=[2, 7, 11, 15], target=9
return [1, 2]
Note
You may assume that each input would have exactly one solution
Challenge
Either of the following solutions are acceptable:
O(n) Space, O(nlogn) Time
O(n) Space, O(n) Time
Tags Expand 
Two Pointers Sort Hash Table Array Airbnb Facebook
*/

/*
Thoughts:
    Using a HashMap, O(n) space and O(n) time.
    Thinking process:
    Push everything into a HashMap.
    Check if one element exist in the HashMap, if so save it. Meanwhile, save the other one.
    Trick: after adding into the HashMap, we are looking for the 2nd index first. 
        Always check (target - current) from the HashMap. 
        If exist, that means index0 has already been pushed into the HashMap and current value is at index1.
    (key, value) = (numbers[i], i)
    Note: return index+1 because this is not 0-based.
*/
class Solution {
    final static int RESULT_SIZE = 2;
    final static int RESULT_INDEX1 = 0;
    final static int RESULT_INDEX2 = 1;
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        final int[] result = new int[RESULT_SIZE];
        final Map<Integer, Integer> recordMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (recordMap.containsKey(target - nums[i])) {
                result[RESULT_INDEX1] = recordMap.get(target - nums[i]);
                result[RESULT_INDEX2] = i;
                return result;
            } else {
                recordMap.put(nums[i], i);
            }
        }
        return result;
    }
}



//2. O(n) Space O(nlogn) time
/*
    Feels like binary search when looking at O(nlogn)
    1. sort
    2. loop all number
    3. binary search on rest
*/
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return null;
        }
        int[] original = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            original[i] = numbers[i];
        }
        
        Arrays.sort(numbers);
        int start = 0; 
        int end = numbers.length - 1;
        int num1 = -1;
        int num2 = -1;
        while (start != end) {
            int sum = numbers[start] + numbers[end];
            if (sum == target) {
                num1 = numbers[start];
                num2 = numbers[end];
                break;
            }else if (sum < target) {
                start++;
            } else {
                end--;
            }
        }

        //Find the num1,num2 in original array and record the index
        int[] rst = new int[2];
        rst[0] = -1;
        rst[1] = -1;
        for (int i = 0; i < original.length; i++) {
            if (original[i] == num1 || original[i] == num2) {
                if (rst[0] == -1) {
                    rst[0] = i + 1;
                } else {
                    rst[1] = i + 1;
                    break;
                }
            }
        }
        return rst;
    }
}









```
/*
Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

Note
You may assume that each input would have exactly one solution

Example
numbers=[2, 7, 11, 15], target=9

return [1, 2]

Challenge
1. O(1) Space, O(nlogn) Time

2. O(n) Space, O(n) Time

Tags Expand 
Array Two Pointers


Using a HashMap, O(n) space and O(n) time.
Thinking process:
Push everything into a HashMap.
Check if one element exist in the HashMap, if so save it. Meanwhile, save the other one.
Trick: after adding into the HashMap, we are looking for the 2nd index first. This is particularly because the way we write the code in optimized form:
	always check (target - current) from the HashMap. If exist, that means index0 has already been pushed into the HashMap and current value is at index1.
(key, value) = (numbers[i], i)
Note: return index+1 because this is not 0-based.
*/

public class Solution {
    /*
     * @param numbers : An array of Integer
     * @param target : target = numbers[index1] + numbers[index2]
     * @return : [index1 + 1, index2 + 1] (index1 < index2)
     */
     //Using HashMap
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return null;
        }
        int[] rst = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                rst[0] = map.get(target - numbers[i]) + 1;
                rst[1] = i + 1;
            } else {
                map.put(numbers[i], i);
            }
        }
        return rst;
    }
}



//2. O(1) Space O(nlogn) time
//TODO


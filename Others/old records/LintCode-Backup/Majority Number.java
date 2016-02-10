/*
Given an array of integers, the majority number is the number that occurs more than half of the size of the array. Find it.

Example
For [1, 1, 1, 1, 2, 2, 2], return 1

Challenge
O(n) time and O(1) space

Tag: Enumeration

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


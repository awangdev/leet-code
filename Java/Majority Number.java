E

Majority Number是指超半数。任何超半数，都可以用0和1count：是某个number，+1；不是这个number,-1. 

注意：assume valid input, 是一定有一个majority number的。否则此法不成。[1,1,1,2,2,2,3]是个invalid input,结果是3，当然也错了。

Majority Number II，超1/3, 那么就分三份处理，countA, countB来计算最多出现的两个。

Majority Number III, 超1/k, 那么自然分k份。这里用到 HashMap。

```

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
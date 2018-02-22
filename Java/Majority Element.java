E
1519318460

方法1: Vote 计数, vote++, vote--到最后剩下的就是winner. Time O(n), Space O(1)
Majority Number是指超半数. 超半数的数字, 最后都会至少有vote>=1: match current majority number，vote++；if not, vote--. 
注意：assume valid input, 是一定有一个majority number的。否则此法不成。[1,1,1,2,2,2,3]是个invalid input,结果是3，当然也错了。

方法2: HashMap count occurance. Time, Space: O(n)

方法3: Bit manipulation. 还没有做.

Related Problems:
Majority Number II，超1/3, 那么就分三份处理，countA, countB来计算最多出现的两个。

Majority Number III, 超1/k, 那么自然分k份。这里用到 HashMap。

```
/*
LeetCode: Majority Element
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
 */

/*
Thoughts:
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
Thoughts:
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
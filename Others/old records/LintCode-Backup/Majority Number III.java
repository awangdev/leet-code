/*
Given an array of integers and a number k, the majority number is the number that occurs more than 1/k of the size of the array. Find it.

Note
There is only one majority number in the array.

Example
For [3,1,2,3,2,3,3,4,4,4] and k = 3, return 3

Challenge
O(n) time and O(k) extra space

Thinking process:
Very similar to Majority I, II, except we can use a HashMap to store information (value, count).
Having a HashMap we have one advantage: when checking if current value matches any previously recorded val, just do a map.containsKey(val).
When a pair in hashMap has count ==0, remove this pair.
Note, to learn how to use iterator in HashMap.
Note: when map.containsKey(currVal) == false, the code checks map.size() == k before count-- perations. This is because:
We first need to put k candidates into HashMap before we count-- from all of them. If map.size() < k, that means we still have free spot for candidate in the HashMap, so in this case we do: map.put(candidateKey, 1).
*/


public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: As described
     * @return: The majority number
     */
    public int majorityNumber(ArrayList<Integer> nums, int k) {
        if (nums == null || nums.size() == 0) {
            return -1;
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (Integer num : nums) {
            if (map.containsKey(num)) {//Found duplicates, count++
                map.put(num, map.get(num) + 1);
            } else {
                if (map.size() == k) {//All candidates added, do count--
                    Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry<Integer, Integer> entry = iter.next();
                        if (entry.getValue() - 1 == 0) {
                            iter.remove();
                        } else {
                            map.put(entry.getKey(), entry.getValue() - 1);
                        }
                    }//While
                } else {
                    map.put(num, 1);
                }
            }
        }//For
        
        int result = 0;
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }
}


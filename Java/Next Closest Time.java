M
1527751968
tags: String, Basic Implementation, Enumeration

给一个时间string"12:09", 用里面的4个integer组合成其他时间string, 目标找最小的next time.

如果组合出的time string 在input time之前, 默认 + 24 hours.

#### String
- enumerate all candidates and filter to keep the correct ones
- String.compareTo(string) -> gives lexicographical comparision

```

/*
Given a time represented in the format "HH:MM", 
form the next closest time by reusing the current digits. 
There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid. 
For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

Example 1:

Input: "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  
It is not 19:33, because this occurs 23 hours and 59 minutes later.
Example 2:

Input: "23:59"
Output: "22:22"
Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. 
It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.

*/

/*
1. parse the input to digits, Build hh set, mm set.
2. cross match hh x mm
3. compare each valid time against input time, mark the closest
*/
class Solution {
    public String nextClosestTime(String time) {
        // check edge
        if (time == null || time.length() == 0) {
            return time;
        }

        // parse into 4 digits
        Set<Integer> nums = new HashSet<>();
        for (int i = 0; i < time.length(); i++) {
            if (time.charAt(i) != ':') {
                nums.add(time.charAt(i) - '0');
            }
        }

        // use the 4 digits to build hh set, mm set: respect hh:mm limit
        Set<String> hh = new HashSet<>();
        for (int i : nums) {
            for (int j : nums) {
                int num = i * 10 + j;
                if (num >= 0 && num < 24) {
                    hh.add(i + "" + j);
                }
            }
        }

        Set<String> mm = new HashSet<>();
        for (int i : nums) {
            for (int j : nums) {
                int num = i * 10 + j;
                if (num >= 0 && num < 60) {
                    mm.add(i + "" + j);
                }
            }
        }

        String result = time;
        int timeValue = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
        int diff = Integer.MAX_VALUE;
        // cross build hh x mm, and compare with time. +24 for smaller items
        for (String h : hh) {
            for (String m : mm) {
                String newTime = h + ":" + m;
                int value = 0;
                if (newTime.compareTo(time) <= 0) {
                    value = Integer.parseInt(h) * 60 + Integer.parseInt(m) + 1440;
                } else {
                    value = Integer.parseInt(h) * 60 + Integer.parseInt(m);
                }
                if (value - timeValue < diff) {
                    diff = value - timeValue;
                    result = newTime;
                }
            }
        }

        return result;
    }
}

```
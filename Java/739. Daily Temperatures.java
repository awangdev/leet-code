M
tags: Stack, Hash Table, Monotonous Stack
time: O(n)
space: O(n)

#### Method1: Monotonous Stack
- Goal: given a index i, want right-side closest & higer number
- Draw example: right-most number at base, and builds up monotonous stack (mountain shape)
    - add smaller item on top of stack
    - keep popping if peek is higher than incoming
- space: O(n), time:O(n)

#### Method2: `Map <fixed value(temperature), Index>`, kinda of like bucket sort
- Refernece: https://leetcode.com/problems/daily-temperatures/solution/
- From right side: 
    - 1) record tempIndex[currTemp] = i; 
    - 2) Brutle find smallest temp index in range [currTemp + 1, 100] and record as result
```
/**
Given a list of daily temperatures T, return a list such that, for each day in the input, 
tells you how many days you would have to wait until a warmer temperature. 
If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
*/

/*
Method1: Monotonous Stack
- Goal: given a index i, want right-side closest & higer number
- Draw example: right-most number at base, and builds up monotonous stack (mountain shape)
    - add smaller item on top of stack
    - keep popping if peek is higher than incoming
- space: O(n), time:O(n)
*/
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] rst = new int[n];
        Stack<Integer> stack = new Stack<>(); // note: store indexes
        
        for (int i = n - 1; i >= 0; i--) {
            int num = T[i];
            // mono stack
            while (!stack.isEmpty() && num >= T[stack.peek()]) stack.pop();
            // process right-side closest & higer number
            if (!stack.isEmpty() && num < T[stack.peek()]) rst[i] = stack.peek() - i;
            else rst[i] = 0;
            
            // add curr
            stack.push(i);
        }
        
        return rst;
    }
}

/*
#### Method2: Map <fixed value(temperature), Index>
- Refernece: https://leetcode.com/problems/daily-temperatures/solution/
- From right side: 1) record tempIndex[currTemp] = i; 2) Brutle find smallest temp index in range [currTemp + 1, 100] and record as result
*/
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] rst = new int[n];
        Integer[] tempIndex = new Integer[101]; // assume top temp is 101
        
        for (int i = n - 1; i >= 0; i--) {
            int num = T[i], warmerIndex = Integer.MAX_VALUE;;
            
            for (int t = num + 1; t <= 100; t++) { // find the smallest index that has temp greater than num
                if (tempIndex[t] != null && tempIndex[t] < warmerIndex) warmerIndex = tempIndex[t];
            }
            
            if (warmerIndex < Integer.MAX_VALUE) rst[i] = warmerIndex - i; // default rst[i] = 0
            tempIndex[num] = i;
        }
        
        return rst;
    }
}
```
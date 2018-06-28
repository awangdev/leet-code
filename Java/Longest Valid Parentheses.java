H
1530168397
tags: Coordinate DP, Stack, String

给一串string, 里面只有`(`, `)`. 找最长valid parentheses 的长度.

#### 1D Coordinate DP
- use dp[i] track local max, maintain global max
- int[] dp. dp[i]: longest valid string that ends on i.
- 结尾是 ')', 2种情况: 1. 刚好s[i-1]是'('; 2. s[i]的')'更前面的一个起始'(' 对应
- 注意, 结尾如果是'('属于不合理情况, 忽略.
- init: dp[0] = 0, 单个char不可能成型.
- 计算顺序: 从左到右, 找local max, maintain global max
- O(n) space, O(n) runtime

#### Stack
- Stack 里面存所有的open/close parentheses.
- 如果遇到stack.top()刚好开合结掉, 就stack.pop().
- 剩下的都是不合理的elements.
- 有点像negatively找 solution: `endIndex - 最后一个failedIndex(stack.pop()) - 1`, 应该就是最后一个succeeded string的长度
- 每次更新 endIndex 为stack.top(), 然后从stack继续找下一个failedIndex
- 所有的length作比较, 就可以找出最长length
- O(n) stack space, O(n) runtime. 应该比dp慢一点, 因为做了2遍O(n)


```

/**
Given a string containing just the characters '(' and ')', 
find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"

*/

// DP, 98%
class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        
        // build && init dp[i]: longest valid string that ends at index i
        int[] dp = new int[n];
        dp[0] = 0;

        // calculate dp && track global max
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = 2 + addPriorLength(i - 1, dp);
                } else {
                    int priorIndex = i - dp[i - 1] - 1; // 1 spot ahead of the known start index of valid string with dp[i-1]
                    if (priorIndex >= 0 && s.charAt(priorIndex) == '(') {
                        dp[i] = dp[i - 1] + 2 + addPriorLength(priorIndex, dp);
                    } 
                }
                max = Math.max(max, dp[i]);
            }
        }

        return max;
    }

    private int addPriorLength(int priorIndex, int[] dp) {
        return (priorIndex - 1 >= 0) ? dp[priorIndex - 1] : 0;
    }
}

// A bit more concise code, 90%
class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        
        // build && init dp[i]: longest valid string that ends at index i
        int n = s.length();
        int[] dp = new int[n];

        // calculate dp && track global max
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '(') continue;

            boolean consecutiveEnd = s.charAt(i - 1) == '(';
            int priorIndex = consecutiveEnd ? i - 1 : i - dp[i - 1] - 1;
            if (priorIndex < 0 || s.charAt(priorIndex) != '(') continue;

            dp[i] = 2 + addPriorLength(priorIndex, dp);
            dp[i] += consecutiveEnd ? 0 : dp[i - 1]; //add length from (priorIndex, i - 1]
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    private int addPriorLength(int priorIndex, int[] dp) {
        return (priorIndex - 1 >= 0) ? dp[priorIndex - 1] : 0;
    }
}

// Stack
class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        // build stack && remove valid pairs
        Stack<Integer> stack = new Stack<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (arr[i] == ')' && !stack.isEmpty() && arr[stack.peek()] == '(') {
                stack.pop();
                continue;
            }
            stack.push(i);
        }

        // go over stack && negatively calculate the longest valid result
        int max = stack.isEmpty() ? n : 0;
        int end = n, start = 0;
        while (!stack.isEmpty()) {
            start = stack.pop();
            max = Math.max(max, end - start - 1);
            end = start;
        }
        // compare very last range [0 ~ end] => length = end
        return Math.max(max, end); 
    }
}


```
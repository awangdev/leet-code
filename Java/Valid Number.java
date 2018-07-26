H
1532500367
tags: Math, String
time: O(n)

分析edge case, 和各种情况, 然后判别是否是valid number

#### 情况总结
- 遇到 `.`, `e`, `+/-`, `int`的几种不同情况
- 分别遇到的顺序不同时候, 结果也不同.
- 这道题更多是分析情况, 然后把edge case enumerate出来, 算法的意义比较少.

```
/*
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true

Note: It is intended for the problem statement to be ambiguous. 
You should gather all requirements up front before implementing one.

Update (2015-02-10):
The signature of the C++ function had been updated. 
If you still see your function signature accepts a const char * argument, 
please click the reload button to reset your code definition.
*/

class Solution {
    public boolean isNumber(String s) {
        if (s == null) return false;
        s = s.trim();
        if (s.length() == 0) return false;
        int i = 0;
        char[] ss = s.toCharArray();
        if (ss[i] == '+' || ss[i] == '-') i++;
        boolean isNum = false, isDot = false, isExp = false;
        for (; i < s.length(); i++) {
            char c = ss[i];
            if (Character.isDigit(c)) {
                isNum = true;
            } else if (c == '.') {
                if (isDot || isExp) return false; // . or e exists already, return false
                isDot = true;
            } else if (c == 'e') {
                if (isExp || !isNum) return false; // e exists, or if no integer ahead of e, return false
                isExp = true;
                isNum = false; // can start re-count integer
            } else if (c == '+' || c == '-') {
                if (i >= 1 && s.charAt(i - 1) != 'e') return false;
            } else {
                return false;
            }
        }

        return isNum;
    }
}
```
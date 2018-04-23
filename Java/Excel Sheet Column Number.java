E
1524455965
tags: Math

#### Math
- 26位的运算, 根据10位运算去思考
- 'A' - 'A' = 0. 所以 char - 'A' + 1 = 题目里的对应数位
- 或者: 26位运算和10位一样:num += 每位的digit * Math.pow(26, 数位号)


```
/*
Given a column title as appear in an Excel sheet, 
return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
*/

/*
Thoughts:
- 26-bits. 
- Char array, head char is at most significant index.
- char - 'a' + 1 = number

*/
class Solution {
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = 0;
        for (char c : s.toCharArray()) {
            int digit = c - 'A' + 1;
            count = count * 26 + digit;
        }
        return count;
    }
}

//3.4.2016 recap
//digit * pow(26, digit position)
public class Solution {
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int num = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int digit = s.charAt(i) - 'A' + 1;
            num += digit * Math.pow(26, s.length() - i - 1);
        }
        return num;
    }
}

```
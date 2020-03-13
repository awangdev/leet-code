M
tags: Math, String
time: O(n)
space: O(n)

#### String 
- Handling use cases
- Parse steps:
    - 0. trim space
    - 1 parse operator
    - 2 trim leading zero
    - 3. get number string
- Validation:
    - 1. max length over max integer length
    - 2. exceed min/max value
- Alternatively, regular expression, but not applicable in interview: if (!str.matches("[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)")).  猛了一点

```



/*
02.02.2016 from leetcode

Implement atoi which converts a string to an integer.

The function first discards as many whitespace characters as necessary 
until the first non-whitespace character is found. Then, starting from this character, 
takes an optional initial plus or minus sign followed by as many numerical digits as possible, 
and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, 
which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, 
or if no such sequence exists because either str is empty or it contains only whitespace characters, 
no conversion is performed.

If no valid conversion could be performed, a zero value is returned.

Note:

Only the space character ' ' is considered as whitespace character.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. 
If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
Example 1:

Input: "42"
Output: 42
Example 2:

Input: "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign.
             Then take as many numerical digits as possible, which gets 42.
Example 3:

Input: "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
Example 4:

Input: "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a numerical 
             digit or a +/- sign. Therefore no valid conversion could be performed.
Example 5:

Input: "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
             Thefore INT_MIN (−231) is returned.
             
*/
/*
- cut space
- validate if operator is prefixed or if letters
- finds all digits into sb
- convert
*/
class Solution {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0 || str.trim().length() == 0) return 0;

        String s = str.trim();
        int i = 0, n = s.length(), maxIntLen = String.valueOf(Integer.MAX_VALUE).length();
        char operator = ' ';
        
        // step1: parse operator
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) operator = s.charAt(i++);
        // step2: parse zero
        while (i < n && s.charAt(i) == '0') i++; // parse 0
        // step3: get number string
        StringBuffer sb = getNumStr(s, i);

        // validation1: max length over max integer length
        if (sb.length() > maxIntLen) return operator == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        // validateion2: exceed min/max value
        long num = Long.parseLong(sb.toString()) * (operator == '-' ? -1 : 1);
        if (num > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (num < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return (int) num;
    }
    
    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
    
    private StringBuffer getNumStr(String s, int i) {
        StringBuffer sb = new StringBuffer();
        if (i >= s.length() || !isDigit(s.charAt(i))) {
            sb.append(0); // leading char validation, return 0 if invalid
            return sb; 
        }
        while (i < s.length()) {
            char c = s.charAt(i++);
            if (!isDigit(c)) break;
            sb.append(c);
        }
        return sb;
    }
}

public class Solution {
    /**
     * @param str: A string
     * @return An integer
     */
    public int atoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        str = str.replaceAll("\\s","");
        if (!str.matches("[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)")) {
            return 0;
        }
        double rst = Double.parseDouble(str);
        if (rst > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (rst < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int)rst;
        }
    }
}


```
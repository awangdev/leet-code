M
tags: Math, String, Basic Implementation
time: O(n)
space: O(n)

#### String, Basic implementation
- Parse each digit based on rules
- 1) parse: analyze the situations
/*
- /1000 -> M's,
- /100: -> C's for (100 - 399), CD(400), DC's for (501 - 899), CM(900+) 
- /10: -> X's (10, 39), XL (40's), LX(51 - 89), XC(90+)
- /1: -> I's for (1-3), IV(4), VI(5-8), IX (9)
*/
- 2) print, print some letter repeatedly. i.e. `III`

```
/**
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.

Example 1:

Input: 3
Output: "III"
Example 2:

Input: 4
Output: "IV"
Example 3:

Input: 9
Output: "IX"
Example 4:

Input: 58
Output: "LVIII"
Explanation: L = 50, V = 5, III = 3.
Example 5:

Input: 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
*/

/*
- Divide by each symbol value to get remain
- translate the remain by symbo combinations
    - create map of 4/9, 40/90, 400/900
- /1000 -> M's,
- /100: -> C's for (100 - 399), CD(400), DC's for (501 - 899), CM(900+) 
- /10: -> X's (10, 39), XL (40's), LX(51 - 89), XC(90+)
- /1: -> I's for (1-3), IV(4), VI(5-8), IX (9)
*/
class Solution {
    public String intToRoman(int num) {
        
        StringBuffer sb = new StringBuffer();
        
        sb.append(parse("M", null, null, num / 1000)); // M's
        num %= 1000;
        
        sb.append(parse("C", "D", "M", num / 100)); // C's
        num %= 100;
        
        sb.append(parse("X", "L", "C", num / 10)); // X's
        num %= 10;
        
        sb.append(parse("I", "V", "X", num)); // I's
        
        return sb.toString();
    }
    
    private String parse(String curr, String mid, String next, int num) {
        if (num == 0) return "";
        if (num <= 3) return repeat(curr, num);
        if (num == 4) return curr + mid;
        if (num <= 8) return mid + repeat(curr, num % 5);
        return curr + next;
    }
    
    private String repeat(String curr, int num) {
        StringBuffer sb = new StringBuffer();
        while (num-- > 0) {
            sb.append(curr);
        }
        return sb.toString();
    }
}
```
E
1533541868
tags: Math, String

#### String 
- 熟悉罗马字母规则     
- 1. 'I V X L C D M' 分别代表的数字     
- 2. 列举combo的情况，需要从原sum里面减掉多加的部分: 'IV, IX'减2, 'XL, XC'减20, 'CD, CM'减200. 
- Leading `I(1*2)`, `X(10*2)`, `C(100*2)` causes double counting 

https://en.wikipedia.org/wiki/Roman_numerals

```
/*
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.

Hide Company Tags Bloomberg Uber
Hide Tags Math String
Hide Similar Problems (M) Integer to Roman

*/

/*
Thoughts:
Know the rules of roman number:
I: 1
V: 5
X: 10
L: 50
C: 100
D: 500
M: 1000

There are usual combinations:
IV: 4
IX: 9
XL: 40
XC: 90
CD: 400
CM: 900
When the combo occurs, for instance:
I V -> 1 + 5 = 6
IV  -> 4
=> extra '2'
If we sum each individual digits, then we need to extract the extra '2'

Similar for other examples:
C D -> 100 + 500 = 600
DC  -> 400
=> extra '200'

The solution is:
add all digits up, and remove the number of combo occurrances
*/
class Solution {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            switch(s.charAt(i)) {
                case 'I': sum += 1; break;
                case 'V': sum += 5; break;
                case 'X': sum += 10; break;
                case 'L': sum += 50; break;
                case 'C': sum += 100; break;
                case 'D': sum += 500; break;                
                case 'M': sum += 1000; break;
                default: return 0;
            }
        }
        
        sum -= countCombo("IV", s) * 2;
        sum -= countCombo("IX", s) * 2;
        sum -= countCombo("XL", s) * 2 * 10;
        sum -= countCombo("XC", s) * 2 * 10;
        sum -= countCombo("CD", s) * 2 * 100;
        sum -= countCombo("CM", s) * 2 * 100;
        return sum;
    }
    
    private int countCombo(final String pattern, String str) {
        int count = 0;
        while (str.contains(pattern)) {
            count++;
            str = str.substring(str.indexOf(pattern) + pattern.length());
        }
        return count;
    }
}


//switch different char, add all together
//Letter representation, and 'less than' special combos

public class Solution {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            switch(s.charAt(i)) {
                case 'I': num += 1; break;
                case 'V': num += 5; break;
                case 'X': num += 10; break;
                case 'L': num += 50; break;
                case 'C': num += 100; break;
                case 'D': num += 500; break;
                case 'M': num += 1000; break;
                default:
                    return 0;
            }
        }
        
        num -= countMatch(s, "IV") * 2;
        num -= countMatch(s, "IX") * 2;
        num -= countMatch(s, "XL") * 10 * 2;
        num -= countMatch(s, "XC") * 10 * 2;
        num -= countMatch(s, "CD") * 100 * 2;
        num -= countMatch(s, "CM") * 100 * 2;
        
        return num;
    }
    
    public int countMatch(String s, String key) {
        int count = 0;
        while (s.indexOf(key) != -1) {
            count++;
            int i = s.indexOf(key);
            s = s.substring(i + 2);
        }
        return count;
    }

}
```

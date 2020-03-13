E
tags: Math, String
time: O(n)
space: O(1)

#### String 
- 熟悉罗马字母规则     
- 1. 'I V X L C D M' 分别代表的数字     
- 2. 列举combo的情况，需要从原sum里面减掉多加的部分: 'IV, IX'减2, 'XL, XC'减20, 'CD, CM'减200. 
- Leading `I(1*2)`, `X(10*2)`, `C(100*2)` causes double counting 

https://en.wikipedia.org/wiki/Roman_numerals

#### use map to store combinations


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
// Use math to remove double-counted value 
class Solution {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) return 0;
        int sum = getPlainSum(s);
        
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

    private int getPlainSum(String s) {
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
        return sum;
    }
}


// works, but kinda slow because we are building additona O(n) structure
class Solution {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) return 0;
        int sum = 0, i = 0;
        Map<Character, Integer> map = buildMap();
        Map<String, Integer> specialMap = buildSpecialMap();
        for (; i < s.length() - 1; i++) {
            char c = s.charAt(i);
            if (c == 'I' || c == 'X' || c == 'C') {
                String pair = s.substring(i, i+2);
                if(specialMap.containsKey(pair)) {
                    sum += specialMap.get(pair);
                    i++;
                    continue;
                }
            }
            sum += map.get(c);
        }
        if (i < s.length()) {
            sum += map.get(s.charAt(i));
        }
        return sum;
    }

    private Map<String, Integer> buildSpecialMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);
        return map;
    }

    private Map<Character, Integer> buildMap() {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        return map;
    }
}



```

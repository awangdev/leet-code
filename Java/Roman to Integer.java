E

熟悉罗马字母规则     
1. 'I V X L C D M' 分别代表的数字     
2. 'IV, IX'减1, 'XL, XC'减10, 'CD, CM'减100.     

```
/*
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.

Hide Company Tags Bloomberg Uber
Hide Tags Math String
Hide Similar Problems (M) Integer to Roman

*/


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

E

'A' - 'A' = 0. 所以 char - 'A' + 1 = 题目里的对应数位。      
26位运算和10位一样嘛，num += 每位的digit * Math.pow(26, 数位号)。


```
/*
Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
*/


public class Solution {//ABC -> 'A', 'B', 'C'
    public int titleToNumber(String s) {//S = AA
        int rst = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {//i = 0,1,2 // (char c : arr)
            rst = rst * 26 + arr[i] - 'A' + 1;//rst =1, 26 + 1 = 27, 
        }
        return rst;
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
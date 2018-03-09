E
1519709164
tags: Math, String

方法一:土办法没技术，把binary换成数字，加起来，再换成binary。如果input很大，那么很可能int,long都hold不住。不保险。

方法二:一般方法，string化为charArray,然后逐位加起，最后记得处理多余的一个carry on
注意: 需要从末尾加起来, 所以要用一个diff来帮助  shortArray[i-diff] 指向 shortArray的相对应index.

```
/*
Add Binary

Given two binary strings, return their sum (also a binary string).

Example
a = 11

b = 1

Return 100

Tags Expand 
String Binary Facebook
*/

/*
Thoughts:
Can't just convert to int because of Integer.MAX_VALUE limitation.
Convert to char, and add up all chars
*/
class Solution {
    public String addBinary(String a, String b) {
        if (a == null || b == null) {
            return a == null ? b : a;
        }
        int m = a.length();
        int n = b.length();
        int size = Math.max(m, n);
        char[] result = new char[size];
        char[] longArray = m > n ? a.toCharArray() : b.toCharArray();
        char[] shortArray = m > n ? b.toCharArray() : a.toCharArray();
        int diff = longArray.length - shortArray.length; // important
        int carry = 0;
        for (int i = size - 1; i >= 0; i--) {
            int sum = carry + (longArray[i] - '0');
            if (i - diff >= 0) {
                sum += (shortArray[i - diff] - '0');
            }
            carry = sum / 2;
            result[i] = (char)(sum % 2 + '0');
        }
        
        if (carry != 0) {
            return "1" + new String(result);
        }
        return new String(result);
    }
}


/*
    Thought:
    Use binary property, add all and move carry-on
    String to charArray
*/

public class Solution {
    public String addBinary(String a, String b) {
        if (a == null || b == null || a.length() == 0 || b.length() == 0) {
            return null;
        }
        char[] shortArr = a.length() < b.length() ? a.toCharArray() : b.toCharArray();
        char[] longArr = a.length() < b.length() ? b.toCharArray() : a.toCharArray();
        int carry = 0;
        int shortVal = 0;
        int nextCarry = 0;
        int diff = longArr.length - shortArr.length;
        for (int i = longArr.length - 1; i >= 0; i--) {
            shortVal = (i - diff) >= 0 ? shortArr[i - diff] - '0': 0;
            nextCarry = (longArr[i] - '0' + shortVal + carry) / 2;
            longArr[i] =(char)((longArr[i] - '0' + shortVal + carry) % 2 + '0');
            carry = nextCarry;
        }

        if (carry != 0) {
            return "1" + new String(longArr);
        }
        
        return new String(longArr);
    }
}


/*
//Thougths:
1. Turn string binary format into integer
2. add integer
3. turn integer into binary string
Note: this just test if we know how to manipulate string/binary/Integer
*/

public class Solution {
    /**
     * @param a a number
     * @param b a number
     * @return the result
     */
    public String addBinary(String a, String b) {
        if (a == null || b == null || a.length() == 0 || b.length() == 0) {
            return null;
        }
        int decimalA = Integer.parseInt(a, 2);
        int decimalB = Integer.parseInt(b, 2);
        
        int sum = decimalA + decimalB;
        
        return Integer.toBinaryString(sum);
    }
}



```
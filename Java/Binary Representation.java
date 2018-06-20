H
1529478003
tags: String, Bit Manipulation

#### String
- 首先要分两半解决，断点是'.': str.split("\\.");
- Integer那一半好弄，whie loop里: num%2, num/2. 做一个 `parseInteger()` function
- Decimal那边复杂点. 做一个 `parseDecimal()` function:
- bit == 1的数学条件: 当下num * 2 >= 1。 更新: num = num * 2 - 1;
- bit == 0的数学条件: num * 2 < 1. 更新: num = num * 2

#### 注意
- num是 double, 小数在 `num = num * 2 - 1` 的公式下可能无限循环
- 因此check: num重复性，以及binary code < 32 bit.
- 所以题目也才有了32BIT的要求!

```
/*
Given a (decimal - e.g. 3.72) number that is passed in as a string, 
return the binary representation that is passed in as a string. 
If the fractional part of the number can not be represented 
accurately in binary with at most 32 characters, return ERROR.

Example
For n = "3.72", return "ERROR".

For n = "3.5", return "11.1".

Tags Expand 
String Cracking The Coding Interview Bit Manipulation

*/

/*

Thoughts:
Expan the value for binary representation:
8, 4, 2, 1, 0.5, 0.25 ... etc
3, 2, 1, 0, -1, -2, ... etc

1. Think of a good method to split the number into front . end part.
2. Deal with integer transforming into binary: integer divided by 2 see if != 0 
3. Deal with double transforming into binary: times 2 see if >= 1

Split: split string by '.'.
Checkfloat first: if float part is '0' or "", can just move on the integer part.

Note: str.split("\\.")
Note2: use a set to prevent infinite loop on float: 
for example: 2x - 1 = x -> x = 1. that will cause infinite loop.

*/

public class Solution {
    public String binaryRepresentation(String n) {
        if (n.length() == 0 || n.equals("0")) {
            return "0";
        }
        //If no '.', no decimal, just parseInteger
        if (n.indexOf(".") == -1) {
            return parseInteger(n);
        }
        //Split the string by '.'
        String[] strs = n.split("\\.");
        //Deal with decimal first. 
        String decimal = parseDecimal(strs[1]);
        //If not applicable, it won't work, don't need to calculate integer part. Just return ERROR.
        if (decimal.equals("ERROR")) {
            return decimal;
        }
        //Deal with integer part
        if (decimal.length() == 0 || decimal.equals("0")) {
            return parseInteger(strs[0]);
        } else {
            return parseInteger(strs[0]) + "." + decimal;
        }
    }

    public String parseInteger(String n) {
        if (n.length() == 0 || n.equals("0")) {
            return n;
        }
        int num = Integer.parseInt(n);
        StringBuffer sb = new StringBuffer();
        while (num != 0) {
            sb.insert(0, num % 2);//mod(2) -> binary representation
            num = num / 2;//小时候转换二进制也是这样。
        }
        return sb.toString();
    }
    // A little bit math, but implemtable.
    public String parseDecimal(String n) {
        if (n.length() == 0 || n.equals("0")) {
            return "";
        }
        //A doublem must be able to catch it. If not, that is way bigger than 32 bit.
        double num = Double.parseDouble("0." + n);
        //Check existance
        HashSet<Double> set = new HashSet<>();
        StringBuffer sb = new StringBuffer();
        while (num > 0) {
            if (sb.length() > 32 || set.contains(num)) {
                return "ERROR";
            }
            set.add(num);
            //For decimal: binary code on one spot == 1, means: num * 2 - 1 > 0
            if (num * 2 >= 1) {
                sb.append("1");
                num = num * 2 - 1;
            } else {
                sb.append("0");
                num = num * 2;
            }
        }
        return sb.toString();
    }
}
```
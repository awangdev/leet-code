M
tags: Math, String
time: O(mn)
space: O(mn)

给两个integer String, 求乘积

#### String calculation, basic implementation
- let num1 = multipier, num2 = base. mutiply and save into int[m + n]. 
    - Loop over num1, each row num1[x] * num2, save to correct index (i + j + 1)
    - Note: skip leading '0' during output, but do not delete string "0"
    - time,space O(mn)
- Option1: Calculate carry on the fly
    - index `curr = i + j + 1`, left index `left = curr - 1`, since we start calculation from end of the array.
    - **we only touch right side of the array once**, so we can move the carry off from it, and carry to left index
    - code is concise
- Option2: save product first without calculating carry
    - save product in each int index
    - calculate carry on rst[] and `sb.insert(0, c)` (since we start from end of rst)
    - this is actaully faster than Option1, somehow.

#### Reverse, calculate from index 0, and reverse back
- 1. 数字‘123’， 在数组里面， index == 0 是 ‘1’。 但是我们平时习惯从最小位数开始乘积，就是末尾的'3'开始。
- 所以！翻转两个数字先！我去。这个是个大坑。
- 2. 乘积product，和移动Carrier都很普通。
- 3. ！！最后不能忘了再翻转。
- 4. 最后一个看坑。要是乘积是0，就返回‘0’。 但是这个其实可以在开头catch到没必要做到结尾catch。
- 用到几个StringBuffer的好东西: reverse(), sb.deleteCharAt(i)   
- 找数字，或者26个字母，都可以: s.charAt(i) - '0'; s.charAt(i) - 'a';

```
/*
Given two non-negative integers num1 and num2 represented as strings, 
return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
*/

// Same concept, slight concise
class Solution {
    public String multiply(String num1, String num2) {
        if (num1 == null || num1.length() == 0) return num2;
        if (num2 == null || num2.length() == 0) return num1;
        
        int m = num1.length(), n = num2.length();
        int[] rst = new int[m + n];

        // multiply without carry handling, save from the end index
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int curr = i + j + 1, left = curr - 1;
                rst[curr] += toInt(num1, i) * toInt(num2, j);
                rst[left] += rst[curr] / 10;
                rst[curr] %= 10;
            }
        }
        
        // calc carry from end of rst
        StringBuffer sb = new StringBuffer();
        for (int num : rst) {
            if (!(sb.length() == 0 && num == 0)) { // skip front '0'
                sb.append(toChar(num));
            }
        }
        
        return sb.length() == 0 ? "0" : sb.toString();
    }
    
    private char toChar(int num) {
        return (char)(num + '0');
    }
    private int toInt(String s, int x) {
        return s.charAt(x) - '0';
    }
}

/*
num can be different length
- let num1 = multipier, num2 = base. 
- mutiply and save into int[m + n], without carry. Loop over num1, each row num1[x] * num2
- calculate carry and reverse result (remove leading '0')
*/
class Solution {
    public String multiply(String num1, String num2) {
        if (num1 == null || num1.length() == 0) return num2;
        if (num2 == null || num2.length() == 0) return num1;
        
        // convert, reverse
        int m = num1.length(), n = num2.length();
        int[] rst = new int[m + n];

        // multiply w/o carry handling, save from the end index
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                rst[i + j + 1] += toInt(num1, i) * toInt(num2, j);
            }
        }
        
        // calc carry from end of rst
        StringBuffer sb = new StringBuffer();
        for (int i = rst.length - 1; i > 0; i--) {
            rst[i - 1] += rst[i] / 10;
            rst[i] %= 10;
            sb.insert(0, toChar(rst[i]));
        }
        sb.insert(0, toChar(rst[0]));
        
        while (sb.length() > 1) {
            if (sb.charAt(0) != '0') break;
            sb.deleteCharAt(0);
        }
        
        return sb.toString();
    }
    
    private char toChar(int num) {
        return (char)(num + '0');
    }
    private int toInt(String s, int x) {
        return s.charAt(x) - '0';
    }
}




/*
Previous: too complicated
    Thoughts:
    1. too long to multiply int. so convert to int[]
    2. Multiply by definition:
        a. create a product[] of num1.size() + num2.size() - 1
        b. catches each product[i + j]
    3. for loop on product array again, to carry over the carries

    if both null, return null.
    if both "", return ""
    
    O(m + n)
*/
public class Solution {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return "";
        } else if (num1.length() == 0 || num2.length() == 0) {
            return num1.length() == 0 ? num2 : num1;
        } else if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        //reverse string, so to calculate from 0 base. easier to calculate
        num1 = new StringBuffer(num1).reverse().toString();
        num2 = new StringBuffer(num2).reverse().toString();
     
        //product array. extra leading space for carriers
        //normally just need num1.length() + num2.length() -1
        int[] product = new int[num1.length() + num2.length()];
        
        //Calculate the product normally
        for (int i = 0; i < num1.length(); i++) {
        	int a = num1.charAt(i) - '0';
            for (int j = 0; j < num2.length(); j++) {
            	int b = num2.charAt(j) - '0';
                product[i + j] += a * b;
            }
        }
        
        //calcualte and output
        //remember, now the string is reversed calculated. 
        //so every time, add to index 0. so it will all reverse back; OR, append, and reverse later.
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < product.length; i++) {
           	int number = product[i] % 10;
           	int carrier = product[i] / 10;
           	sb.append(number);
            if (i < product.length - 1) {
                product[i + 1] += carrier;
            } 
        }
        sb.reverse();
        //trim leading 0's
        while (sb.length() > 0 && sb.charAt(0) == '0') {
        	sb.deleteCharAt(0);
        }

        return sb.toString();    
    }
}





```
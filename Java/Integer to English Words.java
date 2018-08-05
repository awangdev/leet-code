H
1533443082
tags: Math, String, Enumeration

给一个小于 Integer.MAX_VALUE (2^31 - 1) 的数字, 转换成英语. (不需要加 'and')

#### String
- 基本implementation
- `分类讨论`: thounsand, million, billion.  `3个数字一格`.
- 用array枚举 token
- 运用 % 和 / 来找到每个分段的英语翻译
- 3-digit 的部分, 可以用一个helper funtion来找到结果, 每段的处理方法都是一样的

#### 注意
- StringBuffer 更有效率! `sb.insert(0, xxx)` append在sb前面
- 注意加 " " 的时候, 如果多余, 要`trim()`
- 注意, 小于20的数字, 有自己的特殊写法, 需要额外handle
- 这道题目就是要细致`耐心`, 几乎么有什么算法, 就是想要写的efficient并且正确, 需要很小心


```
/*
Convert a non-negative integer to its english words representation. 
Given input is guaranteed to be less than 2^31 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Hint:

Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)

Tags: Math, String
Similar Problems: (M) Integer to Roman

*/

/*
Thoughts:
1. Find the max number 2^31 - 1  = 2,147,483,647, maximum is 'Billion'
2. Break into 3-digt parts
3. Translate each 3-digit number
4. Be carefull with numbers < 20, && 10's that's >= 20
*/
class Solution {
    public String[] v1 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    public String[] v2 = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    public String[] v3 = {"", "Thousand ", "Million ", "Billion "};
    public String numberToWords(int num) {
        if (num < 0)  return "";
        if (num == 0) return "Zero";

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            int partial = num % 1000; // Obtain smaller 3-digit section
            if (partial > 0) { // Append suffix depending on i, where v3[0] = "";
                sb.insert(0, calcPartialNumber(partial) + " " + v3[i]);
            }
            num /= 1000;
        }
        
        return sb.toString().trim();
    }
    
    private String calcPartialNumber(int num) {
        StringBuffer sb = new StringBuffer();
        if (num >= 100) {
            int hund = num / 100;
            sb.append(v1[hund] + " Hundred ");
            num = num % 100;
        }

        if (num < 20) {
            sb.append(v1[num] + " ");
        } else {
            int numTens = num / 10;
            int numDigit = num % 10;
            sb.append(v2[numTens] + " " + v1[numDigit] + " ");
        }

        return sb.toString().trim();
    }
}


/**
Previous notes:

2^31 - 1  = 2,147,483,647
Trillion, Billion, Million, Thousand, Hundred, Ninty .... Ten, Nine ... One, Zero.

1. Break the words to up to 4 parts: new break[4] by /(1000 ^ i)
2. For each i, deal with that 3-digit number in break[i]
3. Append corresponding words for each break[i]

Special case:
zero
000 in one break[i]: skip the whole thing
*/


public class Solution {
    public String[] v1 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    public String[] v2 = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    public String numberToWords(int num) {
        if (num < 0) {
            return "";
        }
        if (num == 0) {
            return "Zero";
        }
        String rst = "";
        for (int i = 0; i < 4; i++) {
            int partial = num  - (num / 1000) * 1000;
            if (partial > 0) {
                rst = helper(partial, i) + " " + rst;
            }
            num /= 1000;
        }
        while (rst.charAt(rst.length() - 1) == ' ')  {
            rst = rst.substring(0, rst.length() - 1);
        }
        return rst;
    }

    public String helper(int num, int i) {
        String str = "";
        if (num >= 100) {
            int hund = num / 100;
            str = v1[hund] + " Hundred ";
            num = num % 100;
        }

        if (num < 20) {
            str += v1[num] + " ";
        } else {
            int numTens = num / 10;
            int numDigit = num % 10;
            str += v2[numTens] + " ";
            str += v1[numDigit] + " ";
        }
        
        while (str.charAt(str.length() - 1) == ' ')  {
            str = str.substring(0, str.length() - 1);
        }
        
        //depending on i:
        switch (i) {
            case 1 : 
                str += " Thousand";
                break;
            case 2 :
                str += " Million";
                break;
            case 3 :
                str += " Billion";
                break;
        }

        return str;
    }
}
```

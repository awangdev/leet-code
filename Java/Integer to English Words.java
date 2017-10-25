/*
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.

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

Thoughts:
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
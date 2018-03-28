E
1522213680
tags: String, Basic Implementation

介绍一种count数字的方法, 然后每一行读出上一行的结果, 一行一行推算. 问nth行是啥样?

#### Basic Implementation
- 主要是题意很难理解, 非常misleading, 等到看明白题目, 其实没有什么算法要求.
- Count duplicates and print

```
/*
The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

Example 1:

Input: 1
Output: "1"
Example 2:

Input: 4
Output: "1211"

*/

/*
Thoughts: Don't get confused about the index number of (1 ~ 5) from the questions,
it's not trying to encode integer.
nth 'term' means nth row. Each row is constructed based on last row.
Count the occurance of same digit and print it.
*/
class Solution {
    public String countAndSay(int n) {
        if (n <= 1) {
            return n + "";
        }
        String curr = "1";
        for (int i = 2; i <= n; i++) {
            int count = 1;
            char c = curr.charAt(0);
            int size = curr.length();
            StringBuffer sb = new StringBuffer();
            for (int j = 1; j < size; j++) {
                if (curr.charAt(j) == curr.charAt(j - 1)) {
                    count++;
                } else {
                    sb.append(count + String.valueOf(c));
                    c = curr.charAt(j);
                    count = 1;
                }
            }
            sb.append(count + String.valueOf(c)); // append end letter for each row
            curr = sb.toString();
        }
        
        return curr;
    }
}

/*
Previous notes:

1. Set up initial value '11'
2. use while loop to build on past variable
3. In each while loop case, break the string into charArray, count and name mark the type
4. In for loop: when different, append string (count+type); when same, count++.
*/
public class Solution {
    /**
     * @param n the nth
     * @return the nth sequence
     */
    public String countAndSay(int n) {
        if (n <= 1) {
            return n + "";
        }
        String str = "11";
        int ind = 2;
        while (ind < n) {
            StringBuffer sb = new StringBuffer();
            char[] arr = str.toCharArray();
            int count = 1;
            int type = Character.getNumericValue(arr[0]);
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] == arr[i - 1]) {
                    count++;
                } else {
                    sb.append(count + "" + type);
                    type = Character.getNumericValue(arr[i]);
                    count = 1;
                }
            }
            ind++;
            sb.append(count + "" + type);
            str = sb.toString();
        }
        return str;
    }
}

```
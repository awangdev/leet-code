E

方法1: 问清情况，一点一点把case都涉及到。

方法2: 用regular expression。if (!str.matches("[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)")).  猛了一点

```
/*
Implement function atoi to convert a string to an integer.

If no valid conversion could be performed, a zero value is returned.

If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.

Example
"10" => 10

"-1" => -1

"123123123123123" => 2147483647

"1.0" => 1

Tags Expand 
Basic Implementation String

*/


/*
02.02.2016 from leetcode
Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
*/
public class Solution {
    public int myAtoi(String str) {
        
        if (str == null) {
            return 0;
        } 
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }

        char[] arr = str.toCharArray();
        String rst = "";
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (i == 0 && (c == '+' || c == '-')) {
                rst += c;
            } else if (c < '0' || c > '9') {
                break;
            } else {
                rst += c;
            }
        }
        if (rst.length() == 0 || rst.equals("+") || rst.equals("-")) {
            return 0;
        }
        if (rst.length() > 11) {
            return rst.charAt(0) == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        long largeNum = Long.parseLong(rst);
        if (largeNum > Integer.MAX_VALUE || largeNum < Integer.MIN_VALUE) {
            return largeNum > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        return Integer.parseInt(rst);   
    }
}



/*

Thoughts:
First idea: why not using Integer.parseInt(str)? Maybe that's too costly, and maybe it does not over all possible integers?
Can we just use a Long.parseLong(str) ?

Issues to check:
Long is not enough, because we might be getting decimal point. So we can use Double here.
String might have space: remove all " "
String might have char of other kind: check each individual char if isNaN()

It looks like somewhere we'd need to use regular exp to search for pattern, and remove space.

Note: need to ask if things like '21lintcode' can be considered as partial-integer and return 21. This is a more complex case, even after reg exp.

*/

public class Solution {
    /**
     * @param str: A string
     * @return An integer
     */
    public int atoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        str = str.replaceAll("\\s","");
        if (!str.matches("[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)")) {
            return 0;
        }
        double rst = Double.parseDouble(str);
        if (rst > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (rst < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int)rst;
        }
    }
}


```
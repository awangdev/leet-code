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
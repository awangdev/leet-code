E
tags: String, Basic Implementation, Math
time: O(n)
space: O(n)

#### Two Pointer 
- Use i, j to process from end of 2 strings
- handle edge case for i, j
    - if i < 0, its num = 0 (since we are doing sum, blindly setting 0 is okay)
- Note: `sb.insert(0, x)` is much slower than doing a final `sb.reverse()`

#### If manually convertin to int[]
1. when converting to int[], remember to reverse string.
1. when converting to int[], remember to reserve extra space for carry

```
/**
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
*/
class Solution {
     public String addStrings(String num1, String num2) {
         StringBuffer sb = new StringBuffer();
         int carry = 0;
         int i = num1.length() - 1, j = num2.length() - 1;

         // carefully handle index i/j pointer.
         for (; i >= 0 || j >= 0 || carry == 1; i--, j--) { // can switch to a while loop
             int x = i < 0 ? 0 : (num1.charAt(i) - '0');
             int y = j < 0 ? 0 : (num2.charAt(j) - '0');
             int sum = x + y + carry;
             sb.append(sum % 10);
             carry = sum / 10;
         }

         return sb.reverse().toString();
     }
}

/*
- turn into char, and to int, then add from end
- turn final result of int[] into string
*/
class Solution {
     public String addStrings(String num1, String num2) {
        int[] maxNums, minNums;
        if (num1.length() <= num2.length()) {
            minNums = convertToInt(num1);
            maxNums = convertToInt(num2);
        } else { 
            minNums = convertToInt(num2);
            maxNums = convertToInt(num1);
        }
        
        int carry = 0;
        for (int i = 0; i < minNums.length; i++) {
            int sum = minNums[i] + maxNums[i] + carry;
            maxNums[i] = sum % 10;
            carry = sum / 10;
        }
        for (int i = minNums.length; i < maxNums.length; i++) {
            int sum = maxNums[i] + carry;
            maxNums[i] = sum % 10;
            carry = sum / 10;
        }
        return convertToString(maxNums);
    }
    
    // reverse string gand convert to int[], leave 1 open spot
    private int[] convertToInt(String num) {
        if (num == null || num.length() == 0) {
            return new int[1];
        }
        int n = num.length();
        int[] nums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            nums[i] = num.charAt(n - i - 1) - '0';
        }
        return nums;
    }
    
    // cut the one possible leading zero, and reverse the string
    private String convertToString(int[] result) {
        int n = result[result.length - 1] == 0 ? result.length - 1: result.length;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            char c = (char)('0' + result[n - i - 1]);
            sb.append(c);
        }
        return sb.toString();
    }
}
```
E
tags: Bit Manipulation

#### Unsigned Shift, Mask
- Move pointer: move digit after process 4 bits. 
    - `>>>` Unsigned right shift
    - always fills 0 irrespective of the sign of the number
- Mas: `num & 0xf` = `num & 15`

```
/*
Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.

Note:

All letters in hexadecimal (a-f) must be in lowercase.
The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
The given number is guaranteed to fit within the range of a 32-bit signed integer.
You must not use any method provided by the library which converts/formats the number to hex directly.
Example 1:

Input:
26

Output:
"1a"
Example 2:

Input:
-1

Output:
"ffffffff"
*/

/*

each time we take a look at the last four digits of
            binary verion of the input, and maps that to a hex char
            shift the input to the right by 4 bits, do it again
            until input becomes 0.
*/
class Solution {
    public String toHex(int num) {
        if (num == 0) return "0";
        
        StringBuffer sb = new StringBuffer();
        while (num != 0) {
            int digit = num & 0xf; // same as num & 15
            num >>>= 4;
            sb.insert(0, convert(digit));
        }
        return sb.toString();
    }
    
    private String convert(int digit) {
        if (digit <= 9) return String.valueOf((char)(digit + '0'));
        return String.valueOf((char)(digit - 10 + 'a'));
    }
}
```
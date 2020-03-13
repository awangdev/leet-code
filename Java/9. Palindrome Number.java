E
tags: Math

#### Reverse half of the number
- build reversed integer 直到midpoint, where x <= reverse
- 如果input双数: x == reverse
- 如果input单数 (而且x>reverse): x == reverse/10

#### Consider palindrome
- optionA: compare digit by digit
- optionB: reverse half of the string/int, and compare with other half.




```

/*
Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Example 1:

Input: 121
Output: true
Example 2:

Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
Follow up:

Coud you solve it without converting the integer to a string?
 */

// Build reverse until mid point
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) return false;
        int reverse = 0;
        while (x > reverse){
            reverse = reverse * 10 + x % 10;
            x = x/10;
        }
        return (x == reverse || x == reverse / 10);
    }
}
```
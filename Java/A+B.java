/*
For given numbers a and b in function aplusb, return the sum of them.

Note
You don't need to parse the input and output. Just calculate and return.

Example
If a=1 and b=2 return 3

Challenge
Can you do it with out + operation?

Clarification
Are a and b both 32-bit integers?

    - Yes.

Thinking process:
Bit operation. Just to remmeber this problem, doing A+B using bit.
*/

class Solution {
    /*
     * param a: The first integer
     * param b: The second integer
     * return: The sum of a and b
     */
    public int aplusb(int a, int b) {
        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }
};


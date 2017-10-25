E

^ 是不完全加法. 每次都忽略了进位。而 & 刚好可以算出需要的所有进位。

那么就，首先记录好进位的数字：carry. 然后 a^b 不完全加法一次。然后b用来放剩下的carry, 每次移动一位，继续加，知道b循环为0为止。

Bit Operation    
Steps: 
   a & b: 每bit可能出得余数       
   a ^ b: 每bit在此次操作可能留下的值，XOR 操作         
   每次左移余数1位，然后存到b, 再去跟a做第一步。loop until b == 0    

(http://www.meetqun.com/thread-6580-1-1.html)

```
/*
Write a function that add two numbers A and B. You should not use + or any arithmetic operators.

Example
Given a=1 and b=2 return 3

Note
There is no need to read data from standard input stream. Both parameters are given in function aplusb, you job is to calculate the sum and return it.

Challenge
Of course you can just return a + b to get accepted. But Can you challenge not do it like that?

Clarification
Are a and b both 32-bit integers?

Yes.
Can I use bit operation?

Sure you can.
Tags Expand 
Cracking The Coding Interview Bit Manipulation


*/

/*
Thought:
    Bit operation. Just to remmeber this problem, doing A+B using bit.
*/
class Solution {
    public int aplusb(int a, int b) {
        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }
};


```
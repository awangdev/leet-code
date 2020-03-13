E
1519709532
tags: Math

方法1: 普通做法就是按照题意, double-while loop把数字加起来. 第一层循环是O(n), 然后第二层循环就少很多数位, overall O(n)

方法2: 找数学规律, 每过9个数字, 取mod就会开始重复, 所以给所有数字取mod 就可以间接找到答案. O(1)

```
/*
Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?
*/

/*
Thoughts:
Keep adding until result < 10
Double-while loop: start with a num, calculate result, then num = result.
*/
class Solution {
    public int addDigits(int num) {
        if (num < 10) {
            return num;
        }
        while (num > 9) {
            int temp = 0;
            while (num != 0) {
                temp += num % 10;
                num = num / 10;
            }
            num = temp;
        }
        return num;
    }
}

class Solution {
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}
```
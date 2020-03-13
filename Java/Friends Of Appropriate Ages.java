M
1531813653
tags: Array, Math

#### Array, Math
- 这个问题更在于问题本身的分析 (而且还有多余条件); 最终的for loop 也比较不standard.
- People younger than 15 cannot make requests due to the first rule.
- From the age of 15, people can make requests to the same age: a[i] * (a[i] - 1) requests.
- People can make requests to younger people older than 0.5 * i + 7: a[j] * a[i] requests.
- The third rule is redundant as the condition is already covered by the second rule.
- TODO: the approach.

```
/*
Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person. 

Person A will NOT friend request person B (B != A) if any of the following conditions are true:

age[B] <= 0.5 * age[A] + 7
age[B] > age[A]
age[B] > 100 && age[A] < 100
Otherwise, A will friend request B.

Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.

How many total friend requests are made?

Example 1:

Input: [16,16]
Output: 2
Explanation: 2 people friend request each other.
Example 2:

Input: [16,17,18]
Output: 2
Explanation: Friend requests are made 17 -> 16, 18 -> 17.
Example 3:

Input: [20,30,100,110,120]
Output: 
Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
 

Notes:

1 <= ages.length <= 20000.
1 <= ages[i] <= 120.
*/

/*
1. bound
age[B] > 0.5 * age[A] + 7
age[B] <= age[A]

2.once found the boundary above, apply these:
age[A] >= 100 : all B candidates
age[A] < 100  : only age[B] <= 100

sort ages, and then binary search?

*/

// wut?
class Solution {
    public int numFriendRequests(int[] ages) {
        int[] count = new int[121];
        for (int age: ages) {
            count[age]++;
        }
        int rst = 0;
        for (int i = 15; i < 121; i++) {
            for (int j = (int)(i * 0.5 + 8); j <= i; j++) {
                rst += i == j ? count[j] * (count[i] - 1) : count[j] * count[i];
            }
        }
        return rst;
    }
}
```
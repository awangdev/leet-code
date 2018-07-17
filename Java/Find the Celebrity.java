M
1531810500
tags: Array, Greedy
time: O(n)
space: O(1)

有n个人, 其中有个人是celebrity, 满足条件 `Celeb knows nobody; Everyone else knows the celeb`. 找到celeb

#### Understand the property
- If brutly find celeb by comparing all possible pair: take complete O(n^2) handshakes.
- Instead, we can perform pruning, or like survival mode:
- 1. Assume a celeb = 0, and compare with all i = [1~ n-1]
- 2. If `celeb candidate know i, set celeb = i` as the next candidate (ex: prev canddiate invalid when he knows i)
- 3. For last standing celeb candidate: compare with all for validation
- Why performing the last run of validation? There could be someone dropped out before we execute `know(celeb, i)`. 

##### 思考逻辑
- 先写出来[0 ~ n - 1], 最简单的方式 O(n^2) 检查, 记录每个人的状态.
- 逐渐发现, 因为 celeb 谁都不会认识, 那么当任何candidate knows anyone, 他自身就不是celeb.
- 我们可以greedy地, 一旦fail一个, 就立刻假设下一个是celeb candidate
- 最终还是要检查一遍, 避免错漏.
- 想一下happy case: 如果 celeb=0,  那么 know(celeb, i) 永远都是false, 然后 celeb一直保持0, 坚持到verify所有人.

```
/*
Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. 
The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. 
The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" 
to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) 
by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. 
Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

Note: There will be exactly one celebrity if he/she is in the party. 
Return the celebrity's label if there is a celebrity in the party. 
If there is no celebrity, return -1.

*/
/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        if (n <= 1) return -1;
        
        int celeb = 0;
        // pick candidate
        for (int i = 0; i < n; i++) {
            if (celeb != i && knows(celeb, i)) {
                celeb = i;
            }
        }
        
        // final check and return
        for (int i = 0; i < n; i++) {
            if (celeb != i && !(knows(i, celeb) && !knows(celeb, i))) {
                return -1;
            }
        }

        return celeb;
    }
}
```
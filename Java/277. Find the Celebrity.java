M
tags: Array, Greedy, Pruning, Adjacency Matrix, Graph
time: O(n)
space: O(1)

有n个人, 其中有个人是celebrity, 注意必要条件 `Celeb knows nobody; Everyone else knows the celeb`. 找到celeb

Note: the relationship graph can be presented as an adjacency matrix, but graph is not directly used in this problem.

#### Pruning
- Given assumption: 1) `only 1 celebrity`, 2) person k, who knows nobody ahead of him or after him.
- if first pass finds candidate, `person k`, it means:
    - person [0, k-1] are not celebrity: they know a previous or current candidate
    - person k knows no one between [k + 1,  n): k+1 to n-1 can not be the celebrity either. 
    - person k is just the last standing possible celebrity
- second pass validation: we do not know if `knows(celeb, [0~k-1] )`. Do a final O(n) check
- time:O(n), space O(1)
- DO NOT: Brutle compare all -> all: O(n^2) handshakes.

##### 思考逻辑
- 先写出来[0 ~ n - 1], 最简单的方式 O(n^2) 检查, 记录每个人的状态.
    - 逐渐发现, 因为 celeb 谁都不会认识, 那么当任何candidate knows anyone, 他自身就不是celeb.
    - 我们可以greedy地, 一旦fail一个, 就立刻假设下一个是celeb candidate
- 最终还是要检查一遍, 避免错漏.
- 想一下happy case: 如果 celeb=0,  那么 know(celeb, i) 永远都是false, 然后 celeb一直保持0, 坚持到verify所有人.

```
/*
Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.

 

Example 1:
Input: graph = [
  [1,1,0],
  [0,1,0],
  [1,1,1]
]
Output: 1
Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.
Example 2:


Input: graph = [
  [1,0,1],
  [1,1,0],
  [0,1,1]
]
Output: -1
Explanation: There is no celebrity.
 

Note:

The directed graph is represented as an adjacency matrix, which is an n x n matrix where a[i][j] = 1 means person i knows person j while a[i][j] = 0 means the contrary.
Remember that you won't have direct access to the adjacency matrix.


*/
/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        if (n <= 1) return -1;
        
        int celeb = 0;
        // first pass: find 1 possible candidate k, by pruning [0~k] candidates and (k+1~n)
        for (int i = 0; i < n; i++) {
            if (celeb != i && knows(celeb, i)) celeb = i;
        }
        
        // second pass: simple validate if celeb is real
        for (int i = 0; i < n; i++) {
            if (celeb != i && !(knows(i, celeb) && !knows(celeb, i))) return -1;
        }

        return celeb;
    }
}
```
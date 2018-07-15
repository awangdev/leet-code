E
1531627559
tags: Array
time: O(n)
space: O(1)

给一排座位, 一个人去坐: 找离两边的人都最远的地方(中间点), return 跟旁边人的最大distance

是Exam Room 的同种概念, 简单化题目: 这里只考虑一个人就好了

#### Basic Implementation, track start/end
- start/end point, 然后比较大小记录dist
- 注意1: 如果第一个座位没有人, 特殊处理, dist = [0 ~ end]
- 注意2: 如果最后一个座位没有人, 特殊处理: dist = [n - 1 - start];
- 其余: `dist = Math.max(dist, (end - start) / 2)`
- 相关题目: 几乎同样概念 `Binary Gap`, 升级复杂版`Exam Room`


```
/*
In a row of seats, 1 represents a person sitting in that seat, 
and 0 represents that the seat is empty. 

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him 
and the closest person to him is maximized. 

Return that maximum distance to closest person.

Example 1:

Input: [1,0,0,0,1,0,1]
Output: 2
Explanation: 
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.
Example 2:

Input: [1,0,0,0]
Output: 3
Explanation: 
If Alex sits in the last seat, the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.
Note:

1 <= seats.length <= 20000
seats contains only 0s or 1s, at least one 0, and at least one 1.

 */

// similar to Binary gap: find largest gap and sit in the middle
class Solution {
    public int maxDistToClosest(int[] seats) {
        if (seats == null || seats.length == 0) return -1;
        
        int n = seats.length, dist = 0;
        int start = seats[0] == 1 ? 0 : -1; // check start bound: -1, imaginary boundary
        
        for (int end = 1; end < n; end++) {
            if (seats[end] == 1) {
                if (start == -1) dist = end;
                else dist = Math.max(dist, (end - start) / 2);
                start = end;
            } 
        }
        
        // end bound
        if (seats[n - 1] == 0) dist = Math.max(dist, n - 1 - start);
        
        return dist;
    }
}
```
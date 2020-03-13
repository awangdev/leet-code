M
1531811136
tags: Hash Table
time: O(mn)
space: O(X), X = max wall width

给一面墙, 每一行是一行bricks. 用一条vertical line 扫描, 会vertically割开brink. 找到割开最少brick的那条线的x index.

#### Hash Table
- Find the vertical line (x-coordinate of the grid), where most gaps are found.
- Each gap has (x,y) coordinate
- Create `map<x-coordinate, #occurrance>`, and maintain a max occurance. 
- 计算: x-coordinate: `x = 0; x += brick[i] width`
- Eventually: min-crossed bricks = wall.lenght - maxOccurrance 

##### 思想
- 分析题意, 找到题目的目标
- 虽然Map自己不能有sort的规律, 但是可以maintain global variable

```
/*
There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. 
The bricks have the same height but different width. 
You want to draw a vertical line from the top to the bottom and cross the least bricks.

The brick wall is represented by a list of rows. Each row is a list of integers 
representing the width of each brick in this row from left to right.

If your line go through the edge of a brick, then the brick is not considered as crossed. 
You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.

You cannot draw a line just along one of the two vertical edges of the wall, 
in which case the line will obviously cross no bricks.

Example:
Input: 
[[1,2,2,1],
 [3,1,2],
 [1,3,2],
 [2,4],
 [3,1,2],
 [1,3,1,1]]
Output: 2
Explanation: 
// image missing, https://leetcode.com/problems/brick-wall/description/

Note:
The width sum of bricks in different rows are the same and won't exceed INT_MAX.
The number of bricks in each row is in range [1,10,000]. 
The height of wall is in range [1,10,000]. 
Total number of bricks of the wall won't exceed 20,000.

*/

// Hasp map of frequency, maintain global variable
class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        if (validate(wall)) {
            return 0;
        }
        
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        // build map, and maintain max
        for (List<Integer> row : wall) {
            int x = 0;
            for (int i = 0; i < row.size() - 1; i++) {
                x += row.get(i);
                map.putIfAbsent(x, 0);
                map.put(x, map.get(x) + 1);
                max = Math.max(max, map.get(x));
            }
        }
        return wall.size() - max;
    }

    private boolean validate(List<List<Integer>> wall) {
        return wall == null || wall.size() == 0 || wall.get(0) == null || wall.get(0).size() == 0;
    }
}
```
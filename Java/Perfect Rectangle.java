H
1528569462
tags: Geometry, Hash Table, Design

看的list of coordinates 是否能组成perfect rectangle, 并且不允许overlap area.

#### 画图发现特点
- 特点1: 所有给出的点(再找出没有specify的对角线点), 如果最后组成perfect rectangle, 都应该互相消除, 最后剩下4个corner
- 特点2: 找到所有点里面的min/max (x,y), 最后组成的 maxArea, 应该跟过程中accumulate的area相等
- 特点1确保中间没有空心的部分, 保证所有的重合点都会互相消除, 最后剩下4个顶点
- 特点2确保没有重合: 重合的area会最终超出maxArea

```
/*
Given N axis-aligned rectangles where N > 0, 
determine if they all together form an exact cover of a rectangular region.

Each rectangle is represented as a bottom-left point and a top-right point. 
For example, a unit square is represented as [1,1,2,2]. 
(coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).


Example 1:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [3,2,4,4],
  [1,3,2,4],
  [2,3,3,4]
]

Return true. All 5 rectangles together form an exact cover of a rectangular region.

Example 2:

rectangles = [
  [1,1,2,3],
  [1,3,2,4],
  [3,1,4,2],
  [3,2,4,4]
]

Return false. Because there is a gap between the two rectangular regions.

Example 3:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [3,2,4,4]
]

Return false. Because there is a gap in the top center.

Example 4:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [2,2,4,4]
]

Return false. Because two of the rectangles overlap with each other.
*/


/*
- build coordinates, and diagonal points, cancel all points from hashmap.
- should remain 4 points, which are diagonal. otherwise, fail
*/
class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        // check input
        if (rectangles == null || rectangles.length == 0 || rectangles[0] == null || rectangles.length == 0) {
            return false;
        }
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE, minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, area = 0;
        // build hashmap of all points, and diagonal points
        Set<String> set = new HashSet<>();

        for (int[] row: rectangles) {
            area += (row[2] - row[0]) * (row[3] - row[1]);
            validate(set, row[0], row[1]);
            validate(set, row[2], row[3]);
            validate(set, row[0], row[3]);
            validate(set, row[2], row[1]);
            minX = Math.min(minX, row[0]);
            minY = Math.min(minY, row[1]);
            maxX = Math.max(maxX, row[2]);
            maxY = Math.max(maxY, row[3]);
        }

        if (set.size() != 4 || !set.contains(buildKey(minX, minY)) || !set.contains(buildKey(maxX, maxY)) ||
            !set.contains(buildKey(minX, maxY)) || !set.contains(buildKey(maxX, minY))) {
            return false;
        }

        return area == (maxX - minX) * (maxY - minY);
    }

    private void validate(Set<String> set, int x, int y) {
        String key = buildKey(x, y);
        if (set.contains(key)) {
            set.remove(key);
        } else {
            set.add(key);
        }
    }
    private String buildKey(int x, int y) {
        return x + "@" + y;
    }
}



```
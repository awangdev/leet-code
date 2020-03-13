M
1531928255
tags: Hash Table, Math
time: O(n)
space: O(n)

给一串点, 找是否有一个所有点中间的, 跟y-axis平行的中线.

#### Hash Table
- 1. store in `Map<y, set<x>>`, 2. iterate over map, check head,tail against the mid point
- 很好的细节题目:
- 1. 除以2, 需要存double
- 2. (问面试官)可以有重复的点! 所以track `set<x>`
- 3. 处理 left==right时候, 就当做两个点来处理.
- 4. 存进set里面没有sort, 但是最后做check的时候, 需要sort list
- 时间: visit all nodes 两遍,  O(n)

```
/*
Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

Example 1:
Given points = [[1,1],[-1,1]], return true.

Example 2:
Given points = [[1,1],[-1,-1]], return false.

Follow up:
Could you do better than O(n2)?

Credits:
Special thanks to @memoryless for adding this problem and creating all test cases.
*/

/*
line parrel to y-axis: for all points with same y, all head/tail should have same mid point
1. store in Map<y, list<x>>
2. iterate over map, check head,tail mid
*/
class Solution {
    public boolean isReflected(int[][] points) {
        if (points == null || points.length == 0 || points[0] == null || points[0].length == 0) return true;
        
        Map<Integer, Set<Double>> map = new HashMap<>();
        for (int[] point : points) {
            map.putIfAbsent(point[1], new HashSet<>());
            map.get(point[1]).add((double)point[0]);
        }
        
        List<Double> sampleRow = new ArrayList<>(map.get(points[0][1]));
        Collections.sort(sampleRow);
        double mid = (sampleRow.get(0) + sampleRow.get(sampleRow.size() - 1)) / 2;
        
        for (Set<Double> row: map.values()) {
            int left = 0, right = row.size() - 1;
            List<Double> sortedRow = new ArrayList<>(row);
            Collections.sort(sortedRow);
            while (left <= right) {
                if ((sortedRow.get(left) + sortedRow.get(right)) / 2 != mid) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
}
```
H
1520916753
tags: Divide and Conquer, DFS, Binary Search

Should break down by mid row. More details:
http://www.jiuzhang.com/solution/find-peak-element-ii/#tag-highlight-lang-java
http://courses.csail.mit.edu/6.006/spring11/lectures/lec02.pdf

#### 方法1
##### 基本原理
我们不可能一口气准确定位(x,y), 但是我们可以再一个row/col里面, 找到1D array的 peak.
根据这个点, 再往剩下两个方向移动

1. 在中间的一行, 找到peak所在的y.

2. 在中间的一列, 找到peak所在的x. (有可能强势override之前找到的y, 也就是放弃那一行的peak, 在midY上找peak)

3. 猜一猜 (x,y) 是不是 peak, 如果不是, 像更高的位置移动一格

4. 根据之前算的 midX, midY 把board分成4个象限, 在每一份里面再继续找

##### 剪枝/切分象限
每次只是找到一个row/col里面的peak而已!

找到这个点, 就等于把board切成了两半.

然后, 再跟剩下的相邻的两个位置比较, 就知道了哪里更大, 就去哪里找peak, 也就是又切了第二刀.

切第二刀的时候, 也要把(x, y) 移到需要取的象限. 进行DFS

##### 时间复杂度
每一个level都减一半
T(n) = n + T(n/2) = n + n/2 + n/4 + ... + 1 = n(1 + 1/2 + .... + 1/n) = 2n = O(n)

#### 方法2
Binary Search
还没有写 : )
O(nLogN)

```
/*
There is an integer matrix which has the following features:

The numbers in adjacent positions are different.
The matrix has n rows and m columns.
For all i < m, A[0][i] < A[1][i] && A[n - 2][i] > A[n - 1][i].
For all j < n, A[j][0] < A[j][1] && A[j][m - 2] > A[j][m - 1].
We define a position P is a peek if:

A[j][i] > A[j+1][i] && A[j][i] > A[j-1][i] && A[j][i] > A[j][i+1] && A[j][i] > A[j][i-1]
Find a peak element in this matrix. Return the index of the peak.

Have you met this question in a real interview? Yes
Example
Given a matrix:

[
  [1 ,2 ,3 ,6 ,5],
  [16,41,23,22,6],
  [15,17,24,21,7],
  [14,18,19,20,10],
  [13,14,11,10,9]
]
return index of 41 (which is [1,1]) or index of 24 (which is [2,2])

Note
The matrix may contains multiple peeks, find any of them.

Challenge
Solve it in O(n+m) time.

If you come up with an algorithm that you thought it is O(n log m) or O(m log n), 
can you prove it is actually O(n+m) or propose a similar but O(n+m) algorithm?

Tags Expand 
Binary Search LintCode Copyright Matrix
*/

/*
Not on LeetCode. 
*/
class Solution {
    /**
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] A) {
        List<Integer> rst = new ArrayList<>();
        if (A == null || A.length == 0 || A[0] == null || A[0].length == 0) {
            return rst;
        }
        int m = A.length;
        int n = A[0].length;
        rst = find(1, m - 2, 1, n - 2, A);

        return rst;
    }

    private List<Integer> find(int x1, int x2, int y1, int y2, int[][] A) {
        // Given coordinate boundary, find center point (midX, midY)
        int midX = x1 + ((x2 - x1) >> 1); // or just (x2 - x1)/2
        int midY = y1 + ((y2 - y1) >> 1);

        int x = midX, y = midY; // 从中间点开始寻找 x,y
        int max = A[x][y];
        // With i = midX, find peak on the center row.
        // 固定y: 这里顾不到curr row上下的大小
        for (int j = y1; j <= y2; j++) {
            if (A[midX][j] > max) {
                max = A[midX][j];
                x = midX;
                y = j;
            }
        }

        // With j = midY, find peak on the center col.
        // 固定x: 这里顾不到curr col左右的大小
        // 这里如果找到A[i][midY]大于max, 强行override 之前写到的y.
        // 放弃找之前的peak, 找这个新peak, 肯定找的到.
        for (int i = x1; i <= x2; i++) {
            if (A[i][midY] > max) {
                max = A[i][midY];
                y = midY;
                x = i;
            }
        }
 
        // If (x,y) not at the peak, move towards the peak for 1 step, then DFS
        // 剪枝/矫正.
        boolean isPeak = true;
        if (A[x - 1][y] > A[x][y]) {
            isPeak = false;
            x -= 1;
        } else if (A[x + 1][y] > A[x][y]) {
            isPeak = false;
            x += 1;
        } else if (A[x][y - 1] > A[x][y]) {
            isPeak = false;
            y -= 1;
        } else if (A[x][y + 1] > A[x][y]) {
            isPeak = false;
            y += 1;
        }

        if (isPeak) {
            return new ArrayList<Integer>(Arrays.asList(x, y));
        }

        // Depending which quadrant (x,y) is at. DFS into one of the 4 quadrants.
        // UP-LEFT
        if (x >= x1 && x < midX && y >= y1 && y < midY) {
            return find(x1, midX - 1, y1, midY - 1, A);
        }
        
        // UP-RIGHT
        if (x >= x1 && x < midX && y > midY && y <= y2) {
            return find(x1, midX - 1, midY + 1, y2, A);
        }
        
        // DOWN-LEFT
        if (x > midX && x <= x2 && y >= y1 && y < midY) {
            return find(midX + 1, x2, y1, midY - 1, A);
        }
        
        // DOWN-RIGHT
        if (x >= midX && x <= x2 && y > midY && y <= y2) {
            return find(midX + 1, x2, midY + 1, y2, A);
        }

        // Failed, return empty corridate.
        return new ArrayList<>();
    }
}


// 简化
class Solution {
    /**
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] A) {
        List<Integer> rst = new ArrayList<>();
        if (A == null || A.length == 0 || A[0] == null || A[0].length == 0) {
            return rst;
        }
        int m = A.length;
        int n = A[0].length;
        rst = find(1, m - 2, 1, n - 2, A);

        return rst;
    }

    private List<Integer> find(int x1, int x2, int y1, int y2, int[][] A) {
        // Given coordinate boundary, find center point (midX, midY)
        int midX = x1 + ((x2 - x1) >> 1); // or just (x2 - x1)/2
        int midY = y1 + ((y2 - y1) >> 1);

        int x = midX, y = midY; // 从中间点开始寻找 x,y
        int max = A[x][y];
        // With i = midX, find peak on the center row.
        // 固定y: 这里顾不到curr row上下的大小
        for (int j = y1; j <= y2; j++) {
            if (A[midX][j] > max) {
                max = A[midX][j];
                x = midX;
                y = j;
            }
        }

        // With j = midY, find peak on the center col.
        // 固定x: 这里顾不到curr col左右的大小
        // 这里如果找到A[i][midY]大于max, 强行override 之前写到的y.
        // 放弃找之前的peak, 找这个新peak, 肯定找的到.
        for (int i = x1; i <= x2; i++) {
            if (A[i][midY] > max) {
                max = A[i][midY];
                y = midY;
                x = i;
            }
        }
 
        // If (x,y) not at the peak, move towards the peak for 1 step, then DFS
        // 剪枝/矫正.
        if (A[x - 1][y] > max) { // UP-LEFT
            return find(x1, midX - 1, y1, y2, A);
        } else if (A[x + 1][y] > max) { // UP-RIGHT
            return find(x1, midX + 1, y1, y2, A);
        } else if (A[x][y - 1] > max) { // DOWN-LEFT
            return find(x1, x2, y1, midY - 1, A);
        } else if (A[x][y + 1] > max) { // DOWN-RIGHT
            return find(x1, x2, midY + 1, y2, A);
        }

        return new ArrayList<Integer>(Arrays.asList(x, y));
    }
}

```
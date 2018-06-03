M
1527959005
tags: Hash Table, Design

#### basic implementation
- 用HashMap， 理解题目规律，因为重复的计算可以被覆盖，所以是个优化题。没有什么太大的悬念和意义.
- 消灭重合点:       
- 如果process当下col, 其实要减去过去所有加过的row的交接点。。。     
- 再分析，就是每次碰到row 取一个单点, sumRow += xxx。       
- 然后process当下col时候， sum += colValue * N - sumRow. 就等于把交叉所有row（曾经Process过的row）的点减去了。很方便。
- 最后read in 是O(P),  process也是O(P).


```

/*
HackerRank.
You are given an N×NN×N grid. Each cell has the color white (color 0) in the beginning.

Each row and column has a certain color associated with it. 
Filling a row or column with a new color VV means changing all the cells of 
that row or column to VV (thus overriding the previous colors of the cells).

Now, given a sequence of PP such operations, calculate the sum of the colors in the final grid.

For simplicity, the colors will be positive integers whose values will be most 109109.

Input Format 
The first line of input contains two integers NN and PP separated by a space. 
The next PP lines each contain a filling operation. There are two types of filling operations.

ROW I V which means "fill row II with color VV".
COL I V which means "fill column II with color VV".
Output Format 
Output one line containing exactly one integer which is the sum of the colors in the final grid.

Constraints 
1≤N≤60001≤N≤6000 
1≤P≤4000001≤P≤400000 
1≤I≤N1≤I≤N 
1≤V≤1091≤V≤109

Sample Input

5 4
COL 1 6
COL 4 11
ROW 3 9
COL 1 24
Sample Output

200
Explanation 
There are four operations. After the second operation, the grid looks like

 6  0  0 11  0
 6  0  0 11  0
 6  0  0 11  0
 6  0  0 11  0
 6  0  0 11  0
After the third operation (ROW 3 9), the third row was colored with 9, overriding any previous color in the cells.

 6  0  0 11  0
 6  0  0 11  0
 9  9  9  9  9
 6  0  0 11  0
 6  0  0 11  0
After the fourth operation (COL 1 24), the grid becomes:

24  0  0 11  0
24  0  0 11  0
24  9  9  9  9
24  0  0 11  0
24  0  0 11  0
The sum of the colors in this grid is 200.

*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/*
Thoughts:
This is for practice. I didn't run much tests on the code.
    Store info into class Cell {int x;  boolean isRow; long value}
    Save to arraylist. Later need to call list.remove(object)
    Use hash map to store the appearance <String, Cell>
    process the final data:
        keep track of curr single row cell sum = rowSum; also colSum
        during process: add up n*colorValue.
                        if row, minus rowSum
                        if col, minus colSum
*/
public class Solution {
    class Cell {
        int x;
        boolean isRow;
        long value;
        public Cell(String s) {
            String[] ss = s.split(" ");
            this.isRow = ss[0].charAt(0) == 'R';
            this.x = Integer.parseInt(ss[1]);
            this.value = Long.parseLong(ss[2]);
        }
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        Scanner in = new Scanner(System.in);
        String[] ss = in.nextLine().split(" ");
        int N = Integer.parseInt(ss[0]);
        int P = Integer.parseInt(ss[1]);
        
        //Storage
        HashMap<String, Cell> map = new HashMap<String, Cell>();
        ArrayList<Cell> list = new ArrayList<Cell>();
        
        while (P != 0) {//O(P)
            //create Cell
            String s = in.nextLine();
            Cell cell = sol.new Cell(s);
            //add into list
            list.add(cell);
            //Check if cell exist in map. 
            //if exist in map, replace it with current cell, and remove old cell from list
            String key = s.substring(0, s.lastIndexOf(" "));
            if (!map.containsKey(key)) {
                map.put(key, cell);
            } else {
                Cell oldCell = map.get(key);
                map.put(key, cell);
                list.remove(oldCell);
            }
            P--;
        }
        
        //Process final results
        int sumCol = 0;
        int sumRow = 0;
        long sum = 0;
        for (int i = 0; i < list.size(); i++) {//O(P)
            Cell cell = list.get(i);
            sum += cell.value * N;
            if (cell.isRow) {
                sum -= sumCol;
                sumRow += cell.value;
            } else {
                sum -= sumRow;
                sumCol += cell.value;
            }
        }
        
        System.out.println(sum);
    }
}
```
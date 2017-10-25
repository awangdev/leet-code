/*
According to the Wikipedia's article: 
"The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). 
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules 
(taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
Could you solve it in-place? Remember that the board needs to be updated at the same time: 
You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. 
	In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. 
	How would you address these problems?
Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Hide Company Tags Google TinyCo
Hide Tags Array
Hide Similar Problems (M) Set Matrix Zeroes

*/

/*
	Thoughts:
	https://segmentfault.com/a/1190000003819277
	http://my.oschina.net/Tsybius2014/blog/514447
	build state machine.
	take mod of 2 at the end.
*/

public class Solution {
    public void gameOfLife(int[][] board) {
        
    }
}
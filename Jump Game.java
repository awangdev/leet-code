/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

This can be done using DP. However, greedy algorithm is fast in this particular problem. Consider both solutions.

DP
Thinking Process:
We have array A, that stores the # of steps for each index.
State: f[i] means if previous steps can reach to i. True/False
Function: f[i] = f[j] && (j + A[j] >= i)
Init: f[0] = true
Answer: f[n-1], if n is the length of A
*/

public class Solution {
    /**
     * @param A: A list of integers
     * @return: The boolean answer
     **/
  //DP
  public boolean canJump(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }
	//By default, boolean[] can is all false
        boolean[] can = new boolean[A.length];
        can[0] = true;
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (A[j] && (j + A[j] >= i)) {
                    can[i] = true;
                    break;
                }
            }
        }
        return can[A.length - 1];
    }
}



/*

Greedy. Ideas from Yu’s Garden
At each index, check how far we can jump, store this forest-can-jump position in variable ‘farest’. Take max of current farest and (index + A[index]), store is in farest
At each index, compare if ‘farest’ is greater than the end of array, if so, found solution, return true.
At each index, also check if ‘farest == current index’, that means the farest we can move is to current index and we cannot move forward. Then return false.
*/

public class Solution {
    /**
     * @param A: A list of integers
     * @return: The boolean answer
     **/
     
    public boolean canJump(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }
        int farest = 0;
        for (int i = 0; i < A.length; i++) {
            farest = Math.max(farest, i + A[i]);
            if (farest > A.length - 1) {
                return true;
            }
            if (farest == i) {
                return false;
            }
        }
        return true;
    }
}


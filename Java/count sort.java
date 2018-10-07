package pers.jack.sort;


import java.util.*;


/**
 *  The sorting algorithm is stable and uses O(1) extra memory  & O(1) time.
 *  @author Yash Bharti
 */

public class Countsort {

    
			public static void main(String[] args) {

		int[] arr = { 0, 4, 3, 0, 2, 7, 15, 2, 14, 12, 5, 10, 14, 6,12,0, 1 ,8 };

		PRINT(arr);
		COUNTSort(arr, 15);
		PRINT(arr);
	}

	public static void COUNTSort(int[] arr, int k) {

		int RES[] = new int[arr.length];

		int count[] = new int[k + 1];
		Arrays.fill(count, 0);

		for (int i = 0; i < arr.length; i++)
			count[arr[i]]++;

		for (int i = 1; i <= k; i++)
			count[i] += count[i - 1];

		for (int i = 0; i < arr.length; i++) {
			RES[count[arr[i]] - 1] = arr[i];
			count[arr[i]]--;
		}

		for (int i = 0; i < arr.length; i++)
			arr[i] = RES[i];
	}

	public static void PRINT(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + ", ");
		System.out.println();
	}
}
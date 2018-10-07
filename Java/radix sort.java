package pers.jack.sort;


import java.util.*;


public class Radixsort {

    
		public static void main(String[] args) {
		int arr[] = { 170, 45, 75, 90, 802, 24, 2, 66 };
		Print(arr);
		System.out.println();
		RADIXsort(arr);
		Print(arr);
	}

	public static int getMax(int arr[]) {
		int MAX = arr[0];
		for (int i = 1; i < arr.length; i++)
			if (arr[i] > MAX)
				MAX = arr[i];
		return MAX;
	}

	public static void COUNT_RAD(int arr[], int exp) {
		int output[] = new int[arr.length];
		int i;
		int count[] = new int[10];
		Arrays.fill(count, 0);

		for (i = 0; i < arr.length; i++)
			count[(arr[i] / exp) % 10]++;

		for (i = 1; i < 10; i++)
			count[i] += count[i - 1];

		for (i = arr.length - 1; i >= 0; i--) {
			output[count[(arr[i] / exp) % 10] - 1] = arr[i];
			count[(arr[i] / exp) % 10]--;
		}

		for (i = 0; i < arr.length; i++)
			arr[i] = output[i];
	}

	public static void RADIXsort(int arr[]) {
		int m = getMax(arr);
//////////////LEAST SIGNIFICANT///////////////////
		for (int exp = 1; m / exp > 0; exp *= 10)
			COUNT_RAD(arr, exp);
	}

	public static void Print(int arr[]) {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
	}

}

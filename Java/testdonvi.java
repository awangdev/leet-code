import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class testdonvi {
	 public int twoSum2(int[] nums, int target) {
	        if (nums == null || nums.length == 0) {
	            return 0;
	        }
	        int count = 0;
	        int left = 0;
	        int right = nums.length - 1;
	        Arrays.sort(nums);
	        while (left < right) {
	            if (nums[left] + nums[right] > target) {
	                count += (right - left);
	                right--;
	            } else {
	                left++;
	            }
	        }
	        return count;
	    }

	@Test
	public void test() {
		int[] a= {};
		
		int result;
		
		result=twoSum2(a, 3);
		assertEquals(0,result);
		
	}
	@Test
	public void test2() {
		int[] a= null;
		
		int result;
		
		result=twoSum2(a, 3);
		assertEquals(0,result);
		
	}
	@Test
	public void test3()
	{int[] a= {3,1};
	int result=twoSum2(a, 2);
	assertEquals(1,result);
		
	}
	@Test
	public void test4()
	{int[] a= {3,1};
	int result=twoSum2(a, 5);
	assertEquals(0,result);
		
	}
	@Test
	public void test5()
	{int[] a= {3};
	int result=twoSum2(a, 2);
	assertEquals(0,result);
		
	}
	
	
	

}

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
//@test1: trường hợp if đầu tiên true ,vòng lặp sau k khả thi , thì trả về count=0 ( với nums.length()=0)
	@Test
	public void test() {
		int[] a= {};
		
		int result;
		
		result=twoSum2(a, 3);
		assertEquals(0,result);
		
	}
//@test2: trường hợp if đầu tiên true, vòng lặp sau k khả thi , thì trả về count=0 (với nums=null)
	@Test
	public void test2() {
		int[] a= null;
		
		int result;
		
		result=twoSum2(a, 3);
		assertEquals(0,result);
		
	}
//@test3: trường hợp if đầu tiên false, if thứ 2 true  trả về count=1 ( vòng lặp thực hiện 1 lần)
	@Test
	public void test3()
	{int[] a= {3,1};
	int result=twoSum2(a, 2);
	assertEquals(1,result);
		
	}
//@test4: trường hợp if đầu tiên false, if thứ 2 false trả về 0  (với right+left<target) kết quả trả về 0
	@Test
	public void test4()
	{int[] a= {3,1};
	int result=twoSum2(a, 5);
	assertEquals(0,result);
		
	}
//@test5: trường hợp if đầu tiên false, if thứ 2 lần đầu đúng, nhưng lần thứ 2 false  , kết quả trả về là 0.
	@Test
	public void test5()
	{int[] a= {3};
	int result=twoSum2(a, 2);
	assertEquals(0,result);
		
	}
	
	
	

}

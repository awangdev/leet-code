import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

public class testalldupaths extends twoSum2{
	
//0->1->8
	@Test
	public void testnum() {
		int[] a= {};
		
		int result;
		
		result=twoSum2(a, 10);
		assertEquals(0,result);
		
	}
//0->1->8
	@Test
	public void testnum2() {
		int[] a= null;
		
		int result;
		
		result=twoSum2(a, 10);
		assertEquals(0,result);
		
	}
////0->1->2->3->4->6->(3->...)->7
	@Test
	public void testnum3()
	{int[] a= {13,11};
	int result=twoSum2(a, 12);
	assertEquals(1,result);
		
	}
//0->1->2->3->4->6->(3->...)->7
	@Test
	public void testnum4()
	{int[] a= {5,4};
	int result=twoSum2(a, 10);
	assertEquals(0,result);
		
	}
//0->1->2->3->4->6->(3->...)->7
	@Test
	public void testtarget1()
	{int[] a= {3,6};
	int result=twoSum2(a, 10);
	assertEquals(0,result);
		
	}
//0->1->2->3->4->6->(3->...)->7
		@Test
		public void testtarget2()
		{int[] a= {3,2};
		int result=twoSum2(a, 4);
		assertEquals(1,result);
			
		}
//0->1->2->3->4->5->3->7
		@Test
		public void testcount1()
		{int[] a= {10,2};
		int result=twoSum2(a, 9);
		assertEquals(1,result);
			
		}
//0->1->2->3->4->5->(3->5->..)->7
		@Test
		public void testcount2()
		{int[] a= {1,3,4};
		int result=twoSum2(a, 2);
		assertEquals(3,result);
			
		}
//0->1->2->3->4->6->3->7
		@Test
		public void testleft1()
		{
			int[] a= {1,3};
		int result=twoSum2(a, 8);
		assertEquals(0,result);
		}
//0->1->2->3->4->5->3->7
		@Test
		public void testright1()
		{
			int[] a= {1,3};
		int result=twoSum2(a, 2);
		assertEquals(1,result);
		}
//0->1->2->3->4->5->(3->5->..)->7
		@Test
		public void testright2()
		{
				int[] a= {1,3,5};
				int result=twoSum2(a, 2);
				assertEquals(3,result);
		}



	
	
	

}

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class testIsOneBitCharacter {
	
	// ham can test
	public static boolean isOneBitCharacter(int[] bits) {
        if (bits == null || bits.length == 0) {
            return false;
        }
        int n = bits.length;
        if (n == 1) {
            return bits[0] == 0;
        }
        if (bits[n - 1] != 0) {
            return false;
        }
        
        boolean[] dp = new boolean[n];
        dp[0] = bits[0] == 0;
        dp[1] = bits[0] != 0 || bits[1] != 1;
        for (int i = 2; i < n - 1; i++) {
            if (bits[i] == 0) {
                dp[i] = dp[i - 2] || dp[i - 1];
            } else if (bits[i] == 1) {
                dp[i] = dp[i - 2] && bits[i - 1] == 1;
            }
        }
        return dp[n - 2];
    }
	
	// ham test thu 1, khi bit cuoi cung la 0, co nhieu hon 2 bit
	@Test
	void test1() {
		final boolean expected = false;
		int a[] = {1, 0, 1, 1, 1, 0};
		final boolean actual = isOneBitCharacter(a);
		Assert.assertEquals(expected, actual);
	}
	
	// ham test thu 2, khi bit cuoi cung la 0, va co 2 bits
		@Test
		void test2() {
			final boolean expected = false;
			int a[] = {1, 0};
			final boolean actual = isOneBitCharacter(a);
			Assert.assertEquals(expected, actual);
		}
		
		// ham test thu 3, khi bit cuoi cung khac 0 va co 2 bits
		@Test
		void test3() {
			final boolean expected = false;
			int a[] = {1, 1};
			final boolean actual = isOneBitCharacter(a);
			Assert.assertEquals(expected, actual);
		}
		
		// ham test thu 4, khi bit cuoi cung khac 0 va co nhieu hon 2 bit
		@Test
		void test4() {
			final boolean expected = false;
			int a[] = {1, 0, 1, 1, 1, 1};
			final boolean actual = isOneBitCharacter(a);
			Assert.assertEquals(expected, actual);
		}
		
		// ham test thu 5, mang rong
		@Test
		void test5() {
			final boolean expected = false;
			int a[] = {};
			final boolean actual = isOneBitCharacter(a);
			Assert.assertEquals(expected, actual);
		}
		
		// ham test thu 6, mang co 1 phan tu la 1
				@Test
				void test6() {
					final boolean expected = false;
					int a[] = {1};
					final boolean actual = isOneBitCharacter(a);
					Assert.assertEquals(expected, actual);
				}
				
		// ham test thu 7, khi mang co 1 phan tu la 0
		@Test
		void test7() {
				final boolean expected = true;
				int a[] = {0};
				final boolean actual = isOneBitCharacter(a);
				Assert.assertEquals(expected, actual);
				}
}

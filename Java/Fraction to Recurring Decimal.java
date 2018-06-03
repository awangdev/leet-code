M
tags: Hash Table, Math

TODO: no need of hashMap, just use set to store the existing

不难想到处理除法：考虑正负，考虑小数点前后。主要是小数点以后的要着重考虑。
很容易忽略的是integer的益处。
```
/*
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".

Hide Company Tags Google
Show Tags
Hash Table Math


*/

/*
	Thoughts:
	Divide it into small pieces:
	1. d = 0, return null;
	2. n = 0 -> 0
	3. mark negative. let n = abs(numerator), d = abs(denominator)
	4. consider front and end:
		front = (int)sharp divide
		end: build hashmap to track if the numerator*10 occurs. Once occurs again, return the remianing.
	5. based on sign, return results.

Note:
Have to take int overflow INT_MAX, INT_MIN....
*/
//Optimized code:
public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        long nume = Math.abs((long)numerator);
        long deno = Math.abs((long)denominator);
        String sign = (numerator < 0) ^ (denominator < 0) ? "-" : "";
        if (deno == 0) {
        	return "";
        } else if (nume == 0) {
        	return "0";
        } else if (nume % deno == 0) {
        	return sign + nume/deno + "";
        }
        
        HashMap<Long,Integer> map = new HashMap<Long, Integer>();
        StringBuffer rst = new StringBuffer(sign + nume/deno + ".");
        long end = nume%deno * 10;//The decimal portion of the value, after decimal point
  		int i = 0;
        while (end != 0){
			if (map.containsKey(end)) {
				rst.insert(rst.indexOf(".") + map.get(end) + 1, "(");
				rst.append(")");
				return rst.toString();
			}
			rst.append(end/deno);
        	map.put(end, i++);
        	end = (end % deno) * 10;
        }
        return rst.toString();
    }
}



//Original working version
public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        long nume = Math.abs((long)numerator);
        long deno = Math.abs((long)denominator);
        String sign = (numerator < 0) ^ (denominator < 0) ? "-" : "";
        if (deno == 0) {
        	return "";
        } else if (nume == 0) {
        	return "0";
        } else if (nume % deno == 0) {
        	return sign + nume/deno + "";
        }
        
        String rst = sign + nume/deno + ".";
        long end = nume%deno * 10;

        HashMap<Integer, Long> map = new HashMap<Integer, Long>();
  		boolean repeat = false;
  		String endRepeat = "";
  		int n = 0;
        while (true){
			if (end == 0) {
				break;
			} else if (map.containsValue(end)) {
				repeat = true;
				break;
			}
        	map.put(n++, end);
        	end = (end % deno) * 10;
        }

        for (int i = 0; i < n; i++) {
        	if (repeat && map.get(i) == end) {
        		rst += "(" + map.get(i)/deno;
        		endRepeat = ")";
				repeat = false;
        	} else {
				rst += map.get(i)/deno;        		
        	}
        }

        return rst + endRepeat;
    }
}






```
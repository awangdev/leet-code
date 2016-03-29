M

方法1:    
用数字+"#"+string来encode.    
基于我们自己定的规律, 在decode的里面不需要过多地去check error input, assume所有input都是规范的.    
decode就是找"#",然后用"#"前的数字截取后面的string.



Old Solution:    
Cast character into int. 串联起来, seperate by "LINE".   
handle empty list [], or just null: 要把Null特别mark一下为‘NULL’, 这样decode时才能check到。      adminadmin


```
/*
Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:
The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.


Tags: String
Similar Problems: (E) Count and Say, (M) Serialize and Deserialize Binary Tree

*/


/*
Recap 3.28.2016
Use number+"#" to mark a string. Append them all.
*/
public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        if (strs.size() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strs.size(); i++) {
            sb.append(strs.get(i).length() + "#" + strs.get(i));
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> strs = new ArrayList<String>();
        if (s == null || s.length() == 0) {
            return strs;
        }
        int start = 0;
        while (start < s.length()) {
            int ind = s.indexOf("#", start);
            int leng = Integer.parseInt(s.substring(start, ind));
                
            start = ind + 1 + leng;
            strs.add(s.substring(ind + 1, start));

        }
        return strs;
    }
}


/*
Thoughts:
Break into integers
Use some special words to: 1. break line. 2. record null condition.
Note: "" empty string is also a string case, so don't treat that as null. Call null, "NULL"
Note2: As long as the list is not empty, though some string might be just "", make sure to encode it as 'LINE' just to remind in decoder: treat it as a ""
*/
public class Codec {
     // Encodes a list of strings to a single string.
    public static String encode(List<String> strs) {
        if (strs == null || strs.size() == 0) {
            return "NULL";
        }
        StringBuffer sb = new StringBuffer();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] >= 100) {
                    sb.append("" + (int)arr[i]);
                } else if (arr[i] >= 10) {
                    sb.append("0" + (int)arr[i]);
                } else {
                    sb.append("00" + (int)arr[i]);
                }
            }
            sb.append("LINE");
        }//END for
        if (sb.length() == 0) {
            sb.append("LINE");
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public static List<String> decode(String s) {
        List<String> rst = new ArrayList<String>();
        if (s.equals("NULL")) {
            return rst;
        }
        int index = s.indexOf("LINE");
        while (index != -1) {
            String str = s.substring(0, index);

            StringBuffer sb = new StringBuffer();
            int i = 0;
            while (i + 3 <= str.length()) {
                int letter = Integer.parseInt(str.substring(i, i + 3));
                sb.append((char)letter);
                i+=3;
            }
            rst.add(sb.toString());

            s = s.substring(index + 4);
            index = s.indexOf("LINE");
        }
        
        return rst;
    }
}
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));

```
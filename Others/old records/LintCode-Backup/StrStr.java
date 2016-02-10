/*
StrStr:
strStr My Submissions

19% Accepted
strstr (a.k.a find sub string), is a useful function in string operation. You task is to implement this function. 

For a given source string and a target string, you should output the "first" index(from 0) of target string in source string.

If target is not exist in source, just return -1.

Example
If source="source" and target="target", return -1.

If source="abcdabcdefg" and target="bcd", return 1.

Challenge
O(n) time.

Clarification
Do I need to implement KMP Algorithm in an interview?

    - Not necessary. When this problem occurs in an interview, the interviewer just want to test your basic implementation ability.

Tags Expand 
Basic Implementation String

Thinking process:
Two Pointer.
String Null case.
Break Statement.


Check position i+j of source and position j of target. If not match, break out.
If j matches target.length(), means target is fully embedded in source. 
return start point of target in source: i
*/
public int strStr(String source, String target) {
        //Check Null
        if(source == null || target == null){
            return -1;
        }
        //Two Pointer check for target
        int i,j;
        for (i = 0; i < source.length() - target.length() + 1; i++){
            for (j = 0; j < target.length(); j++){
                if (source.charAt(i+j) != target.charAt(j)){
                    break; 
                } 
            }
            if( j == target.length()){
                return i;
            }
        }
        //'target' not found:
        return -1;
}

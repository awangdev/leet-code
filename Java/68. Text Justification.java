H
tags: String, Enumeration
time: O(n) go over words
space: O(maxLength) buffer list

按照规则 adjust text. 就是Word里面: 有一行太长, adjust word 中间的space, 然后保证每一行的total width 顶格.

还有一些细节规则, 看原题

#### String
- greedy approach: line up as many words as possible; once exceed the MaxLength, justify the list of words
- Steps
    - 1) Split & group
    - 2) Juststify a row of words
    - 3) clean up last row
- Calcualte bounded row length = `width + (list.size() - 1)`. `list.size()-1` = Minimum amount of slot/space used.
- Calculate max ave spaces ot insert into each slot = `totalExtraSpace/slot`
- `Juststify a row of words`: 
    - 1) take a list of words and assume minimum 1 space in-between words
    - 2) distribute the remaining spaces evenly to each slot
- Overall runtime: O(n) to go over all space
- Overall space O(maxWidth) for maxWidth amount of strings

```
/*
Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
Example 1:

Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be",
             because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only one word.
Example 3:

Input:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
*/
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> list = new ArrayList<>();
        List<String> rst = new ArrayList<>();
        int width = 0;

        // 1) Split & group
        for (String s : words) {
            // when size reach maxWidth, comebine list[s] into s
            if (getRowLength(list, width) + s.length() + 1 > maxWidth) {
                // 2) Juststify a row of words
                rst.add(justify(list, width, maxWidth));
                list = new ArrayList<>();
                width = 0;
            }
            list.add(s);
            width += s.length();
        }
        
        if (list.size() == 0) return rst;
        
        // 3) clean up last row
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size() - 1; i++) sb.append(list.get(i) + " ");
        sb.append(list.get(list.size() - 1));
        sb.append(generateSpace(maxWidth - sb.length()));
        rst.add(sb.toString());
        return rst;
    }
    
    // word total width + (n - 1) spaces
    private int getRowLength(List<String> list, int width) {
        return width + (list.size() - 1); // overall width + (n - 1) spaces
    }
    
    // Juststify a row of words
    private String justify(List<String> list, int width, int max) {
        int slot = list.size() - 1; // # of slots in-between words
        int extraSpace = max - width; // additional space to consume
        if (slot == 0) return list.get(0) + generateSpace(extraSpace); // single word, just return

        int length = extraSpace / slot; // max avg length to insert in-between words
        int remain = extraSpace % slot; // remaining extra spaces
        String space = generateSpace(length);
        StringBuffer sb = new StringBuffer();
        // aggregate first i word
        for (int i = 0; i < slot; i++) {
            sb.append(list.get(i)).append(space);
            if (i < remain) sb.append(" ");
        }
        // append last word
        sb.append(list.get(slot));
        sb.append(generateSpace(max - sb.length()));
        return sb.toString();
    }
    
    private String generateSpace(int n) {
        if (n <= 0) return "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) sb.append(" ");
        return sb.toString();
    }
}


```
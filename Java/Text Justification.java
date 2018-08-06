H
1533529849
tags: String, Enumeration

按照规则 adjust text. 就是Word里面: 有一行太长, adjust word 中间的space, 然后保证每一行的total width 顶格.

还有一些细节规则, 看原题

#### String
- Summing space = `width + (size-1)`. maintain: 1. list of candidates, 2. width of actual words
- calculate space in between: `remain/(size - 1)`
- overall for loop; clean up list: 1. over size; 2. last item
- 一点也不难, 但是要小心: deal with list of string的时候, 注意处理干净sum size of list<string>, 就行了.
- `干净处理space`: 只处理 (n-1) items, 然后最后一个拿到for loop 外面, 特殊处理.

#### Notes
- Clarification, observation:
- can start with greedy approach to stack as many words as possible
- once exceed the length, pop the top, and justify the added words (untouched words tracked by index)
- left justify: given list/stack of words with size t, overall remaining space length m, 
- deal with last line with special care: just fill one space, and fill the rest of the row with space
- Does not seem very complicated, but need additional care of calculating the amount of space needed.
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
        for (String s : words) {
            // when size reach maxWidth, comebine list[s] into s
            if (getRowLength(list, width) + s.length() + 1 > maxWidth) {
                rst.add(aggregate(list, width, maxWidth));
                list = new ArrayList<>();
                width = 0;
            }
            list.add(s);
            width += s.length();
        }
        
        if (list.size() == 0) return rst;
        
        // at end, clean up last row
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size() - 1; i++) {
            sb.append(list.get(i) + " ");
        }
        sb.append(list.get(list.size() - 1));
        sb.append(generateSpace(maxWidth - sb.length()));
        rst.add(sb.toString());
        return rst;
    }
    
    private int getRowLength(List<String> list, int width) {
        return width + list.size() - 1; // overall width + (n - 1) spaces
    }
    
    private String aggregate(List<String> list, int width, int max) {
        int slot = list.size() - 1, diff = max - width;
        if (slot == 0) return (list.get(0) + generateSpace(diff));

        int length = diff / (slot);
        int remain = diff % slot; // less than list.size() - 1
        String space = generateSpace(length);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < slot; i++) {
            sb.append(list.get(i) + space);
            if (i < remain) sb.append(" ");
        }
        sb.append(list.get(slot));
        sb.append(generateSpace(max - sb.length()));
        return sb.toString();
    }
    
    private String generateSpace(int x) {
        if (x <= 0) return "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < x; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
```
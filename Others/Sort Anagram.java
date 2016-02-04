/*
	Sort Anagram
	My guess this problem is: Sort a list of strings and make sure all the anagrams are grouped together.

	The clarification needed would be: 
	1. Does the output need to be in alphabetical order and how to define the rules, since anagram will not be in perfect order comparing with other non-same-group strings.

	2. If no need of alphabetical order, but just group anagram words together, this would turn to a regular anagram problem.
		Use HashMap to store sorted string as KEY, and a list of strings as value.
		Then output all contents from map.

*/
/*
	Given a list of URLs, find top 10 most visited URLs

	Some points this problem might cover:


*/

/*
	1. MapReduce related problem
		http://stackoverflow.com/questions/17928158/find-top-10-most-frequent-visited-url-data-is-stored-accross-network#
	To mimic the process of MapReduce.

	One most voted solution:
	It says you can't use map-reduce directly which is a hint the author of the question wants you to think how map reduce works, so we will just mimic the actions of map-reduce:

		pre-processing: let R be the number of servers in cluster, give each server unique id from 0,1,2,...,R-1
		(map) For each (string,id) - send the tuple to the server which has the id hash(string) % R.
		(reduce) Once step 2 is done (simple control communication), produce the (string,count) of the top 10 strings per server. Note that the tuples where those sent in step2 to this particular server.
		(map) Each server will send all his top 10 to 1 server (let it be server 0). It should be fine, there are only 10*R of those records.
		(reduce) Server 0 will yield the top 10 across the network.

	Notes:

	The problem with the algorithm, like most big-data algorithms that don't use frameworks is handling failing servers. MapReduce takes care of it for you.
	The above algorithm can be translated to a 2 phases map-reduce algorithm pretty straight forward.
*/


/*
	2. Top K words in a document:
	This is an actaul problem on LintCode
	http://www.zrzahid.com/top-k-or-k-most-frequent-words-in-a-document/
	http://www.geeksforgeeks.org/find-the-k-most-frequent-words-from-a-file/
*/
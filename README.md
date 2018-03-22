This practise to find Anagrams from a dictionary has two versions:

1) AnagramFinder.java : this application would take two input agrauments. First, a filepath to the dictionary and Second, a word to find anagrams for.
To run this version 1, follow as:

hemil@hemil-Inspiron-13-7359:~/Realtor/Project$ javac AnagramFinder.java
hemil@hemil-Inspiron-13-7359:~/Realtor/Project$ java AnagramFinder dictionary.txt loop

Welcome to the Anagram Finder
-----------------------------

Execution finished in 552 ms
3 anagrams found for loop
loop, polo, pool


2) AnagramFinderProduction.java : this application would behave same as version1. But instead of stopping after one search, it would prompt the user to search for as many word as he likes until he 
executes "exit" command. This application would take only one input, a filepath to the dictionary.Once loaded, it would present the user with a command line to enter word for searching anagrams.

Run version 2 as follows:

hemil@hemil-Inspiron-13-7359:~/Realtor/Project$ javac AnagramFinderProduction.java 
hemil@hemil-Inspiron-13-7359:~/Realtor/Project$ java AnagramFinderProduction dictionary.txt 

Welcome to the Anagram Finder
-----------------------------
Dictionary loaded in 584 ms
AnagramFinder> loop
3 anagrams found for loop in 0 ms
loop, polo, pool

AnagramFinder> alerts
8 anagrams found for alerts in 0 ms
laster, lastre, rastle, relast, resalt, salter, slater, stelar

AnagramFinder> exit
hemil@hemil-Inspiron-13-7359:~/Realtor/Project$ 

I tested the application against two dictionary sets. 
for a dictionary containing 100K words, it loads in ~550-600ms and 479K words in ~880-920ms.
Once the dictionary is loaded, it has the combination of all the words and list of their possible anagrams. Lookup for each word happens in constant time O(1). 
For all the alternatives I thought, this approach provided the best performance.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
For version 2, I have tried different approaches to find the most optimal solution. 

Alternative 1: Instead of sorting the word by its characters, I tried to create a unique hash for each word. I assigned each character a prime number and calculated the hash as a result by multiplying the
corresponding value of each character. As each value is a prime number, it would generate unique number for unique combinations. But for words containing same letters, it would give same result. This
way we can store the results in the list.
To avoid the overflow, as the 26th prime number is 101, I assigned the vowels the lowest prime number. a=2, e=3, i=5, o=7, u=11. Thus bringing the result down by significant margin.
But when tested for performance, it would process the 100K words in around ~1200ms, which was considerably lower than our previously set standards.

Alternative 2: In this version, I tried to read the dictionary and store the words into a HashSet. Then find the possible anagrams for a word and check for each word if it exists in the dictionary.
For creating the HashSet dictionary, the time taken was somewhere near 900ms for 100K words and 1400ms for 479K words. This test was also below the approach taken previosly.


<-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->

To enable the aformentioned application in a large infrastructure, we can use an In-memory datastore such as Redis. Redis would maintain the Key-value pair for each word and its list containing the anagrams for the word. The advantage would be that redis store would be independent of the application. Also, if the system needs to shut-down, it can persist the data in a separate database layer and reload in redis store once the application restarts.

The size of the redis store or HashMap in the application would be same as the size of the dictionary. As each word is stored in the map/redis and size of each word is same as the characters in it, the size of the hashMap would be same as size of the dictionary. 
Here, the file containing 479K words is of size 4.5MB. Maintaining this size in memory against a ~0 ms retrival time, would be the most optimal solution in my opinion for this problem.




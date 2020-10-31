# Java Algorithms Tricks

1) Avoid algorithm with irregular memory access pattern; use linear data structures.
2) Use smaller data types
3) Use int instead of Integer -> is an object in Heap.
   1. https://dzone.com/articles/optimizing-memory-access-with-cpu-cache


4) What is transient keyword use for? And when to use it?
If we don't want to serialize any field we use it. Refer -> https://stackoverflow.com/questions/910374/why-does-java-have-transient-fields

5) Either use array or linked list 
    1) Arrays can be implemented with Objects[] -> Java has ArrayDeque
    2) Linked List can be implemented with just classes and Nodes -> Has linkedList
    
6) Can use implement linkedHashMap with arrays only?
7) Internal HashMaP of Java is heavy, bcs they have different sets of data structures for all kind of operations like converting map to keySet
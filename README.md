# Graph-Implementation
  
**Input**  
  
0 1  
1 0  
2 1  
3 4  
4 3 0 5 6 2  
5 4 6  
6 4 5  
  
**Output**  

Please choose an option...  
1- READ FROM FILE  
2- DFS  
3- BFS  
4- DISPLAY  
5- EXIT  

  
**DISPLAY**  
0 -> 1  
1 -> 0  
2 -> 1  
3 -> 4  
4 -> 3 -> 0 -> 5 -> 6 -> 2  
5 -> 4 -> 6  
6 -> 4 -> 5   
  
DFS(5)  
[5]  
[5, 4]  
[5, 4, 3]  
[5, 4, 3, 0]  
[5, 4, 3, 0, 1]  
[5, 4, 3, 0, 1, 6]  
[5, 4, 3, 0, 1, 6, 2]  
  
BFS(5)  
5 -> 4 -> 6 
4 -> 3 -> 0 -> 5 -> 6 -> 2  
6 -> 4 -> 5  
3 -> 4  
0 -> 1  
2 -> 1  
1 -> 0  

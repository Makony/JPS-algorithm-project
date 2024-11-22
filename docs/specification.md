# Project Specification 

---

### Programming Language
For this project, I am implementing the Jump Point Search (JPS) algorithm in Java. 
I also have some basic familiarity in C, C++ and Python, to be able peer review projects developed in these languages.


### Algorithms
The main algorithm implemented in this project is Jump Point Search (JPS), an optimized pathfinding algorithm that enhances the efficiency of traditional A* search on uniform-cost grids. The algorithm reduces unnecessary node expansions by "jumping" over nodes, thus targeting only critical nodes (known as "jump points") to reach the destination faster while maintaining path optimality.


### Data Structures
- Priority Queue (Min-Heap)
- Hash Map
- Grid Matrix (2D Array)
- Parent Map


### What problem will be solved?
The primary problem addressed by the JPS algorithm is the pathfinding optimization on a uniform-cost grid (where movement is allowed in horizontal, vertical, and diagonal directions). JPS is particularly useful in large grid-based environments where traditional A* algorithms become computationally expensive due to the high number of nodes expanded. By implementing JPS, this project aims to provide a solution for efficient and optimal pathfinding with reduced node expansions.


### Program Inputs
The program takes the following inputs:
- Grid Matrix: JPS evaluates the grid’s walkable and blocked cells to determine how far it can "jump" in each direction, only stopping at jump points or obstacles.

- Start and End coordinates of nodes: The start and end coordinates define the search boundaries to help JPS evaluate the shortest and most efficient path.


### Time and Space Complexity

- Best Case:
Time Complexity: O(n), where n is the number of jump points.

- Average Case:
Time Complexity: O(n log m), where n is the jump points and m is the number of nodes.

- Worst Case:
Time Complexity: O(m log m), similar to A* for sparse grids.


**Space Complexity** in all cases is O(m), where m is the total number of nodes.

---
Bachelor of Science in Computer Science (CS)

The language of the documentation is English.

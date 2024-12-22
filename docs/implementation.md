# Implementation Document
---
## General Structure of the Program

### Classes

1. **JPS**
   - The core logic of the JPS algorithm
   - Key Methods:
     - **findPath**:
  	- Entry point for pathfinding.
  	- Uses a priority queue for efficient node evaluation
  	- Calculates tentative G and F scores

     - **identifySuccessors**:
  	- Identifies valid successors by pruning unnecessary nodes

     - **jump**:
  	- Finds the next jump point 
  	- Checks for forced neighbors and valid directions recursively

     - **pruneDirections**:
  	- Prunes invalid directions based on the parent node's direction
  	- Considers all directions for the start node


2. **Grid**
   - Represents the 2D grid matrix
   - Key Methods:
     - **isWalkable**:
  	- Checks if a node is walkable

     - **getNeighbors**:
  	- Retrieves a list of valid neighbor nodes
     

3. **Node**
   - Represents a single node on the grid
   - Has attributes such as:
     - Coordinates
     - Parent
     - G, F scores



## Time and Space Complexity

### Time Complexity

- Worst case (Very dense grid with obstacles): The algorithm may need to expand all potential jump points along the path. Each operation in the priority queue takes O(log n).
Total complexity: O(n log n)

- Best case (Sparse grid): Direct path from the start node to the goal, because no obstacles were found along the path.
Total complexity: O(k log k), k number of jump points.


### Space Complexity

Open list stores nodes to be evaluated, space usage is proportional to the size of the grid O(n). 
Closed list stores visited nodes, space usage is also O(n).
G Scores list saves scores for nodes, space complexity is O(n).

## Big O Analysis Comparison

- **Comparison with A***:
  - JPS improves performance by eliminating redundant nodes and leveraging pruning.
  - A* expands all possible neighbors, doing more computational work.

| Algorithm | Time Complexity                              | Space Complexity |
|-----------|----------------------------------------------|------------------|
| A*        | Worst case: O(n log n), Best case: O(log n)  |    O(n)          |
| JPS       | Worst case: O(n log n), Best case: O(k log k)|    O(n)          |



## Shortcomings

   - Only suitable for uniform-cost grids
   - Is faster than A* only on large grids
   - Probably could be more optimized for better runtime

## References
https://zerowidth.com/2013/a-visual-explanation-of-jump-point-search/

---

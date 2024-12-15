# **User Guide**

---

## **Java Environment Setup**
To run this project, ensure you have the Java Development Kit (JDK) installed. If it's not the case, visit [Oracle's JDK](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://openjdk.org/).

To check if you have JDK installed, open a terminal or command prompt and run:
     ```bash
     java -version
     javac -version
     ```run:


## **Run the Program**

### **1. Clone the Repository**


### **2. Compile the Source Files**
In the project directory compile the Java source files:
```bash
javac -d bin src/*.java
```
Now the compiled `.class` files are in the `bin` directory.

### **3. Run the Program**
The project provides three modes of execution. Use the following commands based on the functionality you want to explore:

#### **Create your own inputs**
Run the program to create your own inputs, such as grid size, number of obstacles, and node coordinates:
```bash
java -cp bin Main
```

#### **Compare JPS vs. A***
Compare the JPS algorithm with the A* algorithm on a dense grid:
```bash
java -cp bin Compare
```
- **Details**:
  - Uses a **3000x3000 dense grid** with obstacles.
  - Demonstrates that JPS computes paths faster than A*.

#### **Compare JPS vs. A* on a real map**
Run the program to benchmark the algorithms on a real-world Berlin map:
```bash
java -cp bin CompareOnMap
```
- **Details**:
  - Operates on a **1024x1024 Berlin map** loaded from a `.map` file.
  - Benchmarks both algorithms for multiple start and goal positions using different scenarios from a .scen file.

---

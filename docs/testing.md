# **Testing**

---

## **Steps to test the program**

#### **1. Step**
To compile all the source and test files, including the necessary JUnit5 `.jar` files, run the following command::
```bash
javac -d bin -cp "lib/junit-jupiter-api-5.11.3.jar;lib/junit-jupiter-engine-5.11.3.jar;lib/junit-platform-engine-1.11.3.jar;lib/junit-platform-launcher-1.11.3.jar;lib/junit-platform-commons-1.11.3.jar;lib/apiguardian-api-1.1.2.jar" src/*.java test/*.java
```

- If you have already compiled the source files, this command will add the compiled `.class` files for the tests.
- If you have not compiled the source files before, this will create a `bin` directory at the root of the project containing all the compiled `.class` files.


#### **2. Step**
To run the tests and see the results, use the following command:
```bash
java -javaagent:lib/jacocoagent.jar=destfile=jacoco.exec -jar lib/junit-platform-console-standalone-1.11.3.jar --class-path bin --scan-classpath
```
This will execute all tests in the `bin` directory and output the results to the console.

#### **3. Step**
To generate a test coverage report, run the following command:
```bash
java -jar lib/jacococli.jar report jacoco.exec --classfiles bin --sourcefiles src --html report
```

This will create a `report` directory at the root of the project, containing an `.html` file that provides the test coverage details.


## **What has been tested and how?**
- The program's individual parts or methods have been tested using JUnit5.
- The tests were run using the JUnit5 platform via the .jar file.
- The JaCoCo library was used to measure how much of the source code was covered by the tests.

## **What are the inputs?**
- Inputs are a variety of valid and edge-case scenarios that test the expected functionality of the methods. It also includes invalid or unexpected cases to ensure the program handles errors correctly.


## **Coverage report**
![image](https://github.com/user-attachments/assets/c70dd2f3-4a53-444d-bfcf-0ae426b4fd58)


---


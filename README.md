# aspireapp-auto
1. Clone github repository: git clone https://github.com/baongocpt/aspireapp-auto.git
2. Install Java jdk. Note: Java might be installed already on Mac OS
3. Install Maven on Mac OS : **brew install maven**
Install Maven on Windows: https://maven.apache.org/install.html
4. Check the version of Chrome and download chromedriver with correct version
Location of chromedriver: **src/main/resource/**
Note: Current chromedriver for Mac OS in this folder only supports for MacOS with Apple chip
Please refer more version at: https://chromedriver.storage.googleapis.com/index.html
5. Cd to Project folder: **mvn clean test**
To run only 1 test: **mvn test -DTest=Test1**

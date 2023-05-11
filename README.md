# selenium-java-cucumber
Selenium with Java and Cucumber starter project :wave:

- The project contains 1 feature file and 1 Runner and 1 steps definition class
- You can run the project test cases by running the testSuite.xml from IDE directly or from command line as
 ```
 mvn clean test -DsuiteXmlFile=testSuite
 ```

- The default browser is 'chrome-headles' but You can configure the tests to run with one of the following browsers 
[chrome, chrome-headless, edge, firefox] by updating the browser name value in the testSuite.xml file by one of the above values

- **Tech Stack:** Selenium JAVA, TestNG, Cucumber and Page Object Model pattern.

## The logger in the project  :smiley:
- I Only log the error level when test cases failed,
  also I take a screenshot for it and save them in /screenshots folder


## The Reports :tada:
- The Project Support **cucumber report** by default you can find it on 
``
target/cucumber-html-report.html
``
- The project also support the **Allure** report for generating the reports after running, The reports files located in project root file under ``/allure-results folder``
- but you need to install Allure in your local machine to be able to read these reports

- If you are on **windows** you can execute the bellow commands to install Allure:  :shipit:
```
 Set-ExecutionPolicy RemoteSigned -scope CurrentUser
 iwr -useb get.scoop.sh | iex
 scoop install allure
```
- if you run on **Linux**:
```
 sudo apt-add-repository ppa:qameta/allure
 sudo apt-get update
 sudo apt-get install allure
```
- If you are a lucky man and run on **Mac** it's only one command:
```
 brew install allure
```
- After install the Allure on your machine and run the one testSuites API or UI justrun this command to open the report:
 ```
 allure serve allure-results
 ```

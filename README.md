# Selenium Java Automation Code with TestNG

This repository contains a Selenium Java automation code using the TestNG framework. The code is designed to test MX Techies Applications and ensure its functionality meets the requirements.

## Prerequisites
- Java JDK (version 17 or above)
- Maven
- Eclipse IDE (or any IDE of your choice)
- Selenium WebDriver
- TestNG

## Getting Started
1. Clone this repository to your local machine using `git clone https://github.com/deshikselvaraaj/MXTechies.git
2. Open Eclipse IDE (or any IDE of your choice)
3. Import the project into Eclipse by selecting `File > Import > Existing Projects into Workspace` and browse to the project directory.
4. Configure your environment with all the required dependencies mentioned in the Prerequisites section.
5. Update the TestNG XML file located at src/test/resources to match your desired test suite configuration.
6. Run the project by right-clicking on the TestNG XML file and selecting `Run As > TestNG Suite`.

## Structure
The project is structured as follows:

```
├── src/main/java
│   └── com.utils.tests		             # Contains common java functionalities.
├── src/main/resources
│	├──config.properties		     # Contains key value pairs which are used within the program.
│	└──log4j.properties		     # Contains the properties of log4j to format logs.
├── src/test/java
│   ├── com.test.pages		             # Contains operations of all web elements of webpages for all the test cases.
│   ├── com.test.testcases		     # Contains all the test cases.
│   └── com.test.wrappers		     # Contains all the common functionalties that are required to interact with an element.
├── test-output                              # Contains the test results in HTML format.
├── testng.xml                               # TestNG XML file for configuring the test suite.
└── README.md                                # This file.


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)

## Acknowledgments
- [Selenium WebDriver](https://www.selenium.dev/documentation/en/webdriver/)
- [TestNG](https://testng.org/doc/documentation-main.html)
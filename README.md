# smart apps autotest


**Technology Stack for AT**
- Java ✅
- Maven ✅
- Junit 5 ✅
- AssertJ ✅
- Selenium ✅ (using PageObject Pattern)
- Allure Report ✅
- Logs by log4j ✅
- Partly selenoid ➖


- Implemented the possibility of cross-browser testing (chrome, firefox) ✅
- Implemented the possibility of running tests in parallel (watch junit-platform.properties) ✅

#### Notes

1) There is trouble with remote launching of tests: the Selenoid containers are launched successfully, AT begin to run - website opens (I can see it from the recorded videos, for ex.), 
but the tests fall with the next stack trace:

`org.openqa.selenium.remote.ErrorCodes toStatus
  INFO: HTTP Status: '404' -> incorrect JSON status mapping for 'unknown error' (500 expected)
  авг 27, 2021 1:54:49 PM org.openqa.selenium.remote.ErrorCodes toStatus 
-> - - - - [ERROR] tests.PizzeriaTest.checkNavigationViaCategories  Time elapsed: 101.699 s  <<< ERROR!
org.openqa.selenium.json.JsonException:
Expected to read a START_MAP but instead have: END. Last 0 characters read:`

So now I am looking for a solution to this problem.


2. Also I disable first test - because of unexpected behaviour of keyword search when performing tests: 
if the values are entered automatically, the site does not find dishes. But if you specify the same value manually, the search will work

### HOW TO START TEST

List of additional parameters for tests:
- `useSelenoid` - use or not selenoid (remote / local launch) = `true, false`
- `browser` - name of the test browser = `chrome, firefox`
- `browserVersion` - Browser version = chrome:`85.0, 86.0`, firefox:`77.0, 78.0`
- `enableVNC` - Remote access to the container of Selenoid = `true, false`
- `enableVideo` - Record video in Selenoid = `true, false`
- `enableLog` - Logs in Selenoid = `true, false`<br>

Examples of commands:

`mvn clean test -DuseSelenoid=false -Dbrowser="chrome" -DbrowserVersion="86.0"` - just local launch with chrome

`mvn clean test -DuseSelenoid=false` - local launch with parameters from application.properties

+  To deploy the infrastructure / download the necessary images and run tests remotely, you need to specify in terminal commands:
   `cd .\infrastructure\`
   `cd .\start_selenoid.sh`

but with remote launch, as I said earlier, tests will fall =( 


- To turn off parallel launch just set 'false' in first parameter in junit-platform.properties
         







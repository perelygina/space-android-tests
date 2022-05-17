# Appium tests for Space android application

Use the command to run all tests
`./gradlew test`

Tests run in GitHub Actions on every push. 
To view the test results, download the allure-report.zip and unzip it.<br>
Then open the report using the command `allure open /path/to/allure-report`

Tests are written in Kotlin but widgets are written in java. Appium uses reflection to initialize elements in widgets. I failed to work with widgets using only Kotlin.
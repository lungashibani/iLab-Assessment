# PetStoreApiTesting
Sample framework for API testing with Rest Assured,Cucumber and TestNG. 

Framework makes use of RestAssuared API for performing webservice operation and Cucumber framework for achiveing BDD approach.
Cucumber feature file location : "/src/test/java/features/"
TestNG runner class is used for running the test.

Tests in feature file contains tags (eg: @Test1,@PetTest etc). Set of test with similar feature tags can be run using the maven command "mvn test verify -Dcucumber.options="--tags @PetTest"

POM.xml has build tags for generating feature-view cucumber html reports which if not run as an maven will not be generated.
"cucumber-html-reports.zip" [overview-features.html] contains the cucumber html report.

"config.properties" file contains the base urls of the application. Any change to url is to be updated here.

POJO classes are used to store pet and order details for both request and response details whereever achiveable.

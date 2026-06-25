package org.example.testng;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;

public class AllTestNGAnnotationsDemo {

    /**
     * Runs once before the entire test suite (all tests in testng.xml).
     * Typical use: Open shared resources, init reporting.
     */
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("@BeforeSuite -> Initialize suite resources");
    }

    /**
     * Runs once after the entire test suite finishes.
     * Typical use: Close shared resources, finalize reporting.
     */
    @AfterSuite
    public void afterSuite() {
        System.out.println("@AfterSuite -> Clean up suite resources");
    }

    /**
     * Runs once before <test> block in testng.xml.
     * Typical use: Set up test-level configs.
     */
    @BeforeTest
    public void beforeTest() {
        System.out.println("@BeforeTest -> Setup for <test> block");
    }

    /**
     * Runs once after <test> block.
     */
    @AfterTest
    public void afterTest() {
        System.out.println("@AfterTest -> Teardown for <test> block");
    }

    /**
     * Runs once before any method in this class.
     * Typical use: Initialize class-level dependencies.
     */
    @BeforeClass
    public void beforeClass() {
        System.out.println("@BeforeClass -> Initialize AllAnnotationsDemo class");
    }

    /**
     * Runs once after all methods in this class.
     */
    @AfterClass
    public void afterClass() {
        System.out.println("@AfterClass -> Destroy AllAnnotationsDemo class");
    }

    /**
     * Runs before each @Test method.
     * Typical use: Navigate to page, reset state, create fresh test data.
     */
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("@BeforeMethod -> Setup per-test method");
    }

    /**
     * Runs after each @Test method.
     * Typical use: Reset mocks, clean temp files, log post-conditions.
     */
    @AfterMethod
    public void afterMethod() {
        System.out.println("@AfterMethod -> Teardown per-test method");
    }

    /**
     * Inject parameters from testng.xml
     * @Optional lets you set a default if the parameter is missing.
     */
    @Test(groups = {"sanity"}, priority = 1)
    @Parameters({"baseUrl", "env"})
    public void testWithParameters(@Optional("http://localhost") String baseUrl,
                                   @Optional("dev") String env) {
        System.out.println("@Test (parameters) -> baseUrl=" + baseUrl + ", env=" + env);
        Assert.assertTrue(baseUrl.startsWith("http"));
    }

    /**
     * DataProvider supplies multiple sets of inputs to a single test method.
     * This method will run once per data set.
     */
    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"admin", "admin@123"},
                {"qa", "qa@123"},
                {"user", "user@123"}
        };
    }

    @Test(dataProvider = "loginData", groups = {"sanity", "regression"}, priority = 2)
    public void testWithDataProvider(String username, String password) {
        System.out.println("@Test (data) -> " + username + " / " + password);
        Assert.assertFalse(username.isBlank());
    }

    /**
     * Demonstrates expectedExceptions: test passes if exceptions are thrown.
     */
    @Test(expectedExceptions = {ArithmeticException.class}, priority = 3)
    public void testExpectedException() {
        System.out.println("@Test (expectedExceptions) -> dividing by zero");
        int x = 1 / 0; // throws ArithmeticException
    }

    /**
     * Demonstrates timeOut: test fails if not completed within given milliseconds.
     */
    @Test(timeOut = 500, priority = 4)
    public void testWithTimeout() throws InterruptedException {
        System.out.println("@Test (timeOut) -> must finish within 500ms");
        Thread.sleep(200); // within timeout
    }

    /**
     * Demonstrates invocationCount (run test multiple times) and threadPoolSize (parallel).
     */
    @Test(invocationCount = 3, threadPoolSize = 2, priority = 5)
    public void testRepeated() {
        System.out.println("@Test (invocationCount) -> " + Thread.currentThread().getName());
        Assert.assertTrue(true);
    }

    /**
     * Demonstrates dependsOnMethods: runs only after the dependency passes.
     */
    @Test(priority = 6)
    public void setupResource() {
        System.out.println("@Test (setupResource) -> resource ready");
    }

    @Test(dependsOnMethods = {"setupResource"}, priority = 7)
    public void testDependsOnSetup() {
        System.out.println("@Test (dependsOnMethods) -> running after setupResource");
        Assert.assertEquals(2 + 2, 4);
    }

    /**
     * Demonstrates enabled=false: this test will be skipped.
     */
    @Test(enabled = false, priority = 8)
    public void disabledTest() {
        System.out.println("You will not see this, test is disabled");
    }

    /**
     * Demonstrates groups: you can run specific groups from testng.xml.
     */
    @Test(groups = {"regression"}, priority = 9)
    public void regressionOnlyTest() {
        System.out.println("@Test (groups=regression) -> executing regression test");
        Assert.assertTrue(Arrays.asList("a", "b", "c").contains("b"));
    }

    /**
     * Demonstrates @BeforeGroups / @AfterGroups for group-specific setup/teardown.
     */
    @BeforeGroups(groups = {"api"})
    public void beforeApiGroup() {
        System.out.println("@BeforeGroups(api) -> setup API group");
    }

    @AfterGroups(groups = {"api"})
    public void afterApiGroup() {
        System.out.println("@AfterGroups(api) -> teardown API group");
    }

    @Test(groups = {"api"}, priority = 10)
    public void apiGroupTest1() {
        System.out.println("@Test (api group) -> API test #1");
    }

    @Test(groups = {"api"}, priority = 11)
    public void apiGroupTest2() {
        System.out.println("@Test (api group) -> API test #2");
    }
}


package org.example.testng;

import org.testng.annotations.*;

public class TestNGAnnotationsHirarchy {
    // test case 1
    @Test(priority = 0)
    public void testCase1() {
        System.out.println("in test case 1");
    }

    @Test(priority = 1)
    public void aTestCase2() {
        System.out.println("in test case 2");
    }
//    // test case 2
//    @Test
//    public void testCase2() {
//        System.out.println("in test case 2");
//    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("in afterMethod");
    }
    @BeforeClass
    public void beforeClass() {
        System.out.println("in beforeClass");
    }
    @AfterClass
    public void afterClass() {
        System.out.println("in afterClass");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("in afterTest");
    }
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("in beforeSuite");
    }
    @AfterSuite
    public void afterSuite() {
        System.out.println("in afterSuite");
    }


    @BeforeMethod
    public void beforeMethod() {
        System.out.println("in beforeMethod");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("in beforeTest");
    }
}

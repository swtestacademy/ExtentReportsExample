package utils.ExtentReports;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

        /* OB: A map extentTestMap holds the information of the thread id and ExtentTest.
         *     ExtentReports instance created by calling getReporter() method from ExtentManager.
         *     At startTest() method, an instance of ExtentTest created and put into extentTestMap.
         *     At endTest() method, test ends and thread id got from extentTestMap.
         *     At getTest() method, information held in extentTestMap called.
         */

public class ExtentTestManager {
    static Map extentTestMap = new HashMap();
    static ExtentReports extent = ExtentManager.getReporter();

    public static synchronized ExtentTest getTest() {
        return (ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void endTest() {
        extent.endTest((ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.startTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
}
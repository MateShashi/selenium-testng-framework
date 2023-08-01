package example.example.tests;

import example.example.pages.DemoPage;
import org.testng.annotations.Test;

@Test(testName = "Verify User registration on web application", description = "Test description")
public class DemoTest1 extends BaseTest{
    @Test
    public void registerUser() throws InterruptedException {
        DemoPage demoPage = new DemoPage(driver);
        demoPage.loadHomePage();
        Thread.sleep(2000);
        demoPage.getStarted();
        Thread.sleep(3000);
        demoPage.verifyUserRegistration();
        System.out.println("##DemoTest-1:- User Details entered successfully");
        Thread.sleep(1000);
    }
}

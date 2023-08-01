package example.example.tests;

import example.example.pages.DemoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(testName = "Verify menus on web application", description = "Test description")
public class DemoTest2 extends BaseTest{
    @Test
    public void verifyApplicationMenus() throws InterruptedException {
        DemoPage demoPage = new DemoPage(driver);
        demoPage.loadHomePage();
        Thread.sleep(3000);

        String menuName1 = demoPage.getMenuName("home");
        Assert.assertTrue(menuName1.contains("HOME"));

        String menuName2 = demoPage.getMenuName("contact");
        Assert.assertTrue(menuName2.contains("CONTACT US"));

        String menuName3 = demoPage.getMenuName("aboutus");
        Assert.assertTrue(menuName3.contains("ABOUT US"));

        String menuName4 = demoPage.getMenuName("signup");
        Assert.assertTrue(menuName4.contains("SIGNUP"));

        System.out.println("##DemoTest-2:- Following Menus are displayed on the application: "+menuName1+", "+menuName2+", "+menuName3+", "+menuName4);
    }

}

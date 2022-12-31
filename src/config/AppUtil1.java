package config;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import commonFunction.AdminLoginPage;
import commonFunction.AdminLogoutPage;

public class AppUtil1 {
public static WebDriver driver;
@BeforeTest
public static void setup() throws Throwable
{
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://www.orangehrm.qedgetech.com/");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	AdminLoginPage login = PageFactory.initElements(driver, AdminLoginPage.class);
	login.verifyLogin("Admin", "Qedge123!@#");
}
@AfterTest
public static void teardown()
{
	AdminLogoutPage logout = PageFactory.initElements(driver, AdminLogoutPage.class);
	logout.VerifyLogout();
}
}
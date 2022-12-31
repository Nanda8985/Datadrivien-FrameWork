package commonFunction;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
	// method for login
	public static boolean verifyLogin(String username,String password)
	{
		driver.get(config.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath(config.getProperty("ObjUser"))).sendKeys(username);
	driver.findElement(By.xpath(config.getProperty("ObjPass"))).sendKeys(password);
	driver.findElement(By.xpath(config.getProperty("ObjLogin"))).click();
	String expected = "dashboard";
	String actual = driver.getCurrentUrl();
	if(actual.contains(expected))
	{
		Reporter.log("login success::"+expected+"  "+actual);
		return true;
	}
	else
	{
		String message = driver.findElement(By.xpath(config.getProperty("ObjErrormessage"))).getText();
		Reporter.log(message+"  "+expected+"  "+actual,true);
		return false;
		
	}
	
	}
}




package commonFunction;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLogoutPage {
@FindBy(id = "welcome")
WebElement clickWelcome;
@FindBy(linkText = "Logout")
WebElement clickLogout;
	public void VerifyLogout()
	{
		clickWelcome.click();
		clickLogout.click();
	}
	
	
}

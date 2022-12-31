package commonFunction;

import java.awt.Desktop.Action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AddEmpPage {
	WebDriver driver;
	public AddEmpPage(WebDriver driver)
	{
		this.driver = driver;
	}
	//Define Repositry
	@FindBy(xpath = "//b[normalize-space()='PIM']")
    WebElement ClickPim;
	@FindBy(name = "btnAdd")
	WebElement ClickAddBtn;
	@FindBy(name = "firstName")
	WebElement EnterFname;
	@FindBy(name = "middleName")
	WebElement EnterMname;
	@FindBy(name = "lastName")
	WebElement EnterLname;
	@FindBy(name = "employeeId")
	WebElement BeforEid;
	@FindBy(id = "btnSave")
	WebElement ClickSaveBtn;
	@FindBy(name = "personal[txtEmployeeId]")
	WebElement AfterEid;
	public boolean VerifyEmp(String Fname, String Mname, String Lname)
	{
		Actions ac = new Actions(driver);
		ac.moveToElement(ClickPim).click().perform();
		ac.moveToElement(ClickAddBtn).click().perform();
		this.EnterFname.sendKeys(Fname);
		this.EnterMname.sendKeys(Mname);
		this.EnterLname.sendKeys(Lname);
		String ExpectedEID = this.BeforEid.getAttribute("value");
		this.ClickSaveBtn.click();
		String ActualEID = this.AfterEid.getAttribute("value");
		if(ExpectedEID.equalsIgnoreCase(ActualEID))
		{
			Reporter.log("Add emp success::"+ExpectedEID+"  "+ActualEID,true);
			return true;
		}
		else
		{
			Reporter.log("Add emp FAil::"+ExpectedEID+"  "+ActualEID,true);
			return false;
		}
	}
	
}

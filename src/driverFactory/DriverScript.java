package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunction.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtiles;

public class DriverScript extends AppUtil {
String inputpath = "E:\\EclipseSelenium-WorkSpace\\DataDriven_Framework\\TestInput\\TestData1.xlsx";
String outputpath = "E:\\EclipseSelenium-WorkSpace\\DataDriven_Framework\\TestOutput\\DataDrivienResults.xlsx";
@Test
public void starttest() throws Throwable
{
	// create object for excelfile class
	ExcelFileUtiles xl = new ExcelFileUtiles(inputpath);
	//count no rows in login sheet 
	int rc = xl.rowCount("Login");
	Reporter.log("no of rows in a sheet::"+rc,true);
	for(int i=1;i<=rc;i++)
	{
		String user = xl.getCellData("Login", i, 0);
		String pass = xl.getCellData("Login", i, 1);
		// call login method
		boolean res = FunctionLibrary.verifyLogin(user, pass);
		if(res)
		{
			//if res is true write as login success into result cell
			xl.setCellData("Login", i, 2, "login is success", outputpath);
			xl.setCellData("Login", i, 3, "pass", outputpath);
		}
		else
		{
			// take screen shot for fail test
			File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File("./Screenshot/Iteration/"+i+" "+"Loginpage.png"));
			xl.setCellData("Login", i, 2, "login is fail", outputpath);
			xl.setCellData("Login", i, 3, "fail", outputpath);
			
		}
	}
	
}




}
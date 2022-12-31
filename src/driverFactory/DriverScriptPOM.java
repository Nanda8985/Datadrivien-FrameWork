package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test; 
import commonFunction.AddEmpPage;
import config.AppUtil1;
import utilities.ExcelFileUtiles;

public class DriverScriptPOM extends AppUtil1  {
     String inputpath = "E:\\EclipseSelenium-WorkSpace\\DataDriven_Framework\\TestInput\\Employee.xlsx";
     String outputpath = "E:\\EclipseSelenium-WorkSpace\\DataDriven_Framework\\TestOutput\\POMResults.xlsx";
     @Test
     public void startTest() throws Throwable
     {
    	ExcelFileUtiles xl = new ExcelFileUtiles(inputpath);
    	int rc = xl.rowCount("EmpData");
    	Reporter.log("No of   rows are::"+rc,true);
    	for(int i=1;i<=rc;i++)
    	{
    	 String Fname  = xl.getCellData("EmpData", i, 0);
    	 String Mname  = xl.getCellData("EmpData", i, 1);
    	 String Lname  = xl.getCellData("EmpData", i, 2);
    	 AddEmpPage emp = PageFactory.initElements(driver, AddEmpPage.class);
    	 boolean res = emp.VerifyEmp(Fname, Mname, Lname);
    	 if(res)
    	 {
    		 xl.setCellData("EmpData", i, 3, "pass", outputpath);
    		
    	 }
    	 else
    	 {
    		 xl.setCellData("EmpData", i, 3, "fail", outputpath);
    	 }
    	 
    	}
    	 
     }
	
	
	}


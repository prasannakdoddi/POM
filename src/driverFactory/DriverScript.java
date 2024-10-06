package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import applicationLayer.Com.Pages.CustomerPage;
import applicationLayer.Com.Pages.LoginPage;
import applicationLayer.Com.Pages.LogoutPage;
import config.AppUtil;
import utilities.ExcelFileUtils;

public class DriverScript extends AppUtil{

	
	String inputpath = "./FileInput/Customer Test Data.xlsx";
	String outputpath = "./FileOutput/Customer Test Result.xlsx";
	String adminReportspath = "./Extent Reports/Admin Report.html"; 
	String customerReportspath = "./Extent Reports//Customer Report.html";
	
	ExtentReports reports;
	ExtentTest logger;
	
	@Test(priority = 0)
	public void adminLoginValidation() throws Throwable
	{
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);
		LogoutPage logout = PageFactory.initElements(driver, LogoutPage.class);
		ExcelFileUtils xl = new ExcelFileUtils(inputpath);
		
		reports = new ExtentReports(adminReportspath);
		logger = reports.startTest("Admin Login");
		logger.assignAuthor("Prasanna");
		
		String Username = xl.getCellData("Admin Login", 1, 0);
		String Password = xl.getCellData("Admin Login", 1, 1);
		
		logger.log(LogStatus.INFO, Username + Password);
		
		login.adminLogin(Username, Password);
		
		String Expected = "dashboard";
		String Actual = driver.getCurrentUrl();
	
		if(Actual.contains(Expected))
		{
			Reporter.log("Login Successfull with " + Username + " & " + Password + " :: Test Pass",true);
			logger.log(LogStatus.PASS, "Login sucessfull with " + Username + " & " + Password);
			//xl.setCellData("Admin Login", 1, 2, "Pass", outputpath);
		}
		else
		{
			Reporter.log("Login not Success with " + Username + " & " + Password + " :: Test Fail",true);
			logger.log(LogStatus.FAIL, "Login not sucess with " + Username + " & " + Password);
			//xl.setCellData("Admin Login", 1, 2, "Fail", outputpath);
		}
		
		reports.endTest(logger);
		reports.flush();
		
		logout.adminLogout();
	}
	
	@Test(priority = 1)
	public void addingCustomer() throws Throwable
	{
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);
		LogoutPage logout = PageFactory.initElements(driver, LogoutPage.class);
		CustomerPage customer = PageFactory.initElements(driver, CustomerPage.class);
		ExcelFileUtils xl = new ExcelFileUtils(inputpath);
		
		reports = new ExtentReports(customerReportspath);
		logger = reports.startTest("Customer");
		logger.assignAuthor("Prasanna");
		
		String Username = xl.getCellData("Admin Login", 1, 0);
		String Password = xl.getCellData("Admin Login", 1, 1);
		
		logger.log(LogStatus.INFO, Username + Password);
		
		login.adminLogin(Username, Password);
		
		int rowCount = xl.rowCount("Customer");
		
		for(int i=1; i<= rowCount; i++)
		{
			String cname = xl.getCellData("Customer", i, 0);
			String address = xl.getCellData("Customer", i, 1);
			String City = xl.getCellData("Customer", i, 2);
			String Country = xl.getCellData("Customer", i, 3);
			String ContactPerson = xl.getCellData("Customer", i, 4);
			String PhoneNumber = xl.getCellData("Customer", i, 5);
			String EmailId = xl.getCellData("Customer", i, 6);
			String Mobile = xl.getCellData("Customer", i, 7);
			String Notes = xl.getCellData("Customer", i, 8);
			
			boolean status = customer.addCustomer(cname, address, City, Country, ContactPerson, PhoneNumber, EmailId, Mobile, Notes);
			if(status)
			{
				logger.log(LogStatus.PASS, "Customer add success :: Test Pass");
				xl.setCellData("Customer", i, 9, "Pass", outputpath);
			}
			else
			{
				logger.log(LogStatus.FAIL, "Customer add not success :: Test Fail");
				xl.setCellData("Customer", i, 9, "Fail", outputpath);
			}
			
			reports.endTest(logger);
			reports.flush();
			
		}
		
		logout.adminLogout();
		
	}
	
	
}

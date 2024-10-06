package applicationLayer.Com.Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class CustomerPage {

	WebDriver driver;
	public CustomerPage(WebDriver driver)
	{
		this.driver = driver;
	}

	@FindBy(xpath = "(//a[text()='Customers'])[2]")
	WebElement ObjClickCostomerLink;
	
	@FindBy(xpath = "(//span[@data-caption = 'Add'])[1]")
	WebElement ObjClickAddIcon;
	
	@FindBy(name = "x_Customer_Number")
	WebElement ObjCustomerNumber;
	
	@FindBy(name= "x_Customer_Name")
	WebElement ObjCustomerName;
	
	@FindBy(name = "x_Address")
	WebElement ObjCustomerAddress;
	
	@FindBy(name = "x_City")
	WebElement ObjCustomerCity;
	
	@FindBy(name = "x_Country")
	WebElement ObjCustomerCountry;
	
	@FindBy(name = "x_Contact_Person")
	WebElement ObjCustomerContactPerson;
	
	@FindBy(name = "x_Phone_Number")
	WebElement ObjCustomerPhoneNumber;
	
	@FindBy(name = "x__Email")
	WebElement ObjCustomerEmail;
	
	@FindBy(name = "x_Mobile_Number")
	WebElement OBJCustomerMobile;
	
	@FindBy(name = "x_Notes")
	WebElement ObjCustomerNotes;
	
	@FindBy(id = "btnAction")
	WebElement ObjClickAdd;
	
	@FindBy(id = "btnCancel")
	WebElement ObjClickCancel;
	
	@FindBy(xpath = "//button[text() = 'OK!']")
	WebElement ObjClickConfirmOk;
	
	@FindBy(xpath = "(//button[contains(text(),'Cancel')])[6]")
	WebElement ObjClickConfirmCancel;
	
	@FindBy(xpath = "//button[text() ='OK']")
	WebElement ObjClickAlertOk;
	
	@FindBy(xpath = "//button[@data-caption='Search Panel']")
	WebElement ObjClickSearchPanel;
	
	@FindBy(xpath = "//input[@id='psearch']")
	WebElement ObjSearchTextBox;
	
	@FindBy(xpath = "(//button[contains(text(),'Search')])[1]")
	WebElement ObjClickSearch;
	
	@FindBy(xpath ="//table[@class = 'table ewTable']/tbody/tr[1]/td[5]/div/span/span")
	WebElement ObjWebTable;
	
	public boolean addCustomer(String CustName, 
								String Address, 
								String City, 
								String Country, 
								String ContactPerson, 
								String Phone,
								String EmailId, 
								String Mobile, 
								String Notes) throws Throwable
	{
		String Expected_Data;
		Actions ac = new Actions(driver);
		
		ac.moveToElement(this.ObjClickCostomerLink).click().perform();
		Thread.sleep(2000);
		ac.moveToElement(this.ObjClickAddIcon).click().perform();
		Thread.sleep(2000);
		Expected_Data = this.ObjCustomerNumber.getAttribute("value");
		
		this.ObjCustomerName.sendKeys(CustName);
		this.ObjCustomerAddress.sendKeys(Address);
		this.ObjCustomerCity.sendKeys(City);
		this.ObjCustomerCountry.sendKeys(Country);
		this.ObjCustomerContactPerson.sendKeys(ContactPerson);
		this.ObjCustomerPhoneNumber.sendKeys(Phone);
		this.ObjCustomerEmail.sendKeys(EmailId);
		this.OBJCustomerMobile.sendKeys(Mobile);
		this.ObjCustomerNotes.sendKeys(Notes);
		this.ObjClickAdd.sendKeys(Keys.ENTER);
		
		this.ObjClickConfirmOk.click();
		this.ObjClickAlertOk.click();
		
		if(!this.ObjSearchTextBox.isDisplayed())
		{
			this.ObjClickSearchPanel.click();
		}
		
		this.ObjSearchTextBox.clear();
		this.ObjSearchTextBox.sendKeys(Expected_Data);
		this.ObjClickSearch.click();
		
		String Actual_Data = this.ObjWebTable.getText();
		if(Expected_Data.equals(Actual_Data))
		{
			Reporter.log("Customer " + Expected_Data + " Add Success :: Test Pass",true);
			return true;
		}
		else
		{
			Reporter.log("Customer " + Expected_Data + "  Add not Success :: Test Fail" + Expected_Data + " " + Actual_Data,true);
			return false;
		}
	}
	
}

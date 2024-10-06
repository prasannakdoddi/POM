package applicationLayer.Com.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage {

	@FindBy(id = "logout")
	WebElement ObjLogout;
	
	@FindBy(xpath = "//button[text()='OK!']")
	WebElement ObjOk;
	
	public void adminLogout()
	{
		ObjLogout.click();
		ObjOk.click();
		
	}
	
}

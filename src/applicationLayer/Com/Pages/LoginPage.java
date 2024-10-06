package applicationLayer.Com.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
	@FindBy(id = "username")
	WebElement ObjUname;
	
	@FindBy(id = "password")
	WebElement ObjPwd;
	
	@FindBy(id = "btnsubmit")
	WebElement ObjLogin;
	
	@FindBy(id = "btnreset")
	WebElement ObjReset;
	
	@FindBy(xpath = "//a[contains(text(),'Forgot Password')]")
	WebElement ObjForgotPassword;
	
	@FindBy(xpath = "//a[text()='Register']")
	WebElement ObjRegister;
	
	@FindBy(id = "ewLoginOptions")
	WebElement ObjLoginOptions;
	
	@FindBy(xpath = "//label[text()='Save my user name']")
	WebElement ObjOptSaveUser;
	
	@FindBy(xpath = "//label[text()='Auto login until I logout explicitly']")
	WebElement ObjOptAutoLogin;
	
	@FindBy(xpath = "//label[text()='Always ask for my user name and password']")
	WebElement ObjOptAlwaysAsk;
	
	public void adminLogin(String uname, String pwd) throws Throwable
	{
		ObjReset.click();
		Thread.sleep(2000);
		ObjUname.sendKeys(uname);
		ObjPwd.sendKeys(pwd);
		ObjLogin.click();
	}
}

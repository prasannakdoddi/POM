package config;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import utilities.ExcelFileUtils;


public class AppUtil {

	public WebDriver driver;
	public ExcelFileUtils xl;
	
	@BeforeTest
	public void setUp() throws Throwable
	{
		String inputpath = "./FileInput/Customer Test Data.xlsx";
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://webapp.qedgetech.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		xl = new ExcelFileUtils(inputpath);
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	
}

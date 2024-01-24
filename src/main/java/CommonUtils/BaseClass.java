package CommonUtils;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class BaseClass {
	
	public WebDriver dc ;
	public static WebDriver sdc;
	PropertyFileUtil putils=new PropertyFileUtil();
	
	@BeforeSuite
	public void bS()
	{
		System.out.println("Connect to DataBase");
	}
	
	@BeforeClass
	public void bC() throws IOException
	{
String BROWSER = putils.getDataFromPropertyFile("browser");
		
		//To Launch the Browser
		if(BROWSER.equalsIgnoreCase("Chrome")) {
			dc=new ChromeDriver();
		}
		else {
			dc=new EdgeDriver();
		}
		dc.manage().window().maximize();
		dc.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		dc.get(putils.getDataFromPropertyFile("url"));
	}
	
	@BeforeMethod()
	public void bM() throws IOException
	{
	//	STEP 6: Login to application
		dc.findElement(By.name("user_name")).sendKeys(putils.getDataFromPropertyFile("username"));
		dc.findElement(By.name("user_password")).sendKeys(putils.getDataFromPropertyFile("password"));
		dc.findElement(By.id("submitButton")).click();	
	}
	
	@Test
	public void LeadTest() throws IOException, InterruptedException
	{
		dc.findElement(By.xpath("(//a[text()='Leads'])[1]")).click();
		
		// STEP 8: click on + icon
			dc.findElement(By.xpath("//img[@alt='Create Lead...']")).click();
		
			// STEP 8: Enter first name
			dc.findElement(By.name("firstname")).sendKeys(putils.getDataFromPropertyFile("fname"));
			
			// STEP 8: Enter last name
			dc.findElement(By.name("lastname")).sendKeys(putils.getDataFromPropertyFile("lname"));
			
			// STEP 8: Enter company Name
			dc.findElement(By.name("company")).sendKeys(putils.getDataFromPropertyFile("company"));
			
		
			dc.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
			
			WebElement dropd = dc.findElement(By.name("assigned_group_id"));
			
			Select s=new Select(dropd);
			s.selectByVisibleText(putils.getDataFromPropertyFile("group"));
			
				dc.findElement(By.xpath("(//input[@name='button'])[3]")).click();
				
	}
	@AfterMethod
	public void aM() throws InterruptedException
	{
		WebElement ids1 = dc.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a=new Actions(dc);
		a.moveToElement(ids1).perform();
		Thread.sleep(2000);
	
		dc.findElement(By.xpath("//a[text()='Sign Out']")).click(); 
	}
	
	@AfterClass
	public void aC()
	{
		dc.quit();
	}
	
	@AfterSuite
	public void aS()
	{
		System.out.println("Disconnect from DataBase");
	}

}

package module;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import CommonUtils.PropertyFileUtil;

public class CreateLeadsModule {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriver dc ;
		PropertyFileUtil putils=new PropertyFileUtil();
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
		
	//	STEP 5: To load the url
		dc.get(putils.getDataFromPropertyFile("url"));
		
	//	STEP 6: Login to application
		dc.findElement(By.name("user_name")).sendKeys(putils.getDataFromPropertyFile("username"));
		dc.findElement(By.name("user_password")).sendKeys(putils.getDataFromPropertyFile("password"));
		dc.findElement(By.id("submitButton")).click();
		
	//	STEP 7: click on leads
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
			
			WebElement ids1 = dc.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			Actions a=new Actions(dc);
			a.moveToElement(ids1).perform();
		
			dc.findElement(By.xpath("//a[text()='Sign Out']")).click(); 
			
		

	}

}

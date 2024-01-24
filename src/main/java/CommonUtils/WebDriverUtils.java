package CommonUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

public class WebDriverUtils {
	public void maximize(WebDriver d) {
		
		d.manage().window().maximize();
	}
    public void minimize(WebDriver d) {
		
		d.manage().window().minimize();;
	}
    public void fullscreen(WebDriver d) 
    {
		
		d.manage().window().fullscreen();
	}
    
    public void Impicitwait(WebDriver d) 
    {
		
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
    public void handledropdown(WebElement target,String text) 
    {
		
     Select s=new Select(target);
     s.selectByVisibleText(text);
     
	}
    
    public void handledropdown(WebElement target,String text,int index) 
    {
		
     Select s=new Select(target);
     s.selectByIndex(index);
     
	}

	
    public void handledropdownvalue(WebElement target,String value) 
    {
		
     Select s=new Select(target);
     s.selectByValue(value);
     
	}
    public void mousehover(WebElement target,WebDriver d) 
    {
		
     Actions a=new Actions(d);
     a.moveToElement(target).perform();
    }
  
    public void rightclick(WebElement target,WebDriver d) 
    {
		
     Actions a=new Actions(d);
     a.contextClick(target);
     a.perform();
    }
    public void click(WebElement target,WebDriver d) 
    {
		
     Actions a=new Actions(d);
     a.click(target);
     a.perform();
     
    }
    public void SwitchTo(WebDriver d,String expectedUrl) {
    	 Set<String> ids = d.getWindowHandles();
	      System.out.println(ids);
	      
	      for (String st : ids) {
	    	 String currentUrl = d.switchTo().window(st).getCurrentUrl();
	   
			
	    	 if(currentUrl.contains(expectedUrl)){
	    		 break;
	    		 
	    	 }
	    	 
		}
	}

    public void toTakesScreenshot(WebDriver d) throws IOException {
	
    LocalDateTime ldt = LocalDateTime.now();
	String timestrap = ldt.toString().replace(':','-');
    TakesScreenshot ts=(TakesScreenshot)d;
	File temp = ts.getScreenshotAs(OutputType.FILE);
	File Dest =new File("./screenchots/timestrap.png");
	FileUtils.copyFile(temp, Dest);
    }
    
    public String toTakesScreenshotName(WebDriver d, String Screenshotname) throws IOException {
        LocalDateTime ldt = LocalDateTime.now();
    	String timestrap = ldt.toString().replace(':','-');
        TakesScreenshot ts=(TakesScreenshot)d;
    	File temp = ts.getScreenshotAs(OutputType.FILE);
    	File Dest =new File("./screenchots/"+Screenshotname+"timestrap"+".png");
    	FileUtils.copyFile(temp, Dest);
    	return Dest.getAbsolutePath();
        }
    
    
    public void  oKPopUp(WebDriver d) {
    	d.switchTo().alert().accept();
    	
    }
    public void  cancelPopUp(WebDriver d) {
    	d.switchTo().alert().dismiss();
    	
    }
    public void  textPopUp(WebDriver d) {
    	d.switchTo().alert().getText();
    	
    }
    public void  valuePopUp(WebDriver d,String text) {
    	d.switchTo().alert().sendKeys(text);
    	
    }
    public void frames(WebDriver d,int index) {
    	d.switchTo().frame(0);
    }
    public void frames(WebDriver d,String name) {
    	d.switchTo().frame(name);
    }
    public void frames(WebDriver d,WebElement element) {
    	d.switchTo().frame(element);
    }
    public void pageLoad(WebDriver d) {
    	d.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(0));
    }
    public void waitWebElementtodisplay(WebDriver d,String Expectedurl)
    {
    	WebDriverWait waits=new WebDriverWait(d,Duration.ofSeconds(0));
    	waits.until(ExpectedConditions.urlToBe(Expectedurl));
    }


}

package testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class LoginTest {
	ChromeDriver driver=new ChromeDriver();
	@BeforeTest
	public void StartSite() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
		driver.get("http://localhost:3000/");
		Thread.sleep(1000);
		WebElement login=driver.findElement(By.className("custom-btn"));
		login.click();
	}
	@BeforeMethod
	public void sleep() throws InterruptedException {
		Thread.sleep(2000);		
		WebElement email=driver.findElement(By.id("email"));
		email.clear();
		
		WebElement password=driver.findElement(By.id("password"));
		password.clear();
	}
	
	@Test(priority=0,description="Empty Fields")
	public void EmptyFields() throws InterruptedException {
		WebElement email=driver.findElement(By.id("email"));
		email.sendKeys("aditya@gmail.com");
		
		WebElement password=driver.findElement(By.id("password"));
		password.clear();
		
		WebElement login=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/form/button/span[1]"));
		login.click();
		Thread.sleep(1000);
		
		String expectedTitle="Fields cannot be empty";
		String actualTitle=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/div[2]")).getText().substring(0,22);
		
		Assert.assertEquals(expectedTitle,actualTitle);	
	}
	@Test(priority=1,description="Email not registered")
	public void InvalidEmail() throws InterruptedException {
		WebElement email=driver.findElement(By.id("email"));
		email.sendKeys("garbageemail@gmail.com");
		
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys("garbage");
		
		WebElement login=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/form/button/span[1]"));
		login.click();
		Thread.sleep(1000);
		
		String expectedTitle="No account registered with this email";
		String actualTitle=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/div[2]")).getText().substring(0,37);
		
		Assert.assertEquals(expectedTitle,actualTitle);	
	}
	@Test(priority=2,description="Invalid Credentials")
	public void InvalidPass() throws InterruptedException {
		WebElement email=driver.findElement(By.id("email"));
		email.sendKeys("srivas1234@gmail.com");
		
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys("aditya1");
		
		WebElement login=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/form/button/span[1]"));
		login.click();
		Thread.sleep(3000);
		
		String expectedTitle="Invalid Credentials";
		String actualTitle=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/div[2]")).getText().substring(0,19);
		
		Assert.assertEquals(expectedTitle,actualTitle);	
	}
	@Test(priority=4,description="Successful LogIN")
	public void Success() throws InterruptedException {
		WebElement email=driver.findElement(By.id("email"));
		email.sendKeys("srivas1234@gmail.com");
		
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys("rimjhim8");
		
		WebElement login=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/form/button/span[1]"));
		login.click();
		Thread.sleep(1000);
		
		String expectedNewUrl = "http://localhost:3000/journal";
	    String actualNewUrl = driver.getCurrentUrl();

		Assert.assertEquals(expectedNewUrl,actualNewUrl);		
	}
	
	@AfterTest
	public void Close() {
		driver.quit();
	}
}

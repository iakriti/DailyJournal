package testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JournalTest {
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
		
		WebElement email=driver.findElement(By.id("email"));
		email.sendKeys("srivas1234@gmail.com");
		
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys("rimjhim8");
		
		WebElement login1=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/form/button/span[1]"));
		login1.click();
	}
	
	@Test(priority=0,description="Empty Title")
	public void EmptyTitle() throws InterruptedException {
		Thread.sleep(1000);
		
		WebElement title=driver.findElement(By.name("title"));
		title.sendKeys("Day 1");
		//WebElement textarea=driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[2]/div/div/div/p/div/div/div[2]/div/div[2]/div/div/div/div/div/div/span"));
		((JavascriptExecutor) driver).executeScript("arguments[0].innerText = arguments[1];", driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[2]/div/div/div/p/div/div/div[2]/div/div[2]/div/div/div/div/div/div/span")), "Dear Journal, Its my first day. I'm happy but also feelling a little nervous i dont know why maybe because i have an interview today! okay bye!");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,40)", "");
		WebElement submit=driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/div[2]/div/div/div/p/div/div/div[3]/button"));
		submit.click();
		 
		Thread.sleep(1000);
		String expectedTitle="Journal Entry Created";
		String actualTitle=driver.findElement(By.className("alert")).getText().substring(0,21);
		
		Assert.assertEquals(expectedTitle,actualTitle);	
	}
	
	@AfterTest
	public void Close() {
		driver.quit();
	}
}

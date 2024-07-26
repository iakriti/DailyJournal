package testng;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchTest {
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
		Thread.sleep(1000);
		WebElement searchbtn=driver.findElement(By.xpath("/html/body/div/div[1]/button[2]"));
		searchbtn.click();
	}
	
	@Test(priority=0,description="Empty month and year")
	public void EmptyMonthOrYear() throws InterruptedException {
		Thread.sleep(1000);
		
		WebElement btn=driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/div/button"));
		btn.click();
		Thread.sleep(1000);
		String expectedTitle="Select both month and year";
		String actualTitle=driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div/div")).getText().substring(0,26);
		
		Assert.assertEquals(expectedTitle,actualTitle);
	}
	@Test(priority=1,description="Results searched")
	public void Search() throws InterruptedException {
		Thread.sleep(1000);
		
		WebElement cross=driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div/div/button/span"));
		cross.click();
		Select month = new Select(driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div[2]/div/select[1]")));
		month.selectByVisibleText("May");
		Select year = new Select(driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div[2]/div/select[2]")));
		year.selectByVisibleText("2024");
		WebElement btn=driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/div/button"));
		btn.click();
		Thread.sleep(1000);
		String expectedTitle="Search Results";
		String actualTitle=driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div/div/div/div/div[1]/div/div/h2")).getText().substring(0,14);
		
		Assert.assertEquals(expectedTitle,actualTitle);
	}
	@Test(priority=2,description="View journal")
	public void View() throws InterruptedException {
		Thread.sleep(1000);
		
		WebElement btn=driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div/div/div/div/div[2]/div/div/table/tbody/tr/td[3]/button"));
		btn.click();
		Thread.sleep(1000);
		String expectedTitle="×";
		String actualTitle=driver.findElement(By.xpath("/html/body/div[3]/div/div/div[1]/button/span[1]")).getText();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[1]/button/span[1]")).click();
		Assert.assertEquals(expectedTitle,actualTitle);
	}
	@Test(priority=3,description="Edit journal")
	public void Edit() throws InterruptedException {
		Thread.sleep(1000);
		
		WebElement btn=driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div/div/div/div/div[2]/div/div/table/tbody/tr/td[4]/button"));
		btn.click();
		Thread.sleep(1000);
		String expectedNewUrl = "http://localhost:3000/journal";
        String actualNewUrl = driver.getCurrentUrl();
        
        WebElement searchbtn=driver.findElement(By.xpath("/html/body/div/div[1]/button[2]"));
		searchbtn.click();
        Select month = new Select(driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div[2]/div/select[1]")));
		month.selectByVisibleText("May");
		Select year = new Select(driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div[2]/div/select[2]")));
		year.selectByVisibleText("2024");
		WebElement abtn=driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/div/button"));
		abtn.click();
		Thread.sleep(1000);
		Assert.assertEquals(expectedNewUrl,actualNewUrl);
	}
	
	@AfterTest
	public void Close() {
		driver.quit();
	}
}

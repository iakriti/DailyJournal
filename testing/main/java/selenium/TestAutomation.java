package selenium;

import org.openqa.selenium.support.ui.Select;
import java.time.Duration; 

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAutomation {
	public static void GoToSignUp(ChromeDriver driver) throws InterruptedException {
		WebElement login=driver.findElement(By.className("custom-btn"));
		login.click();
		
		Thread.sleep(2000);
		
		WebElement signup=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/form/div[3]/div[2]/a"));
		signup.click();
	}
	
	public static void FillSignUp(ChromeDriver driver) throws InterruptedException {
		WebElement firstname=driver.findElement(By.id("firstName"));
		firstname.sendKeys("Rimjhim");
		
		WebElement lastname=driver.findElement(By.id("lastName"));
		lastname.sendKeys("Singh");
		
		WebElement email=driver.findElement(By.id("email"));
		email.sendKeys("akrai123@gmail.com");
		
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys("rimjhim0");
		
		WebElement conpassword=driver.findElement(By.id("confirmPassword"));
		conpassword.sendKeys("rimjhim0");
		
		WebElement signup=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/form/button/span[1]"));
		signup.click();
		Thread.sleep(2000);
	}
	public static void LoginIn(ChromeDriver driver) throws InterruptedException {
		
//		WebElement logina=driver.findElement(By.className("custom-btn"));
//		logina.click();

		WebElement email=driver.findElement(By.id("email"));
		email.sendKeys("akrai123@gmail.com");
		
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys("rimjhim0");
		
		WebElement login=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/form/button/span[1]"));
		login.click();
	}
	public static void JournalEntry(ChromeDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		WebElement title=driver.findElement(By.name("title"));
		title.sendKeys("Day 1");
	
		Thread.sleep(1000);
		//WebElement textarea=driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[2]/div/div/div/p/div/div/div[2]/div/div[2]/div/div/div/div/div/div/span"));
		((JavascriptExecutor) driver).executeScript("arguments[0].innerText = arguments[1];", driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[2]/div/div/div/p/div/div/div[2]/div/div[2]/div/div/div/div/div/div/span")), "Dear Journal, Its my first day. I'm happy but also feelling a little nervous i dont know why maybe because i have an interview today! okay bye!");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,40)", "");
		WebElement submit=driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/div[2]/div/div/div/p/div/div/div[3]/button"));
		submit.click();
		js.executeScript("window.scrollBy(0,-40)", "");
		Thread.sleep(500);
	}
	public static void SearchJournal(ChromeDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		WebElement searchbtn=driver.findElement(By.xpath("/html/body/div/div[1]/button[2]"));
		searchbtn.click();
		Thread.sleep(1000);
		Select month = new Select(driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div[2]/div/select[1]")));
		month.selectByVisibleText("May");
		Select year = new Select(driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div[2]/div/select[2]")));
		year.selectByVisibleText("2024");
		
		WebElement btn=driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div[2]/div/button"));
		btn.click();
	}
	public static void ViewEditDelete(ChromeDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		WebElement view=driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div/div/div/div/div[2]/div/div/table/tbody/tr/td[3]/button"));
		view.click();
		Thread.sleep(1000);
		WebElement cross=driver.findElement(By.xpath("/html/body/div[3]/div/div/div[1]/button/span[1]"));
		cross.click();
		Thread.sleep(1000);
		
		WebElement edit=driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div/div/div/div/div[2]/div/div/table/tbody/tr/td[4]/button"));
		edit.click();
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].innerText = arguments[1];", driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[2]/div/div/div/p/div/div/div[2]/div/div[2]/div/div/div/div/div/div/span")), "This is my edited journal");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,40)", "");
		WebElement submit=driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/div[2]/div/div/div/p/div/div/div[3]/button"));
		submit.click();
		js.executeScript("window.scrollBy(0,-40)", "");
		Thread.sleep(1000);
		
		SearchJournal(driver);
		Thread.sleep(1000);
		WebElement delete=driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div/div/div/div/div[2]/div/div/table/tbody/tr/td[5]/button"));
		delete.click();
		Thread.sleep(1000);
	}
	public static void Logout(ChromeDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		WebElement logout=driver.findElement(By.id("logoutbutton"));
		logout.click();
	}

	public static void main(String[] args) throws InterruptedException{
		//INITIALISING CHROME DRIVER
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("http://localhost:3000/");
		
		Thread.sleep(2000);
		
		//FUNCTIONS
		GoToSignUp(driver);
		FillSignUp(driver);
		LoginIn(driver);
		JournalEntry(driver);
		SearchJournal(driver);
		ViewEditDelete(driver);
		Logout(driver);
		
		Thread.sleep(2000);
		driver.quit();
	}

}

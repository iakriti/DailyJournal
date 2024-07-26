package testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignUpTest {
	ChromeDriver driver=new ChromeDriver();
	//SignUp test
	
	@BeforeTest
	public void StartSite() throws InterruptedException {
		driver.manage().window().maximize();
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://localhost:3000/");
		WebElement login=driver.findElement(By.className("custom-btn"));
		login.click();
		
		Thread.sleep(2000);
		
		WebElement signup=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/form/div[3]/div[2]/a"));
		signup.click();
	}
	@BeforeMethod
	public void sleep() throws InterruptedException {
		Thread.sleep(2000);
		WebElement firstname=driver.findElement(By.id("firstName"));
		firstname.clear();
		
		WebElement lastname=driver.findElement(By.id("lastName"));
		lastname.clear();
		
		WebElement email=driver.findElement(By.id("email"));
		email.clear();
		
		WebElement password=driver.findElement(By.id("password"));
		password.clear();
		
		WebElement conpassword=driver.findElement(By.id("confirmPassword"));
		conpassword.clear();
	}
	
	@Test(priority=0,description="Empty field")
	public void EmptyField() throws InterruptedException {
		WebElement firstname=driver.findElement(By.id("firstName"));
//		firstname.sendKeys("");
		
		WebElement lastname=driver.findElement(By.id("lastName"));
//		lastname.sendKeys("");
		
		WebElement email=driver.findElement(By.id("email"));
		email.sendKeys("ra1@gmail.com");
		
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys("rimjhim8");
		
		WebElement conpassword=driver.findElement(By.id("confirmPassword"));
		conpassword.sendKeys("rimjhim8");
		
		WebElement signup=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/form/button/span[1]"));
		signup.click();
		Thread.sleep(1000);
		
		String expectedTitle="Fields cannot be empty";
		String actualTitle=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/div[2]")).getText().substring(0,22);
		
		Assert.assertEquals(expectedTitle,actualTitle);		
	}
	
	
	@Test(priority=1,description="Invalid Email")
	public void InvalidEmail() throws InterruptedException {
		WebElement firstname=driver.findElement(By.id("firstName"));
		firstname.sendKeys("Rimjhim");
		
		WebElement lastname=driver.findElement(By.id("lastName"));
		lastname.sendKeys("Singh");
		
		WebElement email=driver.findElement(By.id("email"));
		email.sendKeys("rimjh");
		
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys("rimjhim0");
		
		WebElement conpassword=driver.findElement(By.id("confirmPassword"));
		conpassword.sendKeys("rimjhim0");
		
		WebElement signup=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/form/button/span[1]"));
		signup.click();
		Thread.sleep(1000);
		
		String expectedTitle="Please enter a valid Email";
		String actualTitle=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/div[2]")).getText().substring(0,26);
		
		Assert.assertEquals(expectedTitle,actualTitle);		
	}
	@Test(priority=2,description="Atleast 8 character password")
	public void EightCharacter() throws InterruptedException {
		WebElement firstname=driver.findElement(By.id("firstName"));
		firstname.sendKeys("Rimjhim");
		
		WebElement lastname=driver.findElement(By.id("lastName"));
		lastname.sendKeys("Singh");
		
		WebElement email=driver.findElement(By.id("email"));
		email.sendKeys("rimjhim@gmail.com");
		
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys("rimjhim");
		
		WebElement conpassword=driver.findElement(By.id("confirmPassword"));
		conpassword.sendKeys("rimjhim");
		
		WebElement signup=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/form/button/span[1]"));
		signup.click();
		Thread.sleep(1000);
		
		String expectedTitle="Password should contain atleast 8 characters!";
		String actualTitle=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/div[2]")).getText().substring(0,45);
		
		Assert.assertEquals(expectedTitle,actualTitle);		
	}
	@Test(priority=3,description="Password Match")
	public void PasswordMatch() throws InterruptedException {
		WebElement firstname=driver.findElement(By.id("firstName"));
		firstname.sendKeys("Rimjhim");
		
		WebElement lastname=driver.findElement(By.id("lastName"));
		lastname.sendKeys("Singh");
		
		WebElement email=driver.findElement(By.id("email"));
		email.sendKeys("rimjhim@gmail.com");
		
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys("rimjhjhjhj8");
		
		WebElement conpassword=driver.findElement(By.id("confirmPassword"));
		conpassword.sendKeys("rimjhim8");
		
		WebElement signup=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/form/button/span[1]"));
		signup.click();
		Thread.sleep(1000);
		
		String expectedTitle="Password mismatch!!";
		String actualTitle=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/div[2]")).getText().substring(0,19);
		
		Assert.assertEquals(expectedTitle,actualTitle);		
	}
//	@Test(priority=4,description="Successful SignUp")
//	public void Success() throws InterruptedException {
//		WebElement firstname=driver.findElement(By.id("firstName"));
//		firstname.sendKeys("Rimjhim");
//		
//		WebElement lastname=driver.findElement(By.id("lastName"));
//		lastname.sendKeys("Singh");
//		
//		WebElement email=driver.findElement(By.id("email"));
//		email.sendKeys("srivas123479@gmail.com");
//		
//		WebElement password=driver.findElement(By.id("password"));
//		password.sendKeys("rimjhim8");
//		
//		WebElement conpassword=driver.findElement(By.id("confirmPassword"));
//		conpassword.sendKeys("rimjhim8");
//		
//		WebElement signup=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/form/button/span[1]"));
//		signup.click();
//		Thread.sleep(1000);
//		
//		String expectedNewUrl = "http://localhost:3000/login";
//	    String actualNewUrl = driver.getCurrentUrl();
//
//		Assert.assertEquals(expectedNewUrl,actualNewUrl);		
//	}

    @Test(priority=4,dataProvider = "signUpData")
    public void Success(String firstName, String lastName, String email, String password, String confirmPassword) throws InterruptedException {
        WebElement firstname=driver.findElement(By.id("firstName"));
        firstname.sendKeys(firstName);
        
        WebElement lastname=driver.findElement(By.id("lastName"));
        lastname.sendKeys(lastName);
        
        WebElement emailInput=driver.findElement(By.id("email"));
        emailInput.sendKeys(email);
        
        WebElement passwordInput=driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);
        
        WebElement confirmPasswordInput=driver.findElement(By.id("confirmPassword"));
        confirmPasswordInput.sendKeys(confirmPassword);
        
        WebElement signup=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/main/div/form/button/span[1]"));
        signup.click();
        Thread.sleep(3000);
        
        String expectedNewUrl = "http://localhost:3000/login";
        String actualNewUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualNewUrl, expectedNewUrl);
    }
    
	@DataProvider(name = "signUpData")
    public Object[][] signUpData() {
        return new Object[][] {
                {"Rimjhim", "Singh", "srivas1234@gmail.com", "rimjhim8", "rimjhim8"},
        };
    }
	

	@AfterTest
	public void Close() {
		driver.quit();
	}
	
}

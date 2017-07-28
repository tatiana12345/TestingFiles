package utilities;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SearchPageFactory {
	
	ExtentTest test;
	WebDriver wd;

	@FindBy(xpath="//span[text()='Hello. Sign in']")
	WebElement Login;

	@FindBy(xpath="//span[text()='Hi tatiana']")
	WebElement LoggedIn;
	
	@FindBy(id="ap_email")
	WebElement emailID;
	

	@FindBy(id="ap_password")
	WebElement loginPassword;
	
	@FindBy(id="signInSubmit")
	WebElement SubmitButton;
	

	
	public SearchPageFactory (WebDriver wd, ExtentTest test){
		this.wd = wd;
		this.test = test;
		PageFactory.initElements(wd, this);
	}
	public void clickLogin(){
		if (Login.isDisplayed()){
			Login.click();
			test.log(LogStatus.INFO, "User is logged out, Login button found");
//			System.out.println("User is not Logged in");
		}
		else{
			test.log(LogStatus.INFO, "User is logeerd in, login button not found");
//			System.out.println("Login element is not displayed");
		}
	}
	public void typeEmail(String email){
		emailID.sendKeys(email);
	}
	public void typePassword(String password){
		loginPassword.sendKeys(password);
	}
	public void clickSubmit(){
		SubmitButton.click();
	}
	
	}



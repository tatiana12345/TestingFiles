package utilities;

//import java.util.ArrayList;

//import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class utility {

	WebDriver wd;
	ExtentTest test;
	Actions action;

	public utility(WebDriver wd, ExtentTest test) {
		this.wd = wd;
		this.test = test;
		action = new Actions(wd);
	}

	public static WebElement element = null;

	public void clickLogin() {
		WebElement clickLoginButton = wd.findElement(By.xpath("//span[text()='Hello. Sign in']"));
		if (clickLoginButton.isDisplayed()) {
			clickLoginButton.click();
			test.log(LogStatus.INFO, "Clicked Sign in");
		} else {
			test.log(LogStatus.INFO, "Sign In is not displayed");
			WebElement accountLogout = wd.findElement(By.id("nav-link-accountList"));
			action.moveToElement(accountLogout).perform();
			WebElement signOut = wd.findElement(By.xpath("//span[contains(text(),'Sign Out')]"));
			action.moveToElement(signOut).click().perform();
			test.log(LogStatus.INFO, "User logged out, ready to log in with correct credentials");
		}

	}

	public void typeEmail(String email) {
		WebElement emailField = wd.findElement(By.id("ap_email"));
		emailField.sendKeys(email);
		test.log(LogStatus.INFO, "Email address is typed in " + "'" + email + "'");
	}

	public void typePassword(String password) {
		WebElement passField = wd.findElement(By.id("ap_password"));
		passField.sendKeys(password);
		test.log(LogStatus.INFO, "Pasword is typed in " + "'" + password + "'");
	}

	public void clickSubmit() {
		WebElement submitButton = wd.findElement(By.id("signInSubmit"));
		submitButton.click();
		test.log(LogStatus.INFO, "Submit button clicked");
	}

	public void verifyLoggedIn() {
		WebElement greeting = wd.findElement(By.xpath("//span[text()='Hi tatiana']"));
		Assert.assertNotNull(greeting);
		test.log(LogStatus.PASS, "User successfully logged in, Login TC passed");
	}

	public boolean isPresent() {
		WebElement greeting = null;
		try {
			greeting = wd.findElement(By.xpath("//span[text()='Hi tatiana']"));
			if (greeting != null) {
				return true;
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
		return false;
	}

	///// TC2

	/// Click Departments
	// Click Toys and Games
	// Print out all tabs on the top of Toys and Games page
	// Iterate through all elements on the top of Toys and Games page
	// Iterate through each item on the page

	public void clickDepartments() {
		WebElement Departments = wd.findElement(By.xpath("//a[@id='nav-link-shopall']//span[@class='nav-line-2']"));
		Departments.click();
		test.log(LogStatus.INFO, "Clicked Departments");
	}

	public void clickToysGames() {
		WebElement toysGames = wd.findElement(By.xpath("//div[@class='fsdDeptCol']/a[text()='Toys & Games']"));
		toysGames.click();
		test.log(LogStatus.INFO, "Clicked Toys & Games");
	}

	// Click each item in All "In Shop The Top"
	public void ClickPrintOutAllTitlesInToys() {
		List<WebElement> items = wd.findElements
				(By.xpath("//div[@class='bxw-pageheader__title__subtext a-color-secondary']/p/a"));

		List<WebElement> subItems = wd.findElements
				(By.xpath("//div[@class='a-row a-spacing-mini']//a/h2"));
		

		java.util.Iterator<WebElement> e = items.iterator();

		String SubPageSearchText = "Shop by category";
		
		int size = items.size();
		int SEsize = subItems.size();
		
		System.out.println(size + " elements on the page found");
		test.log(LogStatus.INFO, +size + " elements on the page found");

		while (e.hasNext()) {
			WebElement item = e.next();
			if (item.isDisplayed()) {
				String title = item.getText();
				System.out.println(title);
				test.log(LogStatus.INFO, title);
			}
		}

		for (int i = 1; i <= items.size(); i++) {
			items = wd.findElements(By.xpath("//div[@class='bxw-pageheader__title__subtext a-color-secondary']/p/a"));
			items.get(i - 1).click();

			String pageTitle = wd.getTitle();
			pageTitle = pageTitle.substring(12);
			System.out.println("\n" + pageTitle);
			
//			pageTitle = pageTitle.substring(12);
//			System.out.println("\n" + pageTitle);
			test.log(LogStatus.INFO, "Page title: " + pageTitle);

			//working, but too long
			// Search for matching text on the page
			// List<WebElement> list =
			// wd.findElements(By.xpath("//*[contains(text(),'" +
			// SubPageSearchText + "')]"));
			// Assert.assertTrue(list.size() > 0, "Text not found!");

			Assert.assertTrue(wd.getPageSource().contains(SubPageSearchText));

			// click items on the page
			//works, but fails in the end and breaks parent loop 	
			 for (int j = 0; j < subItems.size(); j++){
				 subItems = wd.findElements(By.xpath("//div[@class='a-row a-spacing-mini']//a/h2"));
				 
				 System.out.println("Items found on the page: " + SEsize);
				 String title = subItems.get(j).getText();
				 subItems.get(j).click();
				 
				 String toyIdTitle = wd.findElement(By.id("productTitle")).getText();
				 
				 Assert.assertEquals(title, toyIdTitle);
				 System.out.println("\nExpected toy name: " + title + "\nActual name: " + toyIdTitle);
				 test.log(LogStatus.PASS, "\nExpected toy name: " + title + "\nActual name: " + toyIdTitle);
				 
				 wd.navigate().back();
			 }

			wd.navigate().back();
		}
		
		System.out.println("\nSearch text: " + SubPageSearchText + "\n");
		test.log(LogStatus.INFO, "Search text: " + SubPageSearchText);
	}
	
	/////////////TC 3
	//////BOOKS SECTION
	/*
	 * 1. Launch WB;
       2. In Departments select “Books & Audible”; 
       3. In “Books & Audible” select “Children’s Books”;
       4.
       5.
	 */
	
	public void hoverDepartments() throws InterruptedException{
		WebElement Departments =
				 wd.findElement(By.xpath("//a[@id='nav-link-shopall']//span[@class='nav-line-2']"));
				 action.moveToElement(Departments).perform();
				 String tabTitle = Departments.getText();
		test.log(LogStatus.INFO, "Hovered over " + tabTitle);
		Thread.sleep(2000);
		
	}
	
	public void selectBooksAudible() throws InterruptedException{
//		WebElement BooksAudible = wd.findElement(By.xpath("//span[text()='Books & Audible']"));
		WebElement BooksAudible = wd.findElement(By.xpath("//span[text()='Electronics, Computers & Office']"));
		String tabTitle = BooksAudible.getText();	
		System.out.println("Hovered " + tabTitle);
		action.moveToElement(BooksAudible).perform();
		test.log(LogStatus.INFO, "Hovered over " +tabTitle);
		
	}
	
	public void chromeBooksAudible() throws InterruptedException{
		WebElement BooksAudible = wd.findElement(By.xpath("//span[text()='Books & Audible']"));
//		WebElement BooksAudible = wd.findElement(By.xpath("//span[text()='Electronics, Computers & Office']"));
		String tabTitle = BooksAudible.getText();	
		System.out.println("Hovered " + tabTitle);
		action.moveToElement(BooksAudible).perform();
		test.log(LogStatus.INFO, "Hovered over " +tabTitle);
		
	}
	
	public void ChildrenBooks() throws InterruptedException{
//		WebElement ChildrenBooks = wd.findElement(By.xpath("//span[text()=\"Children's Books\"]"));
		WebElement ChildrenBooks = wd.findElement(By.xpath("//span[text()='Home Audio & Theater']"));
		String tabTitle = ChildrenBooks.getText();
		System.out.println("Clicked " + tabTitle);
		action.moveToElement(ChildrenBooks).click().perform();
		test.log(LogStatus.INFO, "Clicked " + tabTitle);
		Thread.sleep(1000);
	}
	
	public void chromeChildrenBooks() throws InterruptedException{
		WebElement ChildrenBooks = wd.findElement(By.xpath("//span[text()=\"Children's Books\"]"));
//		WebElement ChildrenBooks = wd.findElement(By.xpath("//span[text()='Home Audio & Theater']"));
		String tabTitle = ChildrenBooks.getText();
		System.out.println("Clicked " + tabTitle);
		action.moveToElement(ChildrenBooks).click().perform();
		test.log(LogStatus.INFO, "Clicked " + tabTitle);
		Thread.sleep(1000);
	}
		
	public void ClickFrom9to12() {
		WebElement from9to12 = wd.findElement(By.xpath("//img[@alt='Ages 9 to 12']"));
		from9to12.click();
		test.log(LogStatus.INFO, "Clicked Category 'From 9 to 12'");
	}
	public void loopThroughAllBooks(){

		List<WebElement> allBooks = 
				wd.findElements(By.xpath("//div[@id='mainResults']//li[starts-with(@id,'result')]//h2"));
		
		int size = allBooks.size();
		System.out.println(size + " Items fount on the page");
		test.log(LogStatus.INFO, size + " Items fount on the page");
		
		for (int i=0; i<size; i++){
			allBooks = wd.findElements(By.xpath("//div[@id='mainResults']//li[starts-with(@id,'result')]//h2"));
			String title = allBooks.get(i).getText();
			allBooks.get(i).click();
			
			String bookTitle = wd.findElement(By.id("productTitle")).getText();
			System.out.println("The title on main page is: " + title + "\nBook id title is: " + bookTitle);
			
			Assert.assertEquals(title, bookTitle);
			
			wd.navigate().back();	
		}
		
		test.log(LogStatus.PASS, "All book titles match expected - TC 3 Passed");
	}
}

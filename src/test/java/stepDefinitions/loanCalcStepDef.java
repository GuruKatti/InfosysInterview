package stepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import commonUtils.commonUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoanCalculatorPage;
import static commonUtils.commonUtils.TakeScreenshot;

public class loanCalcStepDef{
	WebDriver driver;
	static String borrowingEstimate;
	ExtentReports report = new ExtentReports(userDir+"/html-reports/Report"+System.currentTimeMillis()+".html",false);
	ExtentTest logger;
	static String userDir=System.getProperty("user.dir");
	commonUtils utils;
	LoanCalculatorPage loanPage;
	static String expectedEstimate;

	@Before
	public void initializeDriver(){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Given("^user navigates to the url \"([^\"]*)\"$")
	public void navigateToURL(String url) {
		report.addSystemInfo("Author: ","Gurabasappa Katti");
		logger = report.startTest("Loan_Calculator");
		driver.get(url);
	}
	@When("^user enters the below details$")
	public void enterAllTheDetails(List<Map<String,String>> table) {
		utils = new commonUtils();
		loanPage = new LoanCalculatorPage(driver);
		for(Map<String,String> row: table) {
			String applicationType = row.get(ColumnEnum.APPLICATIONTYPE.toString());
			String numberOfDependants = row.get(ColumnEnum.NUMBEROFDEPENDANTS.toString());
			String PropertyYouWouldLikeToBuy = row.get(ColumnEnum.PROPERTYYOUWOULDLIKETOBUY.toString());
			String AnnualIncome = row.get(ColumnEnum.ANNUALINCOME.toString());
			String OtherIncome = row.get(ColumnEnum.OTHERINCOME.toString());
			String LivingExpenses = row.get(ColumnEnum.LIVINGEXPENSES.toString());
			String HomeLoan = row.get(ColumnEnum.HOMELOAN.toString());
			String OtherLoan = row.get(ColumnEnum.OTHERLOAN.toString());
			String Commitments = row.get(ColumnEnum.COMMITMENTS.toString());
			String CreditCardLimits = row.get(ColumnEnum.CREDITCARDLIMITS.toString());
			expectedEstimate= row.get(ColumnEnum.EXPECTEDESTIMATE.toString());

			loanPage.selectApplicationType();
			loanPage.selectNumberOfDependants(numberOfDependants);
			loanPage.selectpropertyType(PropertyYouWouldLikeToBuy);
			loanPage.enterAnnualIncome(AnnualIncome);
			loanPage.enterotherIncome(OtherIncome);
			loanPage.enterlivingExpenses(LivingExpenses);
			loanPage.enterHomeLoans(HomeLoan);
			loanPage.enterOtherLoan(OtherLoan);
			loanPage.enterCommitments(Commitments);
			loanPage.enterCreditCardLimits(CreditCardLimits);
		}
	}
	@Then("^user clicks on work out how much i could borrow$")
	public void verifyTheBorrowingEstimate()  {
		loanPage.clickCalculateBtn();
	}

	@Then("^verify the borrowing estimate$")
	public void verifyBorrowingEstimate() throws InterruptedException, IOException {
		Thread.sleep(2000);
		borrowingEstimate = loanPage.getBorrowingEstimate();
		borrowingEstimate = borrowingEstimate.substring(1);
		if (borrowingEstimate.equals(expectedEstimate)) {
			logger.log(LogStatus.PASS, "Calculate Borrowing Estimate", "Actual Borrowing Estimate with entered Details : "+borrowingEstimate+" Expected Estimate : "+expectedEstimate);
		} else {
			logger.log(LogStatus.FAIL,logger.getTest().getName(),"Actual Borrowing Estimate with entered Details : "+borrowingEstimate+" Expected Estimate : "+expectedEstimate);
			String screenshotPath = TakeScreenshot(logger.getTest().getName()+System.currentTimeMillis(), driver, userDir);
			logger.log(LogStatus.FAIL,logger.addScreenCapture(screenshotPath));
			Assert.assertEquals(expectedEstimate, borrowingEstimate);
		}
		report.endTest(logger);
	}
	@After
	public void closeConnection(){
		report.flush();
		driver.quit();
	}

}
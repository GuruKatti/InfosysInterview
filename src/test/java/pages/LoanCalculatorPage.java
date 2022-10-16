package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LoanCalculatorPage {
    WebDriver driver;
    @FindBy(xpath = "//label[contains(text(),'Single')]")
    private WebElement applicationType;

    @FindBy(xpath = "//select[@title='Number of dependants']")
    private WebElement dependants;

    @FindBy(xpath = "//label[contains(text(),'Home to live in')]")
    private WebElement propertyType;

    @FindBy(xpath = "//label[text()='Your annual income (before tax)']/../div/input")
    private WebElement annualIncome;

    @FindBy(xpath = "//*[text()='Your annual other income (optional)']/../div/input")
    private WebElement otherIncome;

    @FindBy(id = "expenses")
    private WebElement livingExpenses;

    @FindBy(id = "homeloans")
    private WebElement homeLoans;

    @FindBy(id = "otherloans")
    private WebElement otherLoan;

    @FindBy(xpath = "//*[text()='Other monthly commitments']/../div/input")
    private WebElement commitments;

    @FindBy(id = "credit")
    private WebElement creditCardLimits;

    @FindBy(id = "borrowResultTextAmount")
    private WebElement borrowingEstimate;

    @FindBy(id = "btnBorrowCalculater")
    private WebElement calculateBtn;

    public LoanCalculatorPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectApplicationType(){
        applicationType.click();
    }

    public void selectNumberOfDependants(String dependantsValue){
        Select dependantsSelect = new Select(dependants);
        dependantsSelect.selectByVisibleText(dependantsValue);
    }

    public void selectpropertyType(String propertyTypeValue){
        propertyType.sendKeys(propertyTypeValue);
    }

    public void enterAnnualIncome(String annualIncomeValue){
        annualIncome.clear();
        annualIncome.sendKeys(annualIncomeValue);
    }

    public void enterotherIncome(String otherIncomeValue){
        otherIncome.clear();
        otherIncome.sendKeys(otherIncomeValue);
    }

    public void enterlivingExpenses(String livingExpensesValue){
        livingExpenses.clear();
        livingExpenses.sendKeys(livingExpensesValue);
    }

    public void enterHomeLoans(String homeLoansValue){
        homeLoans.clear();
        homeLoans.sendKeys(homeLoansValue);
    }

    public void enterOtherLoan(String otherLoanValue){
        otherLoan.clear();
        otherLoan.sendKeys(otherLoanValue);
    }

    public void enterCommitments(String commitmentsValue){
        commitments.clear();
        commitments.sendKeys(commitmentsValue);
    }

    public void enterCreditCardLimits(String creditCardLimitsValue){
        creditCardLimits.clear();
        creditCardLimits.sendKeys(creditCardLimitsValue);
    }
    public String getBorrowingEstimate(){
        Actions act=new Actions(driver);
        act.scrollToElement(borrowingEstimate).build().perform();
        return borrowingEstimate.getText();
    }

    public void clickCalculateBtn(){
        calculateBtn.click();
    }

}

package stepDefinitions;

public enum ColumnEnum {
    APPLICATIONTYPE("ApplicationType"),
    NUMBEROFDEPENDANTS("NumberOfDependants"),
    PROPERTYYOUWOULDLIKETOBUY("PropertyYouWouldLikeToBuy"),
    ANNUALINCOME("AnnualIncome"),
    OTHERINCOME("OtherIncome"),
    LIVINGEXPENSES("LivingExpenses"),
    HOMELOAN("HomeLoan"),
    OTHERLOAN("OtherLoan"),
    COMMITMENTS("Commitments"),
    CREDITCARDLIMITS("CreditCardLimits"),
    EXPECTEDESTIMATE("BorrowingEstimate");

    private  final String columnName;
    ColumnEnum(String columnName) {this.columnName=columnName;}

    @Override
    public String toString(){
        return String.valueOf(columnName);
    }
}

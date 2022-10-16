Feature: ANZ borrowing calculator

  Scenario Outline: ANZ borrowing calculator for the given details

    Given user navigates to the url "https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/"
    When user enters the below details
      | ApplicationType   | NumberOfDependants   | PropertyYouWouldLikeToBuy   | AnnualIncome   | OtherIncome   | LivingExpenses   | HomeLoan   | OtherLoan   | Commitments   | CreditCardLimits   | BorrowingEstimate   |
      | <ApplicationType> | <NumberOfDependants> | <PropertyYouWouldLikeToBuy> | <AnnualIncome> | <OtherIncome> | <LivingExpenses> | <HomeLoan> | <OtherLoan> | <Commitments> | <CreditCardLimits> | <BorrowingEstimate> |
    Then user clicks on work out how much i could borrow
    Then verify the borrowing estimate
    Examples:
      | ApplicationType | NumberOfDependants | PropertyYouWouldLikeToBuy | AnnualIncome | OtherIncome | LivingExpenses | HomeLoan | OtherLoan | Commitments | CreditCardLimits | BorrowingEstimate |
      | Single          | 0                  | Home to live in           | 80000        | 10000       | 500            | 0        | 100       | 0           | 10000            | 419,000           |
      | Single          | 0                  | Home to live in           | 80000        | 10000       | 500            | 0        | 100       | 0           | 10000            | 479,000           |

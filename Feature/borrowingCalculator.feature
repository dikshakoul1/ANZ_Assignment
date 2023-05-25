#Borrowing_Calculator

Feature: To test functionality of borrowing calculator

@borrow_estimate
  Scenario: Verify if user is able calculate the amount by passing his details 
    Given User is on Home loan borrowing power calculator page
    When  User select 'Application_Type' as 'Single'
    And   User select 'Number_of_Dependent' as '0' from dropdown list
    And   User select 'Property_like_to_buy' as 'Home to live in'
    And   User enter 'Annual_Income' as '$80000'
    And   User enter 'Other_Income' as '$10000'
    And   User enter 'Living_Expenses' as '$500'
    And   User enter 'Home_loan_repayment' as '$0'
    And   User enter 'Other_loan_repayment' as '$100'
    And   User enter 'Other_Commitments' as '$0'
    And   User enter 'Credit_card_limit' as '$10000'
    And   User click on 'Work_out_how_much_I_could_borrow' button
    Then  User verify 'Borrowing_estimate' of '$407,000'

@start_over
  Scenario: Verify if user is able to clear form using 'start over' button  
    Given User is on Home loan borrowing power calculator page
    When  User select 'Application_Type' as 'Single'
    And   User select 'Number_of_Dependent' as '0' from dropdown list
    And   User select 'Property_like_to_buy' as 'Home to live in'
    And   User enter 'Annual_Income' as '$80000'
    And   User enter 'Other_Income' as '$10000'
    And   User enter 'Living_Expenses' as '$500'
    And   User enter 'Home_loan_repayment' as '$0'
    And   User enter 'Other_loan_repayment' as '$100'
    And   User enter 'Other_Commitments' as '$0'
    And   User enter 'Credit_card_limit' as '$10000'
    And   User click on 'Work_out_how_much_I_could_borrow' button
    Then  User verify 'Borrowing_estimate' of '$407,000'
    And   User click on 'Start_over' button
    And   User verify form is clear with all values as '$0'
     
@Error_message
  Scenario: Verify if user is able to get error message when only living expenses are entered  
    Given User is on Home loan borrowing power calculator page
    When  User enter 'Living_Expenses' as '$1'
    And   User click on 'Work_out_how_much_I_could_borrow' button
    Then  User verify error message 'Based on the details you\'ve entered, we\'re unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 035 500.'
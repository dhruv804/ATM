ATM Project: Phase 1
Group 0315

This file will describe the functionality of our program. Please run the code on IntelliJ and follow the following steps
to fix the test files:

Project Structure > Libraries > Import Project Library > Choose Maven > Download the following Maven Repository:

"org.junit.jupiter:junit-jupiter-api:5.0.0-M4"


Throughout the program, Refresh buttons make everything up-to-date.
Before running for the first time, please delete the phase1/final.ser, and set the desired starting date in Main.java.
After doing so, run Main.java

Login Page:
    - The first tab includes fields for Username and Password to login. Managers and users can both login using this.
        - The Manager's login info is: username: 'admin', pass: 'pass'
        - For ease of testing, we have included a user from the beginning: username: 'vshah', pass: 'csc207'
    - The second tab allows new users to apply using their name.
    - The third tab allows the manager to shut down the ATM for the day. This will increment the day and calculate
        interest if necessary. After hitting the Close button, please wait until the entire java process ends, before
        running once again the 'next' day.

User Page:
    - The first tab, 'Account Details', allows users to view net total and specific amounts in each account.
    - The second tab, 'Withdraw', allows users to withdraw money from the ATM. This will only be successful if the
        user's account has enough balance and the Machine can dispense that amount.
    - The third tab, 'Deposit', allows users to deposit money into their accounts.
    - The fourth tab, 'Transfer', allows transfers between users, accounts and outsiders.
        - The account to user transfer moves money into a primary chequing.
    - The fifth tab, 'Login Management', allows changing of password.
    - The last tab, 'Request Account', allows users to request an additional account to the Manager

Bank Manager Page:
    - The first tab, 'Machine Status', will show the amount of bills in each denomination, and allow refilling.
    - The second tab, 'User Creation', allows the manager to see User applications and allows them to set
        passwords.
    - The last tab, 'Account Creation', allows the manager to see the pending Account requests, and lets the manager
        accept or reject the request.
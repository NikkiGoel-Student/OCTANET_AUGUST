import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ATM class to handle ATM operations
class ATM {
    private static final String ENTER_PIN_MSG = "Enter your PIN: ";
    private static final String INVALID_PIN_MSG = "Invalid PIN. Try again.";
    private static final String ACCOUNT_BALANCE_MSG = "Account balance is: ";
    private static final String ENTER_AMOUNT_MSG = "Enter the amount: ";
    private static final String INSUFFICIENT_BALANCE_MSG = "Insufficient balance.";
    private static final String SUCCESSFUL_WITHDRAW_MSG = "Money withdrawn successfully.";
    private static final String SUCCESSFUL_DEPOSIT_MSG = "Amount deposited successfully.";
    private static final String CHANGE_PIN_MSG = "Enter your current PIN: ";
    private static final String NEW_PIN_MSG = "Enter new PIN: ";
    private static final String SUCCESSFUL_PIN_CHANGE_MSG = "PIN changed successfully.";
    private static final String INCORRECT_CURRENT_PIN_MSG = "Incorrect current PIN.";
    private static final String TRANSACTION_HISTORY_MSG = "Transaction History:";
    private static final String INVALID_CHOICE_MSG = "Please enter a valid choice!";
    private static final String MENU_MSG = 
        "1. Check a/c balance\n" +
        "2. Deposit money\n" +
        "3. Withdraw money\n" +
        "4. Change PIN\n" +
        "5. View transaction history\n" +
        "6. Exit\n" +
        "Enter your choice: ";

    private float balance;
    private int pin;
    private final List<String> transactionHistory;
    private final Scanner scanner;

    // Constructor to initialize ATM with default balance and PIN
    public ATM(float initialBalance, int initialPin) {
        this.balance = initialBalance;
        this.pin = initialPin;
        this.transactionHistory = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Method to handle PIN verification
    public void verifyPin() {
        System.out.print(ENTER_PIN_MSG);
        int enteredPin = scanner.nextInt();
        if (pin == enteredPin) {
            displayMenu();
        } else {
            System.out.println(INVALID_PIN_MSG);
            verifyPin();
        }
    }

    // Method to display the menu and handle user choice
    public void displayMenu() {
        System.out.print(MENU_MSG);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1: checkBalance();
            case 2: depositMoney();
            case 3: withdrawMoney();
            case 4: changePin();
            case 5: viewTransactionHistory();
            case 6: System.exit(0);
            default: 
                System.out.println(INVALID_CHOICE_MSG);
                displayMenu();
        }
    }

    // Method to check account balance
    private void checkBalance() {
        System.out.println(ACCOUNT_BALANCE_MSG + balance);
        transactionHistory.add("Checked balance: " + balance);
        displayMenu();
    }

    // Method to withdraw money from the account
    private void withdrawMoney() {
        System.out.print(ENTER_AMOUNT_MSG);
        int amount = scanner.nextInt();
        if (amount > balance) {
            System.out.println(INSUFFICIENT_BALANCE_MSG);
            transactionHistory.add("Failed withdrawal attempt: " + amount);
        } else {
            balance -= amount;
            System.out.println(SUCCESSFUL_WITHDRAW_MSG);
            transactionHistory.add("Withdrawn: " + amount + ", Remaining balance: " + balance);
        }
        displayMenu();
    }

    // Method to deposit money into the account
    private void depositMoney() {
        System.out.print(ENTER_AMOUNT_MSG);
        int amount = scanner.nextInt();
        balance += amount;
        System.out.println(SUCCESSFUL_DEPOSIT_MSG);
        transactionHistory.add("Deposited: " + amount + ", New balance: " + balance);
        displayMenu();
    }

    // Method to change the account PIN
    private void changePin() {
        System.out.print(CHANGE_PIN_MSG);
        int currentPin = scanner.nextInt();
        if (currentPin == pin) {
            System.out.print(NEW_PIN_MSG);
            int newPin = scanner.nextInt();
            pin = newPin;
            System.out.println(SUCCESSFUL_PIN_CHANGE_MSG);
            transactionHistory.add(SUCCESSFUL_PIN_CHANGE_MSG);
        } else {
            System.out.println(INCORRECT_CURRENT_PIN_MSG);
            transactionHistory.add("Failed PIN change attempt.");
        }
        displayMenu();
    }

    // Method to view the transaction history
    private void viewTransactionHistory() {
        System.out.println(TRANSACTION_HISTORY_MSG);
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
        displayMenu();
    }
}

// Main class to execute the ATM program
public class Task_1_ATM {
    public static void main(String[] args) {
        // Initialize ATM with initial balance and PIN
        ATM obj = new ATM(0, 1722);
        obj.verifyPin();
    }
}

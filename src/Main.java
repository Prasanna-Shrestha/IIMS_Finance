import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    private static final AccountCreation acc = new AccountCreation();

    public static void main(String[] args) {
        options();
    }

    private static void options() {
        System.out.println("\nMenu:");
        System.out.println("1. Create Account");
        System.out.println("2. Display Account by Number");
        System.out.println("3. Login As Customer");
        try{
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    CreateAccount();
                    wantToTerminate();
                    break;
                case 2:
                    PrintUserDetails();
                    wantToTerminate();
                    break;
                case 3:
                    customer();
                    wantToTerminate();
                    break;
                default:
                    System.out.println("Invalid option");
                    wantToTerminate();
            }
        }
        catch (InputMismatchException err) {
            System.out.println("Input Mismatch");
            scanner.next();
            wantToTerminate();
        }

    }
    private static void customerChoices(){
        System.out.println("\nMenu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Interest Calculator");
        System.out.println("4. Balance Inquiry");
        try{
            int option = scanner.nextInt();
            switch (option){
                case 1:
                    performWithdrawal();
                    wantToTerminate();
                    break;
                case 2:
                    performDeposit();
                    wantToTerminate();
                    break;
                case 3:
                    calculateInterest();
                    wantToTerminate();
                    break;
                case 4:
                    balanceInquiry();
                    wantToTerminate();
                    break;
                default:
                    System.out.println("Invalid option");
                    wantToTerminate();
            }
        } catch (InputMismatchException e){
            System.out.println("Input Mismatch");
            scanner.next();
            wantToTerminate();
        }
    }

    private static void CreateAccount() {
        try{
            acc.userCreation();
        } catch (Exception e){
            System.out.println("Error encountered\nError message: " + e.getMessage());
        }
    }
    private static void PrintUserDetails() {
        try {
            System.out.println("Enter account number: ");
            int accountNumber = scanner.nextInt();
            System.out.println("Enter PIN: ");
            int pin = scanner.nextInt();
            try {
                acc.printUserDetails(accountNumber, pin);
            } catch (Exception e){
                System.out.println("Error encountered\nError message: " + e.getMessage());
            }
        } catch (InputMismatchException err) {
            System.out.println("Input Mismatch");
            scanner.next();
            wantToTerminate();
        }
    }
    private static void customer() {
        System.out.println("\nCustomer Login:");
        try {
            System.out.println("Account Number: ");
            int accountNumber = scanner.nextInt();
            System.out.println("PIN: ");
            int pin = scanner.nextInt();
            try {
                if (acc.validateUser(accountNumber, pin))
                    customerChoices();
                else{
                    System.out.println("Invalid Credentials");
                    options();
                }
            } catch (Exception e){
                System.out.println("Error encountered\nError message: " + e.getMessage());
            }
        } catch (InputMismatchException err) {
            System.out.println("Input Mismatch");
            scanner.next();
            wantToTerminate();
        }
    }

    private static void performWithdrawal() {
        try{
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();
            System.out.print("Enter PIN: ");
            int pin = scanner.nextInt();
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            try {
                acc.withdraw(accountNumber, pin, amount);
            } catch (Exception e){
                System.out.println("Error encountered\nError message: " + e.getMessage());
            }
        } catch (InputMismatchException err){
            System.out.println("Input Mismatch");
            scanner.next();
            wantToTerminate();
        }
    }
    private static void performDeposit() {
        try {
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();
            System.out.print("Enter PIN: ");
            int pin = scanner.nextInt();
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            try {
                acc.deposit(accountNumber, pin, amount);
            } catch (Exception e){
                System.out.println("Error encountered\nError message: " + e.getMessage());
            }
        } catch (InputMismatchException err){
            System.out.println("Input Mismatch");
            scanner.next();
            wantToTerminate();
        }
    }
    private static void calculateInterest() {
        try {
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();
            System.out.print("Enter PIN: ");
            int pin = scanner.nextInt();
            System.out.println("Interest Rate(below 0.5): ");
            double interest_rate = scanner.nextDouble();
            try {
                if (interest_rate < 0.5)
                    acc.calculateInterest(accountNumber, pin, interest_rate);
                else {
                    System.out.println("Interest Rate can not be greater than 50%");
                    calculateInterest();
                }
            } catch (Exception e){
                System.out.println("Error encountered\nError message: " + e.getMessage());
            }
        } catch (InputMismatchException err){
            System.out.println("Input Mismatch");
            scanner.next();
            wantToTerminate();
        }
    }
    private static void balanceInquiry() {
        try {
            System.out.println("Enter account number: ");
            int accountNumber = scanner.nextInt();
            System.out.println("Enter PIN: ");
            int pin = scanner.nextInt();
            try {
                acc.balanceInquiry(accountNumber, pin);
            } catch (Exception e){
                System.out.println("Error encountered\nError message: " + e.getMessage());
            }
        } catch (InputMismatchException er){
            System.out.println("Input Mismatch");
            scanner.next();
            wantToTerminate();
        }
    }

    private static void wantToTerminate() {
        try {
            System.out.println("Want to continue? (y/n)");
            String answer = scanner.next();
            if (answer.equalsIgnoreCase("n")) {
                System.out.println("Will be waiting for you!!!\nBe back soon!!!");
                System.exit(0);
            }
        } catch (InputMismatchException err){
            System.out.println("Input Mismatch");
            scanner.next();
            wantToTerminate();
        }
        options();
    }
}
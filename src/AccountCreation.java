import java.util.*;

public class AccountCreation {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static Map<Integer, UserDetails> userDetailsMap = new HashMap<>();

    public void userCreation(){
        UserDetails user = createUserDetails();
        userDetailsMap.put(user.getAccountNumber(), user);

        System.out.println("\n\nAccount successfully created");
        System.out.println("Account Number: " + user.getAccountNumber());
        System.out.println("PIN: " + user.getPin());
        System.out.println("Name: " + user.getName());
        System.out.println("Account Type: " + user.getAccountType());
        System.out.println("Balance(Rs.): " + user.getBalance());
    }
    private UserDetails createUserDetails() {
        try {
            String name = Name();
            double balance = Balance();
            String accountType = AccountType();
            int accountNumber = generateAccountNumber();
            int pin = generatePin();
            return new UserDetails(name, accountType, balance, accountNumber, pin);
        } catch (InputMismatchException err){
            System.out.println("Input Mismatch");
            scanner.nextLine();
            return null;
        }
    }
    public boolean validateUser(int accountNumber, int pin) {
        UserDetails user = userDetailsMap.get(accountNumber);
        return user != null && user.getPin() == pin;
    }

    public void deposit(int accountNumber, int pin, double amount) {
        UserDetails user = userDetailsMap.get(accountNumber);
        if (user != null && user.getPin() == pin) {
            if (amount > 0) {
                double newBalance = user.getBalance() + amount;
                updateUserBalance(user, newBalance);
                System.out.println("Deposit successful.");
            } else {
                System.out.println("Invalid deposit amount.");
            }
        } else {
            System.out.println("Account Information Mismatch.");
        }
    }
    public void withdraw(int accountNumber, int pin, double amount) {
        UserDetails user = userDetailsMap.get(accountNumber);
        if (user != null && user.getPin() == pin) {
            if (amount > 0 && amount <= user.getBalance()) {
                double newBalance = user.getBalance() - amount;
                updateUserBalance(user, newBalance);
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Invalid or insufficient funds for withdrawal.");
            }
        } else {
            System.out.println("Account Information Mismatch.");
        }
    }
    public void calculateInterest(int accountNumber, int pin, double interest_rate) {
        UserDetails user = userDetailsMap.get(accountNumber);
        if (user != null && user.getPin() == pin) {
            if (user.getAccountType().equalsIgnoreCase("Saving")) {
                double monthlyInterest = (user.getBalance() * interest_rate) / 12;
                System.out.println("Monthly Interest: Rs. " + monthlyInterest);
                System.out.println("Balance after interest: Rs. " + (user.getBalance() + monthlyInterest));
            } else {
                System.out.println("Interest calculation is only applicable to savings accounts.");
            }
        } else {
            System.out.println("Account Information Mismatch.");
        }
    }
    public void balanceInquiry(int accountNumber, int pin) {
        UserDetails user = userDetailsMap.get(accountNumber);
        if (user != null && user.getPin() == pin) {
            System.out.println("Balance(Rs.): " + user.getBalance());
        } else {
            System.out.println("Account Information Mismatch.");
        }
    }

    public void printUserDetails(int accountNumber, int pin) {
        UserDetails user = userDetailsMap.get(accountNumber);
        if (user != null && user.getPin() == pin) {
            System.out.println("\nAccount Details:");
            System.out.println("Account Number: " + user.getAccountNumber());
            System.out.println("PIN Number: " + user.getPin());
            System.out.println("Name: " + user.getName());
            System.out.println("Account Type: " + user.getAccountType());
            System.out.println("Balance(Rs.): " + user.getBalance());
        } else {
            System.out.println("Account Information Mismatch.");
        }
    }

    private String Name() {
        System.out.println("Enter Name: ");
        String name = scanner.nextLine();
        if (validateName(name)){
            return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        } else {
            System.out.println("Invalid Name!");
            return Name();
        }
    }

    private double Balance() {
        System.out.println("Enter Balance(Rs): ");
        double balance = scanner.nextDouble();
        scanner.nextLine();
        if (validateBalance(balance)){
            return balance;
        } else {
            System.out.println("Invalid Balance!");
            return Balance();
        }
    }
    private String AccountType() {
        System.out.println("Enter Account Type (Options: Saving/Debit/Credit): ");
        String accountType = scanner.next();
        scanner.nextLine();
        if (validateAccountType(accountType)){
            return accountType.substring(0, 1).toUpperCase() + accountType.substring(1).toLowerCase();
        } else {
            System.out.println("Invalid Account Type");
            return AccountType();
        }
    }
    private int generatePin() {
        int pin = 100 + random.nextInt(900);
        if (validatePin(pin)) {
            return pin;
        } else {
            return generatePin();
        }
    }

    private int generateAccountNumber() {
        int accountNumber = 10000 + random.nextInt(90000);
        if (validateAccountNumber(accountNumber)) {
            return accountNumber;
        } else {
            return generateAccountNumber();
        }
    }
    private boolean validateName(String name) {
        if (!name.isEmpty())
            return true;
        return false;
    }

    private boolean validateBalance(double balance) {
        if (balance >= 0)
            return true;
        return false;
    }
    private boolean validateAccountNumber(int accountNumber) {
        if (accountNumber >= 10000)
            return true;
        return false;
    }
    private boolean validatePin(int pin) {
        if (pin >= 100)
            return true;
        return false;
    }
    private boolean validateAccountType(String accountType){
        return accountType.equalsIgnoreCase("Saving") || accountType.equalsIgnoreCase("Debit") || accountType.equalsIgnoreCase("Credit");
    }

    private void updateUserBalance(UserDetails user, double newBalance) {
        userDetailsMap.put(user.getAccountNumber(), new UserDetails(
                user.getName(),
                user.getAccountType(),
                newBalance,
                user.getAccountNumber(),
                user.getPin()
        ));
    }
}

public class UserDetails {
    private final String name, accountType;
    private final double balance;
    private final int accountNumber, pin;

    public UserDetails(String name, String accountType, double balance, int accountNumber, int pin) {
        this.name = name;
        this.accountType = accountType;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.pin = pin;
    }

    public String getName() {
        return name;
    }
    public String getAccountType() {
        return accountType;
    }
    public double getBalance() {
        return balance;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    public int getPin() {
        return pin;
    }

}

package ei.project.pattern.command;

// Receiver
// Contains the actual business logic for deposit, withdraw, transfer
public class BankAccount {
    private String accountNumber;
    private String accountName; // friendly name
    private double balance;

    public BankAccount(String accountNumber, String accountName, double balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
    }

    public void Deposit(double amount) {
        balance += amount;
        BankLogger.info("Deposited " + amount + " to " + accountName + " (" + accountNumber + ")");
    }

    public void Withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            BankLogger.info("Withdrew " + amount + " from " + accountName + " (" + accountNumber + ")");
        } else {
            BankLogger.error("Insufficient funds for withdrawal in " + accountName + " (" + accountNumber + ")");
        }
    }

    public void Transfer(BankAccount toAccount, double amount) {
        if (balance >= amount) {
            this.Withdraw(amount);
            toAccount.Deposit(amount);
            BankLogger.info("Transferred " + amount + " from " + accountName + " (" + accountNumber + ")" +
                    " to " + toAccount.getAccountName() + " (" + toAccount.getAccountNumber() + ")");
        } else {
            BankLogger.error("Insufficient funds for transfer from " + accountName + " (" + accountNumber + ")");
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }
}

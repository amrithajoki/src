package ei.project.pattern.command;

// ========================
// Command Interface
// ========================
public interface Command {
    void Execute();

    void Undo();
}

// ========================
// DepositCommand (Concrete Command)
// ========================
class DepositCommand implements Command {
    private final BankAccount Account;
    private final double Amount;

    public DepositCommand(BankAccount account, double amount) {
        this.Account = account;
        this.Amount = amount;
    }

    @Override
    public void Execute() {
        Account.Deposit(Amount);
    }

    @Override
    public void Undo() {
        Account.Withdraw(Amount);
    }
}

// ========================
// WithdrawCommand (Concrete Command)
// ========================
class WithdrawCommand implements Command {
    private final BankAccount Account;
    private final double Amount;

    public WithdrawCommand(BankAccount account, double amount) {
        this.Account = account;
        this.Amount = amount;
    }

    @Override
    public void Execute() {
        Account.Withdraw(Amount);
    }

    @Override
    public void Undo() {
        Account.Deposit(Amount);
    }
}

// ========================
// TransferCommand (Concrete Command)
// ========================
class TransferCommand implements Command {
    private final BankAccount FromAccount;
    private final BankAccount ToAccount;
    private final double Amount;

    public TransferCommand(BankAccount fromAccount, BankAccount toAccount, double amount) {
        this.FromAccount = fromAccount;
        this.ToAccount = toAccount;
        this.Amount = amount;
    }

    @Override
    public void Execute() {
        FromAccount.Transfer(ToAccount, Amount);
    }

    @Override
    public void Undo() {
        ToAccount.Transfer(FromAccount, Amount);
    }
}

package ei.project.pattern.command;

import java.util.Scanner;

// Client
// Console-based program to interact with the banking system
public class BankingAppLauncher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransactionManager transactionManager = new TransactionManager();

        BankAccount savings = new BankAccount("ACC1001", "Savings Account", 5000);
        BankAccount checking = new BankAccount("ACC1002", "Checking Account", 3000);

        boolean isBankingActive = true;

        while (isBankingActive) {
            System.out.println("\n=== WELCOME TO OUR BANKING APP! ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Undo Last Transaction");
            System.out.println("5. Redo Last Transaction");
            System.out.println("6. Check Balance");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> deposit(scanner, transactionManager, savings, checking);
                case 2 -> withdraw(scanner, transactionManager, savings, checking);
                case 3 -> transfer(scanner, transactionManager, savings, checking);
                case 4 -> transactionManager.UndoLastCommand();
                case 5 -> transactionManager.RedoLastCommand();
                case 6 -> checkBalance(savings, checking);
                case 7 -> {
                    System.out.println("Thank you for using JBank! Have a great day!");
                    isBankingActive = false;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }

    private static void deposit(Scanner scanner, TransactionManager manager, BankAccount savings, BankAccount checking) {
        BankAccount acc = selectAccount(scanner, savings, checking);
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        manager.ExecuteCommand(new DepositCommand(acc, amount));
        System.out.printf("Deposit successful! ₹%.2f has been added to your %s.%n", amount, acc.getAccountName());
        BankLogger.info(String.format("Deposit: ₹%.2f to %s", amount, acc.getAccountName()));
        System.out.println("Thank you for banking with JBank!\n");
    }

    private static void withdraw(Scanner scanner, TransactionManager manager, BankAccount savings, BankAccount checking) {
        BankAccount acc = selectAccount(scanner, savings, checking);
        System.out.print("Enter amount to withdraw: ₹");
        double amount = scanner.nextDouble();

        manager.ExecuteCommand(new WithdrawCommand(acc, amount));
        System.out.printf("Withdrawal successful! ₹%.2f has been withdrawn from your %s.%n", amount, acc.getAccountName());
        BankLogger.info(String.format("Withdrawal: ₹%.2f from %s", amount, acc.getAccountName()));
        System.out.println("Thank you for using our BankingApp!\n");
    }

    private static void transfer(Scanner scanner, TransactionManager manager, BankAccount savings, BankAccount checking) {
        System.out.println("Select source account:");
        BankAccount fromAcc = selectAccount(scanner, savings, checking);
        BankAccount toAcc = (fromAcc == savings) ? checking : savings;

        System.out.print("Enter amount to transfer: ₹");
        double amount = scanner.nextDouble();

        manager.ExecuteCommand(new TransferCommand(fromAcc, toAcc, amount));
        System.out.printf("Transfer successful! ₹%.2f transferred from %s to %s.%n",
                amount, fromAcc.getAccountName(), toAcc.getAccountName());
        BankLogger.info(String.format("Transfer: ₹%.2f from %s to %s", amount, fromAcc.getAccountName(), toAcc.getAccountName()));
        System.out.println("Thank you for banking with JBank!\n");
    }

    private static void checkBalance(BankAccount savings, BankAccount checking) {
        System.out.println("\nAccount Balances:");
        System.out.printf("%s: ₹%.2f%n", savings.getAccountName(), savings.getBalance());
        System.out.printf("%s: ₹%.2f%n", checking.getAccountName(), checking.getBalance());
    }

    private static BankAccount selectAccount(Scanner scanner, BankAccount savings, BankAccount checking) {
        System.out.println("\nSelect account:");
        System.out.println("1. " + savings.getAccountName());
        System.out.println("2. " + checking.getAccountName());
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();

        return (choice == 1) ? savings : checking;
    }
}

import java.util.Scanner;

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be greater than 0.");
        }
        balance += amount;
    }

    public void withdraw(double amount)
            throws InsufficientFundsException {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }

        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient Funds!");
        }

        balance -= amount;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            BankAccount account = new BankAccount(
                "ACC-1001", "Alex Cruz", 500.00
            );

            System.out.println("=== BANK ACCOUNT EXCEPTION GATEKEEPER ===");
            System.out.println("Account Created: " + account.getAccountNumber());
            System.out.println("Holder: " + account.getAccountHolder());
            System.out.printf("Balance: $%.2f%n", account.getBalance());

            try {
                System.out.print("Enter withdrawal amount: ");
                double withdrawal = scanner.nextDouble();
                account.withdraw(withdrawal);

                System.out.println("Withdrawal successful!");
            } catch (InsufficientFundsException |
                     IllegalArgumentException e) {
                System.out.println("[EXCEPTION CAUGHT]: " + e.getMessage());
            } finally {
                System.out.println("Withdrawal session checked.");
            }

            try {
                System.out.print("Enter deposit amount: ");
                double deposit = scanner.nextDouble();
                account.deposit(deposit);

                System.out.println("Deposit successful!");
            } catch (IllegalArgumentException e) {
                System.out.println("[EXCEPTION CAUGHT]: " + e.getMessage());
            } finally {
                System.out.println("Deposit session checked.");
            }

            System.out.println("[SESSION COMPLETE]: Transaction log updated.");
            System.out.printf("Current Balance: $%.2f%n", account.getBalance());

        } catch (java.util.InputMismatchException e) {
            System.out.println("[INPUT ERROR]: Please enter numbers only.");
        } finally {
            System.out.println("Bank session closed.");
            scanner.close();
        }
    }
}
Compose

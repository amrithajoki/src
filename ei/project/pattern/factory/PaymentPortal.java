package ei.project.pattern.factory;
 import java.util.Scanner;
public class PaymentPortal{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to the Payment Portal ===");
        System.out.print("Enter payment type (CreditCard / PayPal / UPI): ");
        String paymentType = scanner.nextLine();

        System.out.print("Enter payment amount: ");
        double paymentAmount = scanner.nextDouble();

        try {
            Payment paymentMethod = PaymentFactory.createPayment(paymentType);
            paymentMethod.pay(paymentAmount);
        } catch (IllegalArgumentException e) {
            System.out.println("Payment failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

    


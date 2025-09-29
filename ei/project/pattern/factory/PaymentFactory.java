package ei.project.pattern.factory;
import java.io.IOException;
import java.util.logging.*;

// Factory class to create Payment objects
public class PaymentFactory {
    private static final Logger LOGGER = Logger.getLogger(PaymentFactory.class.getName());

    static {
        try {
            // FileHandler: log all messages to a file
            FileHandler fileHandler = new FileHandler("payment_logs.txt", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);

            // ConsoleHandler: display only WARNING and SEVERE
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.WARNING);
            consoleHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(consoleHandler);

            LOGGER.setUseParentHandlers(false);
            LOGGER.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Factory method to return appropriate Payment object
    public static Payment createPayment(String paymentType) {
        if (paymentType == null) return null;

        LOGGER.info("Requested payment type: " + paymentType);

        switch (paymentType.toLowerCase()) {
            case "creditcard":
                LOGGER.info("Credit Card payment selected");
                return new CreditCardPayment();
            case "paypal":
                LOGGER.info("PayPal payment selected");
                return new PayPalPayment();
            case "upi":
                LOGGER.info("UPI payment selected");
                return new UpiPayment();
            default:
                LOGGER.warning("Unknown payment type: " + paymentType);
                LOGGER.severe("Invalid payment type: " + paymentType);
                throw new IllegalArgumentException("Invalid payment type: " + paymentType);
        }
    }
}

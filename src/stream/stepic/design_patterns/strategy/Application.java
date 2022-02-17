package stream.stepic.design_patterns.strategy;

/**
 * While developing your IDE, another idea occurred to you.
 * You decided to send notifications to the users.
 * For that, you have created SMS and email services that send messages in different ways.
 * It looks like you have a strategy here!
 * <p>
 * To implement the Strategy pattern, create a NotificationStrategy interface
 * with the notifyCustomer method that accepts a user.
 * <p>
 * To use different strategies to send notifications,
 * implement the Notifier class that has a field of type NotificationStrategy.
 * You should be able to set a strategy by the Notifier constructor and a setter.
 * Don't forget to add a method that invokes NotificationStrategy.
 * <p>
 * Finally, in the Application class run method that accepts a user,
 * send an email and after that change the strategy and send SMS.
 * Use functional approach (lambdas and method references) while implementing the pattern.
 */
interface NotificationStrategy {
    void notifyCustomer(User user);
}

class Notifier {
    private NotificationStrategy notificationStrategy;

    public Notifier(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    public void setNotificationStrategy(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    public void notifySomebody(User user) {
        notificationStrategy.notifyCustomer(user);
    }
}

class Application {

    private final EmailService emailService;
    private final SMSService smsService;

    public Application(EmailService emailService, SMSService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }

    public void run(User user) {
        Notifier notifier = new Notifier(emailService::sendEmail);
        notifier.notifySomebody(user);
        notifier.setNotificationStrategy(smsService::sendSMS);
        notifier.notifySomebody(user);
    }
}

interface SMSService {
    void sendSMS(User user);
}


interface EmailService {
    void sendEmail(User user);
}

class User {
    private final String email;
    private final String phoneNumber;

    User(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
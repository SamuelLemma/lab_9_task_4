package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws LoginFailed, CapchaFailed,
            PaymentFailed, NumberFormatException, IOException {

        String username = "James", password = "123", capcha = "cat",
                userInput, passInput, capInput;
        int payment, goods = 350;
        BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Username: ");
            userInput = BR.readLine();
            System.out.print("Password: ");
            passInput = BR.readLine();
            System.out.print("Capcha: ");
            capInput = BR.readLine();

            if (!userInput.equals(username) || !passInput.equals(password)) {
                throw new LoginFailed();
            }
            if (!capInput.equals(capcha)) {
                throw new CapchaFailed();
            } else {
                System.out.print("Payment: ");
                payment = Integer.parseInt(BR.readLine());
                if (payment < goods) {
                    throw new PaymentFailed();
                }
            }

            Success.loginMessage(username);
            Success.paymentMessage(payment, goods);
        } catch (LoginFailed | CapchaFailed | PaymentFailed e) {
            System.out.println(e.getMessage());
        }
    }
}
class LoginFailed extends Exception {
    LoginFailed() {
        super("Invalid username or password.");
    }

    LoginFailed(String msg) {
        super(msg);
    }
}

class CapchaFailed extends Exception {
    CapchaFailed() {
        super("capcha failed! Please try again.");
    }

    CapchaFailed(String msg) {
        super(msg);
    }
}

class PaymentFailed extends Exception {
    PaymentFailed() {
        super("Invalid payment.");
    }

    PaymentFailed(String msg) {
        super(msg);
    }
}

class Success {
    public static void loginMessage(String username) {
        System.out.println("Login successfully, welcome back " + username);
    }

    public static void paymentMessage(int payment, int price) {
        String result = payment >= price
                ? ("Payment successfully, your exchange is: " + Integer.toString(payment -= price)
                + ". Enjoy your shopping.")
                : null;
        System.out.println(result);
    }
}
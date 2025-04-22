package com.mycompany.poe1;

import java.util.Scanner;

public class POE1 {
    
    private static String registeredUsername;
    private static String registeredPassword;
    private static String registeredFirstName;
    private static String registeredSurname;
    private static boolean isRegistered = false;

    
    public static void setTestUser(String username, String password, String firstName, String surname) {
        registeredUsername = username;
        registeredPassword = password;
        registeredFirstName = firstName;
        registeredSurname = surname;
        isRegistered = true;
    }

    
    public static void clearTestUser() {
        registeredUsername = null;
        registeredPassword = null;
        registeredFirstName = null;
        registeredSurname = null;
        isRegistered = false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Account System ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    registerUserFlow(scanner);
                    break;
                case 2:
                    loginUserFlow(scanner);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
        scanner.close();
    }

    
    private static void registerUserFlow(Scanner scanner) {
        System.out.println("\n=== REGISTRATION ===");
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        String registrationMessage = registerUser(username, password, firstName, surname);
        System.out.println(registrationMessage);
        
        if (registrationMessage.equals("User registered successfully!")) {
            isRegistered = true;
        }
    }

    
    private static void loginUserFlow(Scanner scanner) {
        if (!isRegistered) {
            System.out.println("No user registered yet. Please register first.");
            return;
        }
        
        System.out.println("\n=== LOGIN ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        boolean loginSuccess = loginUser(username, password);
        System.out.println(returnLoginStatus(loginSuccess));
    }

    // Username (contains underscore and ≤5 chars)
    public static boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    // Password  validation
    public static boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasUpper = false, hasDigit = false, hasSpecial = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        
        return hasUpper && hasDigit && hasSpecial;
    }

    
    public static String registerUser(String username, String password, String firstName, String surname) {
        boolean validUsername = checkUserName(username);
        boolean validPassword = checkPasswordComplexity(password);
        
        if (!validUsername) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }  
        if (!validPassword) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }
        
        
        registeredUsername = username;
        registeredPassword = password;
        registeredFirstName = firstName;
        registeredSurname = surname;
        isRegistered = true;
        
        return "User registered successfully!";
    }

    
    private static String registerUser() {
        return registerUser(registeredUsername, registeredPassword, registeredFirstName, registeredSurname);
    }

    // Login verification
    public static boolean loginUser(String username, String password) {
        return username.equals(registeredUsername) && password.equals(registeredPassword);
    }

    // Login status message
    public static String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + registeredFirstName + ", " + registeredSurname + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again";
        }
    }
}

// refernce list
// Farrell,J. 2019. Java Programming Ninth Edtion.Boston:Cengage

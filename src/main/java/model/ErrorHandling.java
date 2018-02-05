package model;

public class ErrorHandling {
    public static class RegisterUserExeption extends Exception {
        public RegisterUserExeption(String message) {
            super(message);
            System.out.println("ERROR! The following exception occured: " + message);
        }
    }
    public static class RegisterApplicationExeption extends Exception {
        public RegisterApplicationExeption(String message) {
            super(message);
            System.out.println("ERROR! The following exception occured: " + message);
        }
    }
}

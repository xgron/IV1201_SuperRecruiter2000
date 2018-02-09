package model;

public class ErrorHandling {
    public static class RegisterUserExeption extends Exception {
        /**
         *  This method is called from the model layer when an error occurs and an exception is thrown.
         *  This is for registerUser exceptions only.
         *
         *  It returns an error message, but also prints to the console
         *
         * @param   message     The error message for the specific error
         */
        public RegisterUserExeption(String message) {
            super(message);
            //System.out.println("ERROR! The following exception occured: " + message);
        }
    }
    public static class RegisterApplicationExeption extends Exception {
        /**
         *  This method is called from the model layer when an error occurs and an exception is thrown.
         *  This is for registerApplication exceptions only.
         *
         *  It returns an error message, but also prints to the console
         *
         * @param   message     The error message for the specific error
         */
        public RegisterApplicationExeption(String message) {
            super(message);
            //System.out.println("ERROR! The following exception occured: " + message);
        }
    }
}

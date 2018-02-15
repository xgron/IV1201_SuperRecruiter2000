package model;


import java.util.logging.Logger;

public class ErrorHandling {
    private final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static class RegisterUserException extends Exception {
        /**
         *  This method is called from the model layer when an error occurs and an exception is thrown.
         *  This is for registerUser exceptions only.
         *
         *  It returns an error message, but also prints to the console
         *
         * @param   message     The error message for the specific error
         */
        public RegisterUserException(String message) {
            super(message);
            logr.info("Exception thrown: " + message);
        }
    }
    public static class RegisterApplicationException extends Exception {
        /**
         *  This method is called from the model layer when an error occurs and an exception is thrown.
         *  This is for registerApplication exceptions only.
         *
         *  It returns an error message, but also prints to the console
         *
         * @param   message     The error message for the specific error
         */
        public RegisterApplicationException(String message) {
            super(message);
            logr.info("Exception thrown: " + message);

        }
    }
    public static class AuthenticateUserException extends Exception {
        /**
         *  This method is called from the model layer when an error occurs and an exception is thrown.
         *  This is for AuthenticateUser exceptions only.
         *
         *  It returns an error message, but also prints to the console
         *
         * @param   message     The error message for the specific error
         */
        public AuthenticateUserException(String message) {
            super(message);
            logr.info("Exception thrown: " + message);
        }
    }
}

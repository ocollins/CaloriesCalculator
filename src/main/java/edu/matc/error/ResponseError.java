package edu.matc.error;

/**
 * Class that generates a response error message. To be used to inform the user of this service's
 * paths and path variables.
 */
public class ResponseError {

    private String message;

    /**
     * Construct the error info message.
     */
    public ResponseError() {
        message = "HTTP status code: 500. You have reached this page in error. Please ensure that your path and all of the " +
                "path variables are correct. Paths and their uses can be found in the documentation:\n\n" +

                "https://github.com/ocollins/CaloriesCalculator/blob/master/UserGuide.md\n\n" +

                "If all else fails, the server may be down in which case we apologize and will get back up and running ASAP.";
    }

    /**
     * Info message to display to the user.
     *
     * @return info message to display to the user
     */
    public String getResponseErrorMessage() {
        return message;
    }
}

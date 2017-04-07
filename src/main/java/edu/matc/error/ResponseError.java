package edu.matc.error;

/**
 * Class that generates a response error message. To be used to inform the user of this service's
 * paths and path variables.
 */
public class ResponseError {

    private String message;
    private String header;
    private String body;
    private String footer;

    /**
     * Construct the error info message.
     */
    public ResponseError() {
        header = "You have reached this page in error. Please ensure that your path and all of the " +
                "path variables are correct. Paths and their uses are as follows:\n\n";

        body = "http://localhost:8080/activities\n" +
                "Returns: list of activities and their METS. Format: text/plain.\n\n" +

                "http://localhost:8080/activities/html/{activity}/{weight}/{duration}/{unit}\n" +
                "Returns: calories burned. Format: html.\n" +
                "Params: activity - int activity ID; weight - decimal amount; duration - decimal time in hours; unit - 'lb' or 'kg'\n\n" +

                "http://localhost:8080/activities/json/{activity}/{weight}/{duration}/{unit}\n" +
                "Returns: calories burned. Format: json.\n" +
                "Params: activity - int activity ID; weight - decimal amount; duration - decimal time in hours; unit - 'lb' or 'kg'\n\n" +

                "http://localhost:8080/activities/list\n" +
                "Returns: list of activities. Format: json.\n\n" +

                "http://localhost:8080/activities/text/{activity}/{weight}/{duration}/{unit}\n" +
                "Returns: calories burned. Format: text/plain.\n" +
                "Params: activity - int activity ID; weight - decimal amount; duration - decimal time in hours; unit - 'lb' or 'kg'\n\n" +

                "http://localhost:8080/duration/json/{activity}/{weight}/{calories}/{unit}\n" +
                "Returns: duration needed to burn given calories. Format: json.\n" +
                "Params: activity - int activity ID; weight - decimal amount; calories - decimal amount; unit - 'lb' or 'kg'\n\n" +

                "http://localhost:8080/duration/text/{activity}/{weight}/{calories}/{unit}\n" +
                "Returns: . Format: text/plain.\n" +
                "Params: activity - int activity ID; weight - decimal amount; calories - decimal amount; unit - 'lb' or 'kg'\n\n";

        footer = "If all else fails, the server may be down in which case we apologize and will get back up and running ASAP.";

        message = header + body + footer;
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

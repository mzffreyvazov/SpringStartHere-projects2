package org.example.exception;

public class ErrorResponse {
    private String message;

    // Default constructor
    public ErrorResponse() {
    }

    // Constructor with message
    public ErrorResponse(String message) {
        this.message = message;
    }

    // Getter
    public String getMessage() {
        return message;
    }

    // Setter
    public void setMessage(String message) {
        this.message = message;
    }
}
package ee.vladislav.backend.model;

import java.time.LocalDateTime;

public class ExceptionResponse {
    private LocalDateTime dateTime;
    private String message;

    public ExceptionResponse() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

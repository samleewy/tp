package seedu.address.model.session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import seedu.address.model.session.exceptions.SessionException;

/**
 * Represents the date and time of the session
 */
public class SessionDate {

    private static final String INCORRECT_DATE_TIME_FORMAT_ERROR_MESSAGE = "Format of date or time is incorrect: ";


    private LocalDateTime dateTime;

    /**
     * Constructs a {@code SessionDate}.
     *
     * @param dateValue string of date in YYYY-MM-DD format
     * @param timeValue string of time in HH:MM format
     */
    public SessionDate(String dateValue, String timeValue) throws SessionException {
        try {
            LocalDate localDate = LocalDate.parse(dateValue);
            LocalTime localTime = LocalTime.parse(timeValue);

            LocalDateTime localDateTime = localDate.atTime(localTime);
            this.dateTime = localDateTime;
        } catch (DateTimeParseException e) {
            throw new SessionException(INCORRECT_DATE_TIME_FORMAT_ERROR_MESSAGE + e, e);
        }
    }

    /**
     * test
     * @param dateTime
     * @throws SessionException
     */
    public SessionDate(String dateTime) throws SessionException {
        try {
            LocalDateTime localDt = LocalDateTime.parse(dateTime);
            this.dateTime = localDt;
        } catch (DateTimeParseException e) {
            throw new SessionException(INCORRECT_DATE_TIME_FORMAT_ERROR_MESSAGE + e, e);
        }
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns true if LocalTime and LocalDate of both objects are the same
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SessionDate)) {
            return false;
        }

        SessionDate sessionDate = (SessionDate) other;

        return this.dateTime.toLocalDate().equals(sessionDate.dateTime.toLocalDate())
                && this.dateTime.toLocalTime().equals(sessionDate.dateTime.toLocalTime());
    }
}

package util;

public class RegexValidators {
    private static final String EMAIL_VALIDATION = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";

    public static boolean validateEmail(final String email)  {
        return email.matches(EMAIL_VALIDATION);
    }
}

package pl.footballapp.bartek.service.validator;

import javax.swing.*;

public class ValidatorResult {

    private boolean valid;
    private String message;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void toMessageDialog() {
        if (!valid) {
            JOptionPane.showMessageDialog(null, message, "Błąd walidacji", JOptionPane.WARNING_MESSAGE);
        }
    }

}

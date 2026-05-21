package pl.footballapp.bartek.gui;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static pl.footballapp.bartek.gui.ParameterWindow.DATE_FORMAT_PATTERN;

public class DatePickerFormatter extends JFormattedTextField.AbstractFormatter {

    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_PATTERN);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormat.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar calendar = (Calendar) value;
            return dateFormat.format(calendar.getTime());
        }
        return "";
    }
}

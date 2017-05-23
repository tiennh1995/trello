package edu.ttl.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JFormattedTextField.AbstractFormatter;

public class DateLabelFormatter extends AbstractFormatter {

        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String datePattern = "dd/MM/yyyy";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern, Locale.getDefault());

        @Override
        public Object stringToValue(String text) throws ParseException {
            if(text.length()==10) {
                return dateFormatter.parseObject(text);
            }
            return null;
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }

    }
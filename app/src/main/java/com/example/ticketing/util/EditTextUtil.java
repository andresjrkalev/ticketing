package com.example.ticketing.util;

import android.widget.EditText;

import static com.example.ticketing.common.Constants.STRING_EMPTY;

public class EditTextUtil {
    public static String findText(EditText editText) {
        if (editText != null) {
            return editText.getText().toString();
        }
        return STRING_EMPTY;
    }

    public static void clear(EditText editText) {
        editText.setText(STRING_EMPTY);
    }
}

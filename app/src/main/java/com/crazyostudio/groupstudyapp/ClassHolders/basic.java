package com.crazyostudio.groupstudyapp.ClassHolders;

import android.widget.EditText;

public class basic {

    public static boolean Check(EditText etText) {
        if (etText.getText().toString().trim().length() == 0) return true;
        return false;
    }
}

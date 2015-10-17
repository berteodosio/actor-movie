package com.berteodosio.actormovie.validator;

import android.widget.EditText;

import java.util.regex.Pattern;

public class Validator {
    public static void validateName(EditText name) {
        String clientName = name.getText().toString();
        if (clientName.length() <= 3)
            name.setError("Por favor, digite um nome válido");
        else if (clientName.length() > 60)
            name.setError("Nome muito grande!");
        else
            name.setError(null);
    }
}

package com.berteodosio.actormovie.validator.general;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by bernardo on 27/09/15.
 */
public abstract class EditTextValidator implements TextWatcher {
    private EditText editText;

    public EditTextValidator(EditText editText) {
        this.editText = editText;
    }

    public abstract void validate(EditText editText);

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        validate(editText);
    }
}

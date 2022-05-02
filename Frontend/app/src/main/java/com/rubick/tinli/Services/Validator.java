package com.rubick.tinli.Services;

import com.santalu.maskara.widget.MaskEditText;

public class Validator {

    public static boolean isEmpty(String input){ return input.isEmpty(); }

    public static boolean hasMinimumLength(String input){
        return input.length() >= 6;
    }

    public static boolean isDone(MaskEditText input){ return input.isDone();}
}

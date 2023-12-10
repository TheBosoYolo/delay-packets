package com.agentkosticka.modmenu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigValues {
    public enum thOption {
        TOGGLE,
        HOLD
    }

    public static boolean showClone = true;
    public static boolean chatFeedback = true;

    public static thOption delayOutgoing = thOption.HOLD;
    public static thOption delayIncoming = thOption.HOLD;
    public static thOption delayBU = thOption.TOGGLE;
    public static thOption delayEU = thOption.TOGGLE;
}

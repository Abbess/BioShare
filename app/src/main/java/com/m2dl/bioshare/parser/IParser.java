package com.m2dl.bioshare.parser;

import java.util.ArrayList;

/**
 * Created by Abbes on 18/01/2015.
 */
public interface IParser {
    public ArrayList<String> getInitialElements();
    public ArrayList<String> getElementsByParentName(String parentName);


}

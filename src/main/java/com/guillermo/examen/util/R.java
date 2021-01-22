package com.guillermo.examen.util;

import java.io.File;
import java.net.URL;

/**
 * @author Guillermo Suarez
 */

public class R {
    public static URL getUI(String name){
        return Thread.currentThread().getContextClassLoader().getResource( name);
    }
}

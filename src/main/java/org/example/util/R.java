package org.example.util;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class R {
    public static InputStream getImage(String name) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("images" + File.separator + name);
    }

    public static InputStream getProperties(String name) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration" + File.separator + name);
    }

    public static URL getUI(String name) {
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("ui" + File.separator + name));
        return Thread.currentThread().getContextClassLoader().getResource("ui" + File.separator + name);
    }
}
package com.spnsolo;

import com.spnsolo.annotation.PropertyKey;
import com.spnsolo.util.HandlerProperties;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        System.out.println("///////////Unit 11 - Reflection///////////");
        System.out.println();
        try {
            AppProperties appProperties = (AppProperties) HandlerProperties.initializeProperties(AppProperties.class);
            System.out.println(appProperties);
        } catch (IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("//////////////////////////////////////////");
    }

}

package com.spnsolo.util;

import com.spnsolo.AppProperties;
import com.spnsolo.Main;
import com.spnsolo.annotation.PropertyKey;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class HandlerProperties {

    public static Object  initializeProperties() throws IllegalAccessException {
        Object appProperties = new AppProperties();
        Properties properties = loadProperties();

        Class<?> classOfInstance = appProperties.getClass();

        Field[] allFields = classOfInstance.getFields();
        for(Field field : allFields){
            if(field.isAnnotationPresent(PropertyKey.class)){
                PropertyKey property = field.getAnnotation(PropertyKey.class);

                String value = properties.getProperty(property.value());
                field.setAccessible(true);
                field.set(appProperties, value);
            }
        }

        return appProperties;
    }
    private static Properties loadProperties() {

        Properties properties = new Properties();

        try(InputStream input = Main.class.getResourceAsStream("/app.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return properties;
    }
}

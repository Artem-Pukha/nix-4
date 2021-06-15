package com.spnsolo.parser;

import com.spnsolo.annotation.DeserializeField;
import com.spnsolo.data.TableCsv;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public final class CsvMapper {
    public <T> List<T> getCsvAsListObjects(TableCsv tableCsv, Class<T> tClass) {
        try {
            Constructor<T> constructor = tClass.getConstructor();

            T target;
            List<T> list = new ArrayList<>();

            for (int i = 0; i < tableCsv.getCountRows(); i++) {
                target = constructor.newInstance();
                for(Field field : tClass.getDeclaredFields()){

                    field.setAccessible(true);

                    DeserializeField deserializeField = field.getAnnotation(DeserializeField.class);
                    String value = tableCsv.getCell(i,deserializeField.value());

                    Class<?> type = field.getType();

                    if (type == String.class) {
                        field.set(target, value);
                    } else if (type.isEnum()) {
                        field.set(target, Enum.valueOf((Class<Enum>) type, value.toUpperCase()));
                    } else if (type == int.class || type == Integer.class) {
                        field.set(target, Integer.parseInt(value));
                    } else if (type == long.class || type == Long.class) {
                        field.set(target, Long.parseLong(value));
                    } else if (type == double.class || type == Double.class) {
                        field.set(target, Double.parseDouble(value));
                    } else if (type == boolean.class || type == Boolean.class) {
                        field.set(target, Boolean.parseBoolean(value));
                    } else {
                        throw new UnsupportedOperationException("Unsupported field type (" +
                                type.getName() + ") is required for field " +
                                field.getName());
                    }
                }
                list.add(target);
            }
            return list;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}

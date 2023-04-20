package ru.nishpal.crud.annotation.processing;

import org.springframework.util.ReflectionUtils;
import ru.nishpal.crud.annotation.Email;
import ru.nishpal.crud.annotation.NotNull;
import ru.nishpal.crud.annotation.Size;
import ru.nishpal.crud.exception.ApplicationException;
import ru.nishpal.crud.exception.ExceptionMessage;
import ru.nishpal.crud.model.User;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class ProcessingAnnotation {

    public static boolean emailAnnotationProcessing(User user) {
        boolean result = false;
        for (Field field : user.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Email.class)) {
                String email = String.valueOf(ReflectionUtils.getField(field, user));
                String ePattern = "^[A-Za-z0-9+_.-]+@(.+)$";
                Pattern p = Pattern.compile(ePattern);
                if (p.matcher(email).matches()) result = true;
                else throw new ApplicationException(ExceptionMessage.EMAIL_IS_NOT_VALID);
            }
        }
        return result;
    }

    public static boolean sizeAnnotationProcessing(User user) {
        boolean result = false;
        for (Field field : user.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Size.class)) {
                Size annotation = field.getAnnotation(Size.class);
                int lengthString = String.valueOf(ReflectionUtils.getField(field, user)).length();
                int min = annotation.min();
                int max = annotation.max();
                if (min <= lengthString && lengthString <= max) result = true;
                else throw new ApplicationException(ExceptionMessage.SIZE_OF_STRING);
                }
            }
        return result;
    }

    public static boolean notNullAnnotationProcessing(User user) {
        boolean result = false;
        for (Field field : user.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(NotNull.class)) {
                if (ReflectionUtils.getField(field, user) != null) result = true;
                else throw new ApplicationException(ExceptionMessage.FIELD_IS_NULL);
            }
        }
        return result;
    }

    public static boolean checkFields(User user) {
        return notNullAnnotationProcessing(user) & sizeAnnotationProcessing(user) & emailAnnotationProcessing(user);
    }
}

package cn.yizhimcqiu.client.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FieldHelper {
    public static List<Object> getStaticFields(Class<?> clazz, Class<?> type, Predicate<Field> predicate) {
        List<Object> objects = new ArrayList<>();
        for (Field field : clazz.getFields()) {
            if (hasSuperClass(field.getType(), type) && predicate.test(field)) {
                try {
                    objects.add(field.get(null));
                } catch (IllegalAccessException ignored) { }
            }
        }
        return objects;
    }
    public static boolean hasSuperClass(Class<?> clazz1, Class<?> clazz2) {
        if (clazz1 == clazz2) {
            return true;
        }
        if (clazz1 == Object.class || clazz1 == null) {
            return false;
        }
        return hasSuperClass(clazz1.getSuperclass(), clazz2);
    }
}

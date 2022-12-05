package ClassReader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassReader {

    public static void showClassConstructors(Class<?> c) {
        System.out.println("Constructors:");
        for (Constructor<?> co : c.getConstructors()) {
            System.out.println(" " + co.getName());
        }
    }

    public static void showClassMethods(Class<?> c) {
        System.out.println("Methods:");
        for (Method m : c.getDeclaredMethods()) {
            System.out.println(m);
        }
    }

    public static void showClassFields(Class<?> c) {
        System.out.println("Fields:");
        for (Field f : c.getDeclaredFields()) {
            System.out.println(f);
        }
    }

    public static void showClassInterfaces(Class<?> c) {
        System.out.println("Interfaces:");
        for (Class<?> i : c.getInterfaces()) {
            System.out.println(i);
        }
    }

    public static void showClassSuperclass(Class<?> c) {
        System.out.println("Superclass:");
        System.out.println(c.getSuperclass().getName());
    }

    public static void line() {
        System.out.println("--------------------------------------------------");
    }

    public static void showClassInfo(String className) {
        try {
            System.out.println("############################################################");
            Class<?> c = Class.forName(className);
            System.out.println("Class name: " + c.getName());
            line();
            showClassConstructors(c);
            line();
            showClassMethods(c);
            line();
            showClassFields(c);
            line();
            showClassInterfaces(c);
            line();
            showClassSuperclass(c);
            line();
            System.out.println("############################################################");
            System.out.println();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        showClassInfo("ClassReader.Programmer");
        showClassInfo("ClassReader.Human");
    }
}

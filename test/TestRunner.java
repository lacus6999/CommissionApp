package test;


import main.logger.Logger;
import test.helper.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestRunner {

    public static void main(String[] args) {
        new TestRunner().init();
    }

    void init() {
        Logger.getLogger().disableLogger();
        Set<Class<?>> classes = findAllClassesByPackage("test.testclasses");
        classes.forEach(this::runTests);
    }

    public void runTests(Class<?> testClass) {
        List<Method> methods = Arrays.stream(testClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Test.class))
                .collect(Collectors.toList());
        methods.forEach((Method method) -> {
            try {
                method.invoke(testClass.getDeclaredConstructor().newInstance());
                System.out.println(method.getName() + " \u001B[32mpassed\u001B[0m");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(method.getName() + " \u001B[31mfailed\u001B[0m" + "\n");
                if (e.getMessage() != null) {
                    System.out.println("Error message: " + e.getMessage());
                }
            }
        });
    }

    public Set<Class<?>> findAllClassesByPackage(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load classes");
        }
        return null;
    }
}

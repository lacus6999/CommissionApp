package test;
import main.logger.Logger;
import test.helper.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class TestRunner {

    public static void main(String[] args) throws IOException {
        new TestRunner().init();
    }

    public void init() throws IOException {
        Logger.getLogger().disableLogger();
        Set<Class<?>> testClasses = findAllClassesByPackage("test.testclasses");
        StringJoiner testResult = new StringJoiner("\n");
        for (Class<?> testClass : testClasses) {
            runTests(testClass, testResult);
        }
        System.out.println(testResult);
    }

    public void runTests(Class<?> testClass, StringJoiner testResult) {
        List<Method> methods = Arrays.stream(testClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Test.class))
                .collect(Collectors.toList());
        methods.forEach((Method method) -> {
            try {
                method.invoke(testClass.getDeclaredConstructor().newInstance());
                testResult.add(method.getName() + "\t" + " \u001B[32mpassed\u001B[0m");
            } catch (Exception e) {
                testResult.add(method.getName() + "\t" + "\u001B[31mfailed\u001B[0m");
                Arrays.stream(e.getStackTrace()).forEach(stackTraceElement -> testResult.add(stackTraceElement.toString()));
                if (e.getMessage() != null) {
                    testResult.add("Error message: ").add(e.getMessage());
                }
            }
        });
    }

    public Set<Class<?>> findAllClassesByPackage(String packageName) throws IOException {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Set<Class<?>> collect = reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
        reader.close();
        return collect;
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

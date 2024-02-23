package test.helper;

public class Assertions {

    public static <T> void assertEquals(T o1, T o2) {
        if (!o1.equals(o2)) {
            throw new TestFailedException("Assertion error: " + o1 + " " + o2 + " not equals");
        }
    }

    public static <T> void assertNotEquals(T o1, T o2) {
        if (o1.equals(o2)) {
            throw new TestFailedException("Assertion error: " + o1 + " " + o2 + " equals");
        }
    }

}

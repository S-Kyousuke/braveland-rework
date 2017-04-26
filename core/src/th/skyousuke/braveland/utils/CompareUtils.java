package th.skyousuke.braveland.utils;

public class CompareUtils {

    private CompareUtils() {
    }

    public static boolean floatEquals(float value1, float value2) {
        return Float.compare(value1, value2) == 0;
    }
}

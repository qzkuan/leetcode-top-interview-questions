package topinterviewquestions;

public class Problem_0_Test {
    public static void main(String[] args) {
        String str = "-4139 -with";
        System.out.println(str);
        String result = myAtoi(str);
        System.out.println(result);
    }

    public static String myAtoi(String s) {
        String trim = s.trim();
        String result = trim.replaceAll("[^0-9|-]", "");
        return result;
    }
}

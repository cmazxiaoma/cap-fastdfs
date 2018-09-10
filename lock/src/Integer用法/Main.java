package Integer用法;
public class Main {

    public static void main(String[] args) {
        try {
            Integer i = new Integer(String.valueOf("1.5"));
        } catch (Exception e) {
            System.out.println("throw NumberFormatException");
        }
    }
}

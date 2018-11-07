package shadow.android.com.lib.reflected;

/**
 * Author : shadow
 * Desc :
 * Date :2018/11/2/002
 */
public class Fruit {
    private int price;
    private final Size size = new Size(50);

    public Fruit(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "fruit size is : " + size;
    }

    private void sale(int price) {
        System.out.println("hello shadow ,fruit " + price);
    }

    public static class Size {
        private int count;

        public Size(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "size = " + count;
        }
    }
}

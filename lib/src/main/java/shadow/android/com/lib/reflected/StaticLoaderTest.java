package shadow.android.com.lib.reflected;

/**
 * Author : shadow
 * Desc :静态加载测试
 * Date :2018/11/2/002
 */
public class StaticLoaderTest {
    public static void main(String[] args) {
        if ("world".equals(args[0])) {
            World world = new World();
            world.start();
        }

        if ("excel".equals(args[0])) {
            Excel excel = new Excel();
            excel.start();
        }
    }
}

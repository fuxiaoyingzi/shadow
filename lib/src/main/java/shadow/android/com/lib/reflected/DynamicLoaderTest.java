package shadow.android.com.lib.reflected;

/**
 * Author : shadow
 * Desc :动态加载测试
 * Date :2018/11/2/002
 */
public class DynamicLoaderTest {
    public static void main(String[] args){
        try {
            //动态加载类，
           // Class clazz = Class.forName(args[0]);
            Class clazz = Class.forName("shadow.android.com.lib.reflected.World");
            //由类类型，创建类的实例
            OfficeBase officeBase = (OfficeBase) clazz.newInstance();
            //扩展类
            officeBase.start();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}

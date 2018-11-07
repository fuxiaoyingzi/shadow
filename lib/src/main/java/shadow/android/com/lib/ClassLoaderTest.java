package shadow.android.com.lib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Author : shadow
 * Desc :
 * Date :2018/11/1/001
 */
public class ClassLoaderTest {
    public static void main(String[] args){
        System.out.println("hello shadow");
        DiskClassLoader diskClassLoader = new DiskClassLoader("D:\\classLoader");
        try {
            Class c = diskClassLoader.loadClass("com.java.shadow.Hello");
            if (c != null) {
                Object object = c.newInstance();
                System.out.println(object.getClass().getClassLoader());
                Method method = c.getDeclaredMethod("say", null);
                method.invoke(object, null);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

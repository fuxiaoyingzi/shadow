package shadow.android.com.lib.reflected;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Author : shadow
 * Desc :
 * Date :2018/11/2/002
 */
public class TestReflected {
    public static void main(String[] args) {
        //invokeFruitSale();
        modifyFruitSize();
    }

    private static void modifyFruitSize() {
        try {
            Class clazz = Class.forName("shadow.android.com.lib.reflected.Apple");
            Class fruitClass = clazz.getSuperclass();
            Constructor constructor = fruitClass.getDeclaredConstructor(int.class);
            constructor.setAccessible(true);
            Fruit fruit = (Fruit) constructor.newInstance(0);
            //修改之前打印fruit size
            System.out.println("before modify fruit size" + fruit);

            //获取私有变量
            Field sizeField = fruitClass.getDeclaredField("size");
            sizeField.setAccessible(true);
            //我们利用反射将size改为非final类型
            Field modifierField = sizeField.getClass().getDeclaredField("modifiers");
            modifierField.setAccessible(true);
            //修改类型
            int modifiers = sizeField.getModifiers() & ~Modifier.FINAL;
            modifierField.set(sizeField, modifiers);
            //设置新的size
            Fruit.Size size = new Fruit.Size(500);
            sizeField.set(fruit, size);
            System.out.println("after modify fruit size" + fruit);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static void invokeFruitSale() {
        try {
            //获取Apple类
            Class clazz = Class.forName("shadow.android.com.lib.reflected.Apple");
            //获取Apple的直接父类Fruit
            Class fruitClass = clazz.getSuperclass();

            //获取fruit的私有方法
            Method saleMethod = fruitClass.getDeclaredMethod("sale", int.class);
            saleMethod.setAccessible(true);

            //获取父类的私有构造器
            Constructor constructor = fruitClass.getDeclaredConstructor(int.class);
            //设置私有可访问
            constructor.setAccessible(true);
            //通过私有构造器新建Fruit对象
            Fruit fruit = (Fruit) constructor.newInstance(0);


            //调用方法
            saleMethod.invoke(fruit, 100);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}

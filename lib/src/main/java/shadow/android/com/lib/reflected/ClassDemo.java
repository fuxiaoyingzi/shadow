package shadow.android.com.lib.reflected;

/**
 * Author : shadow
 * Desc :
 * Date :2018/11/2/002
 */
public class ClassDemo {
    //类也是对象，是Class类的实例对象

    public static void main(String[] args){
        Class c1 = Foo.class;
        Foo mFoo = new Foo();
        Class c2 = mFoo.getClass();
        {
            try {
                Class c3 = Class.forName("shadow.android.com.lib.reflected.Foo");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println(c1 == c2);

        //通过该类的类类型，得到该类的实例对象
        try {
            Foo foo = (Foo) c1.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
class Foo{}

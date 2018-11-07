package shadow.android.com.lib;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class DiskClassLoader extends ClassLoader {
    private String path;

    public DiskClassLoader(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String s) throws ClassNotFoundException {
        Class clazz = null;
        //加载类文件字节码数组
        byte[] classData = loadClassData(s);
        if (classData != null) {
            //字节码数组，转换为class类实例
            clazz = defineClass(s, classData, 0, classData.length);
        } else {
            throw new ClassNotFoundException();
        }
        return clazz;
    }

    private byte[] loadClassData(String s) {
        String fileName = getFileName(s);
        File file = new File(path,fileName);
        InputStream inputStream = null;
        ByteArrayOutputStream arrayOutputStream = null;
        try {
            inputStream = new FileInputStream(file);
            arrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(bytes)) != -1) {
                arrayOutputStream.write(bytes);
            }
            return arrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (arrayOutputStream != null) {
                try {
                    arrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private String getFileName(String s) {
        int index = s.lastIndexOf(".");
        //如果没有找到，则直接在末尾添加.class
        if (index == -1) {
            return s + ".class";
        } else {
            return s.substring(index + 1) + ".class";
        }
    }
}

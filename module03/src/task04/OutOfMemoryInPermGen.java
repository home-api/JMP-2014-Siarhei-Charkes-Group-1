package task04;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class OutOfMemoryInPermGen {

    public static void main(String[] args) throws IOException {
        Class<?> clazz = OutOfMemoryInPermGen.class;
        String path = clazz.getName().replace(".", "/") + ".class";
        InputStream inputStream = clazz.getClassLoader().getResourceAsStream(path);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream(4096);
        byte[] buff = new byte[1024];
        int i;
        while ((i = inputStream.read(buff)) >= 0) {
            buffer.write(buff, 0, i);
        }

        byte[] classBytes = buffer.toByteArray();

        MyClassLoader myClassLoader = new MyClassLoader();

        for (int index = 0; index < Long.MAX_VALUE; index++) {
            String newClassName =
                    "_" + String.format("%0"
                            + (clazz.getSimpleName().length() - 1) + "d", index);
            byte[] newClassData = new String(classBytes, "latin1")
                    .replaceAll(clazz.getSimpleName(), newClassName)
                    .getBytes("latin1");
            myClassLoader.defineClass(
                    newClassData,
                    clazz.getName().replace(clazz.getSimpleName(), newClassName));
        }
    }
}

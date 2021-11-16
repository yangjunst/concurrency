package com.wangwenjun.concurrent.juc.automic;

import java.io.File;
import java.io.FileInputStream;

import static com.wangwenjun.concurrent.juc.automic.UnsafeExample1.getUnsafe;

public class UnsafeExample3
{
    public static void main(String[] args)
            throws Exception
    {
        byte[] classContents = getClassContent();
        Class c = getUnsafe().defineClass(null, classContents, 0, classContents.length, null, null);
        Object result = c.getMethod("getI").invoke(c.newInstance(), null);
        assert (Integer) result == 10;
    }

    private static byte[] getClassContent() throws Exception
    {
        File f = new File("C:\\Users\\wangwenjun\\IdeaProjects\\java-concurrency-book2\\target\\classes\\com\\wangwenjun\\concurrent\\juc\\automic\\A.class");
        try (FileInputStream input = new FileInputStream(f))
        {
            byte[] content = new byte[(int) f.length()];
            input.read(content);
            return content;
        }
    }
}

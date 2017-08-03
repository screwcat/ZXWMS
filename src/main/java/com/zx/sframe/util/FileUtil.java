package com.zx.sframe.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class FileUtil {

    public static byte[] toByteArray(InputStream ins) throws IOException
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int n = 0;
        while (-1 != (n = ins.read(buffer)))
        {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }
}

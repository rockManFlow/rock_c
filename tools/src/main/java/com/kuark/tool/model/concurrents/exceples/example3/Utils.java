package com.kuark.tool.model.concurrents.exceples.example3;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by caoqingyuan on 2017/6/23.
 */
public class Utils {
    public static FileOutputStream out = null;
    public static void writeDataIntoFile(String info) {
        try {
            if (out == null) {
                out = new FileOutputStream("e:/bbbbbb.txt", true);
            }
            out.write((info + "\r\n").getBytes());
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

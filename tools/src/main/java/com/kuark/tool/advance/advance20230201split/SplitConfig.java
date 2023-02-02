package com.kuark.tool.advance.advance20230201split;


public class SplitConfig {
    //最小拆分byte
    public static final int MIN_SPLIT_SIZE=2048;

    //开始位置
    public static long START_LOCATION=0L;

    //原文件路径
    public static final String SOURCE_FILE_PATH="/Users/opayc/products/tools/src/main/resources/split-file/split.txt";

    //目标文件前缀名
    public static String TARGET_FILE_NAME_PRE;

    //目标文件夹路径
    public static String TARGET_FILE_FOLDER="/Users/opayc/products/tools/src/main/resources/split-file/out-file/";
}

package com.kuark.tool.advance.advance20230201split;

import java.io.IOException;

/**
 * 一个1G的大文件，但程序可用内存只有2M，想处理这1G文件，该怎么处理？
 * 算法
 * 把大文件拆分成1000个1M大小的小文件，再分别对各个小文件进行处理，把处理结果写到新的结果文件中，
 * 再检测结果文件大小是否大于1M，大于再进行拆分并处理（执行上一步骤）直到结果文件小于1M，
 * 再把这个最终文件一次加载到内存中进行最终结果的处理。
 */
public class SplitMain {
    public static void main(String[] args) throws IOException {
        SplitProcess.split();
    }
}

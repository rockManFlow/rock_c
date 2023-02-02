package com.kuark.tool.advance.advance20190905.vo;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author rock
 * @detail
 * @date 2021/8/9 16:19
 */
@Data
public class ComparableVo  implements Comparable{
    private String name;

    @Override
    public int compareTo(@NotNull Object o) {
        return 0;
    }
}

package com.kuark.tool.advance.advance20210810;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rock
 * @detail
 * @date 2021/11/10 18:29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeanTwo {
    private String name;
    private Long age;
    private String tag="AA";
}

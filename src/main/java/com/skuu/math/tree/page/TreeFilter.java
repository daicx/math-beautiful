package com.skuu.math.tree.page;

import lombok.Data;

/**
 * 树条件过滤
 *
 * @author dcx
 * @since 2024-03-05 09:47
 **/
@Data
public class TreeFilter {
    private String name;
    private Integer page;
    private Integer pageSize;
}

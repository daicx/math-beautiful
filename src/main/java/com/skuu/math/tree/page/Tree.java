package com.skuu.math.tree.page;

import lombok.Data;

import java.util.List;

/**
 * @author dcx
 */
@Data
public class Tree {
    /**
     * 树id
     **/
    private Long id;
    /**
     * 树父id
     **/
    private Long pid;
    /**
     * 树名称
     **/
    private String name;
    /**
     * 子节点
     **/
    private List<Tree> child;
}

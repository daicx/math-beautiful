package com.skuu.math.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 树形工具类
 *
 * @author dcx
 * @since 2024-11-20 09:44
 **/
public class TreeLamdaUtil {

    @Data
    public static class MenuVo {
        private Long id;
        private Long pId;
        private String name;
        private Integer sort = 0;
        private List<MenuVo> subMenus = new ArrayList<>();

        public MenuVo(Long id, Long pId) {
            this.id = id;
            this.pId = pId;
        }

    }

    public static <E> List<E> makeTree(List<E> list, Predicate<E> rootCheck, BiFunction<E, E, Boolean> parentCheck, BiConsumer<E, List<E>> setSubChildren) {
        return list.stream().filter(rootCheck).peek(x -> setSubChildren.accept(x, makeChildren(x, list, parentCheck, setSubChildren))).collect(Collectors.toList());
    }

    private static <E> List<E> makeChildren(E parent, List<E> allData, BiFunction<E, E, Boolean> parentCheck, BiConsumer<E, List<E>> setSubChildren) {
        return allData.stream().filter(x -> parentCheck.apply(parent, x)).peek(x -> setSubChildren.accept(x, makeChildren(x, allData, parentCheck, setSubChildren))).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        MenuVo menu0 = new MenuVo(0L, -1L);
        MenuVo menu1 = new MenuVo(1L, 0L);
        MenuVo menu2 = new MenuVo(2L, 0L);
        MenuVo menu3 = new MenuVo(3L, 1L);
        MenuVo menu4 = new MenuVo(4L, 1L);
        MenuVo menu5 = new MenuVo(5L, 2L);
        MenuVo menu6 = new MenuVo(6L, 2L);
        MenuVo menu7 = new MenuVo(7L, 3L);
        MenuVo menu8 = new MenuVo(8L, 3L);
        MenuVo menu9 = new MenuVo(9L, 4L);
        //基本数据
        List<MenuVo> menuList = Arrays.asList(menu0, menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8, menu9);
        //合成树
        List<MenuVo> tree = TreeLamdaUtil.makeTree(menuList, x -> x.getPId() == -1L, (x, y) -> x.getId().equals(y.getPId()), MenuVo::setSubMenus);
        System.out.println(tree);
    }

}

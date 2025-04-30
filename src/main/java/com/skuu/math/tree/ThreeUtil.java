package com.skuu.math.tree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.Data;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * @author dcx
 * @since 2025-04-28 10:52
 **/
public class ThreeUtil {
    @Data
    static class Menu {
        private Integer id;
        private Integer pid;
        private Integer name;
        private List<Menu> child;

        public Menu(Integer id, Integer pid) {
            this.id = id;
            this.pid = pid;
        }
    }

    public static List<Menu> listToTree(List<Menu> menus, Integer pid) {

        //1.找到根节点
        //2.空间换时间
        List<Menu> menuTrees = new ArrayList<>();
        Map<Integer, Menu> menuMap = new HashMap<>();
        for (Menu menu : menus) {
            Integer id = menu.getId();
            if (Objects.equals(id, pid)) {
                menuTrees.add(menu);
            }
            menuMap.put(id, menu);

        }
        //每个子节点找父节点
        for (Menu menu : menus) {
            Integer pid1 = menu.getPid();
            Integer id = menu.getId();
            //根节点不需要找
            if (id.equals(pid)) {
                continue;
            }
            boolean containsKey = menuMap.containsKey(pid1);
            if (containsKey) {
                Menu menu1 = menuMap.get(pid1);
                List<Menu> child = menu1.getChild();
                if (child == null) {
                    List<Menu> menus1 = new ArrayList<>();
                    menus1.add(menu);
                    menu1.setChild(menus1);
                } else {
                    child.add(menu);
                }
            }
        }
        return menuTrees;
    }

    public static void main(String[] args) throws JsonProcessingException {
        Menu menu0 = new Menu(0, -1);
        Menu menu1 = new Menu(1, 0);
        Menu menu2 = new Menu(2, 0);
        Menu menu3 = new Menu(3, 1);
        Menu menu4 = new Menu(4, 1);
        Menu menu5 = new Menu(5, 2);
        Menu menu6 = new Menu(6, 2);
        Menu menu7 = new Menu(7, 3);
        Menu menu8 = new Menu(8, 3);
        Menu menu9 = new Menu(9, 4);
        ArrayList<@Nullable Menu> menus = Lists.newArrayList();
        menus.add(menu0);
        menus.add(menu1);
        menus.add(menu2);
        menus.add(menu3);
        menus.add(menu4);
        menus.add(menu5);
        menus.add(menu6);
        menus.add(menu7);
        menus.add(menu8);
        menus.add(menu9);
        List<Menu> menus1 = listToTree(menus, 0);
        System.out.println(new ObjectMapper().writeValueAsString(menus1));
    }
}

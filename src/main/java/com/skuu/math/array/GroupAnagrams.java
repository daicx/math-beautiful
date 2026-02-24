package com.skuu.math.array;


import java.util.*;

/**
 * 字母异位词分组
 * <OrderCglibProxy href = "https://leetcode.cn/problems/group-anagrams/description/?envType=study-plan-v2&envId=top-100-liked"/>
 * <p>
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 *
 * @author dcx
 * @since 2024-11-13 11:04
 **/
public class GroupAnagrams {

    /**
     * 更像是一个应用题，主要是将字符串进行排序，然后分组
     * @param strs
     * @return java.util.List<java.util.List<java.lang.String>>
     * @author dcx
     * @date 2024/11/13 11:18
     **/
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s = new String(chars);
            if (map.containsKey(s)) {
                List<String> strings = map.get(s);
                strings.add(str);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(str);
                map.put(s, list);
            }
        }
        return new ArrayList<>(map.values());
    }
}

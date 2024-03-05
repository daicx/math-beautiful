package com.skuu.math.tree.page;

import com.google.common.collect.Lists;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author dcx
 * @since 2024-03-04 17:54
 **/
public class TreeUtil {

    /**
     * 列表转树
     *
     * @param list     节点列表
     * @param parentId 根id
     * @return java.util.List<com.skuu.math.tree.page.Tree>
     * @author dcx
     * @date 2024/3/4 18:04
     **/
    public List<Tree> listToTree(List<Tree> list, Long parentId) {
        Map<Long, Tree> idMap = new HashMap<>();
        List<Tree> rootData = new ArrayList<>();
        for (Tree item : list) {
            //1.遍历找到顶层节点
            if (item.getPid().equals(parentId)) {
                rootData.add(item);
            }
            //2.空间换时间，将tree缓存起来
            idMap.put(item.getId(), item);
        }
        for (Tree item : list) {
            Long tmpParentId = item.getPid();
            // 3.排除根节点
            if (tmpParentId.equals(parentId)) {
                continue;
            }
            //4.没有根节点的，不显示。
            Tree parent = idMap.get(tmpParentId);
            if (parent == null) {
                continue;
            }
            //5.如果该父节点没有child，初始化一个空的。
            if (parent.getChild() == null) {
                parent.setChild(new ArrayList<>());
            }
            //6.把循环到的节点，添加到父节点。
            parent.getChild().add(item);
        }
        return rootData;
    }

    /**
     * 树过滤
     *
     * @param trees      全部树
     * @param treeFilter 树过滤条件
     * @return void
     * @author dcx
     * @date 2024/3/5 09:52
     **/
    public void filterTree(List<Tree> trees, TreeFilter treeFilter) {
        bottomToUpRetain(trees, (tree) -> {
            //是否删除
            String name = treeFilter.getName();
            if (!StringUtils.isEmpty(name)) {
                boolean contains = tree.getName().contains(name);
                if (!contains) {
                    return true;
                }
            }
            return false;
        });

    }


    /**
     * 传入一棵树，递归到子，从子到父过节点，只要存在符合条件的子节点，则保留从父到子的路径树，
     * 根据预留函数式接口，利用模板方法设计模式，过滤全量资产树
     * 因java8 collection只提供removeIf，无提供retainIf，故传参判断是否保留
     *
     * @param treeNodeModels   全量资产树
     * @param templateFunction 函数接口，封装模板方法设计模式，封装资产节点查询条件
     * @param isRemove         是否是要从树节点删除
     */
    private void filerAssetTree(List<Tree> treeNodeModels, Function<Tree, Boolean> templateFunction, boolean isRemove) {
        treeNodeModels.removeIf(item -> {
            // 有子节点，进递归
            if (checkNotEmpty(item.getChild())) {
                filerAssetTree(item.getChild(), templateFunction, isRemove);
            }
            // 无子节点，判断当前节点是否符合条件，不符合删除
            if (checkEmpty(item.getChild())) {
                // 判断是否删除，为true的删除
                return isRemove == templateFunction.apply(item);
            }
            // 跳出递归，还存在符合条件的子节点，不删除
            return false;
        });
    }

    private boolean checkNotEmpty(List<Tree> list) {
        return list != null && !list.isEmpty();
    }

    private boolean checkEmpty(List<Tree> list) {
        return list == null || list.isEmpty();
    }

    /**
     * 自下而上，从子到父，过滤出符合条件的节点，保留完整树结构
     *
     * @param treeNodeModels   资产树
     * @param templateFunction 资产节点查询条件
     */
    private void bottomToUpRetain(List<Tree> treeNodeModels, Function<Tree, Boolean> templateFunction) {
        filerAssetTree(treeNodeModels, templateFunction, false);
    }

    /**
     * 自下而上，从子到父，删除掉符合条件的节点，留下来的节点，保留完整的树结构，从子到父
     *
     * @param treeNodeModels   资产树
     * @param templateFunction 资产节点查询条件
     */
    private void bottomToUpRemove(List<Tree> treeNodeModels, Function<Tree, Boolean> templateFunction) {
        filerAssetTree(treeNodeModels, templateFunction, true);
    }

    /**
     * 传入一棵树，由上到下过滤节点，过滤出符合条件的节点，只要不符合条件的节点，从树从删除该节点
     * 根据预留函数式接口，利用模板方法设计模式，过滤全量资产树
     * 因java8 collection只提供removeIf，无提供retainIf，故传参判断是否保留
     *
     * @param treeNodeModels   资产树
     * @param templateFunction 函数接口，封装模板方法设计模式，封装资产节点查询条件
     * @param isRemove         是否是要从树节点删除
     */
    private void filterTopToDown(List<Tree> treeNodeModels, Function<Tree, Boolean> templateFunction, boolean isRemove) {
        treeNodeModels.removeIf(item -> {
            // 判断当前节点是否符合条件，为true的删除
            if (templateFunction.apply(item)) {
                return isRemove == templateFunction.apply(item);
            }
            // 如果当前节点符合条件，并且存在子节点，则递归进入子节点继续遍历
            if (!item.getChild().isEmpty()) {
                filterTopToDown(item.getChild(), templateFunction, isRemove);
            }
            // 子节点符合条件，则保留
            return false;
        });
    }

    /**
     * 自上而下，从父到子，过滤出符合条件的节点
     *
     * @param treeNodeModels   资产树
     * @param templateFunction 资产节点查询条件
     */
    private void topToDownRetain(List<Tree> treeNodeModels, Function<Tree, Boolean> templateFunction) {
        filterTopToDown(treeNodeModels, templateFunction, false);
    }

    /**
     * 自上而下，从父到子，删除掉符合条件的节点
     *
     * @param treeNodeModels   资产树
     * @param templateFunction 资产节点查询条件
     */
    private void topToDownRemove(List<Tree> treeNodeModels, Function<Tree, Boolean> templateFunction) {
        filterTopToDown(treeNodeModels, templateFunction, true);
    }

    /**
     * 树分页
     * @param trees
     * @param treeFilter
     * @return java.util.List<com.skuu.math.tree.page.Tree>
     * @author dcx
     * @date 2024/3/5 10:09
     **/
    public List<Tree> page(List<Tree> trees, TreeFilter treeFilter) {
        Integer pageSize = treeFilter.getPageSize();
        List<List<Tree>> partition = Lists.partition(trees, pageSize);
        Integer page = treeFilter.getPage();
        return partition.get(page);
    }

    public static void main(String[] args) {
        TreeUtil treeUtil = new TreeUtil();
        //列表
        ArrayList<Tree> trees = Lists.newArrayList();
        Long parentId = 1L;
        //列表转树
        List<Tree> treeTree = treeUtil.listToTree(trees, parentId);
        //过滤条件
        TreeFilter treeFilter = new TreeFilter();
        //树过滤
        treeUtil.filterTree(treeTree,treeFilter);
        //树分页
        treeUtil.page(treeTree,treeFilter);
    }

}

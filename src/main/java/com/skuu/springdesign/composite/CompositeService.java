package com.skuu.springdesign.composite;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

/**
 * 组合：树节点用 List<Node> 表示子节点，遍历用 Stream 递归。
 */
@Service
public class CompositeService {

    public static class Node {
        String name;
        List<Node> children;
        public Node(String name, List<Node> children) { this.name = name; this.children = children; }
    }

    public Stream<Node> flatten(Node root) {
        return Stream.concat(
            Stream.of(root),
            root.children == null ? Stream.empty() : root.children.stream().flatMap(this::flatten)
        );
    }
}

package solution;

import javaslang.control.Validation;

import java.io.*;
import java.util.*;
import java.util.stream.*;

/**
 * @author OZY on 2016.11.11.
 */
public class Solution {

    static String SExpression(String nodes) {
        final Validation<ErrorType, String> result = parse(nodes);
        if (result.isValid()) {
            return result.get();
        }
        throw new NodeParserError(result.getError());
    }

    static Validation<ErrorType, String> parse(String nodes) {
        if (nodes == null) return Validation.invalid(ErrorType.NODES_ARE_NULL);
        try {
            final List<Edge> edges = Stream.of(nodes.split(" "))
                    .map(node -> new Edge(parseHead(node), parseTail(node)))
                    .sorted()
                    .collect(Collectors.toList());

            if (edges.isEmpty()) {
                return Validation.invalid(ErrorType.NO_EDGES);
            }

            final Edge rootEdge = edges.get(0);
            final Tree tree = new Tree(rootEdge.head, rootEdge.tail);

            try {
                edges.stream()
                        .skip(1L)
                        .forEach(edge -> addEdge(tree.root, edge));
            } catch (TooManyNodes tooManyNodes) {
                return Validation.invalid(ErrorType.MORE_THAN_TWO_CHILDREN);
            }

            return Validation.valid(TreePrinter.print(tree));

        } catch (Throwable e) {
            return Validation.invalid(ErrorType.INVALID_INPUT);
        }
    }

    private static boolean addEdge(Node root, Edge edge) {
        if (root == null) return false;
        if (root.value.equals(edge.head)) {

            if (root.hasRight()) {
                throw new TooManyNodes(root.value + " already has two nodes");
            }

            if (root.hasLeft()) {
                root.setRight(new Node(edge.tail));
                return true;
            }

            root.setLeft(new Node(edge.tail));
            return true;
        }

        return addEdge(root.left, edge) || addEdge(root.right, edge);
    }

    static Character parseHead(String value) {
        return Stream.of(value.split(","))
                    .findFirst()
                    .map(String::trim)
                    .filter(s -> s.startsWith("("))
                    .map(s -> s.charAt(1))
                    .orElseThrow(() -> new IllegalArgumentException(value));
    }

    static Character parseTail(String value) {
        return Stream.of(value.split(","))
                .skip(1L)
                .map(String::trim)
                .filter(s -> s.endsWith(")"))
                .map(s -> s.charAt(0))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(value));
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        String res;
        String _nodes;
        try {
            _nodes = in.nextLine();
        } catch (Exception e) {
            _nodes = null;
        }

        res = SExpression(_nodes);
        bw.write(res);
        bw.newLine();

        bw.close();
    }

}

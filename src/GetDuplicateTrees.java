import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetDuplicateTrees {
    private HashMap<String, Integer> map;
    private List<TreeNode> result;

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Serialize the tree structure to identify duplicates
    private String serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }

        String serial = root.val + "," + serialize(root.left) + "," + serialize(root.right);

        // Track the serialization
        map.put(serial, map.getOrDefault(serial, 0) + 1);

        // If we've seen this subtree exactly twice, add it to result
        if (map.get(serial) == 2) {
            result.add(root);
        }

        return serial;
    }

    public List<TreeNode> getDuplicateTrees(TreeNode root) {
        map = new HashMap<>();
        result = new ArrayList<>();
        serialize(root);
        return result;
    }

    // Helper method to print tree structure
    private static void printTree(TreeNode root, String prefix, boolean isLeft) {
        if (root == null) {
            return;
        }

        System.out.println(prefix + (isLeft ? "├── " : "└── ") + root.val);

        if (root.left != null || root.right != null) {
            if (root.left != null) {
                printTree(root.left, prefix + (isLeft ? "│   " : "    "), true);
            } else {
                System.out.println(prefix + (isLeft ? "│   " : "    ") + "├── null");
            }

            if (root.right != null) {
                printTree(root.right, prefix + (isLeft ? "│   " : "    "), false);
            } else {
                System.out.println(prefix + (isLeft ? "│   " : "    ") + "└── null");
            }
        }
    }

    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
        System.out.println("Root: " + root.val);
        if (root.left != null || root.right != null) {
            if (root.left != null) {
                printTree(root.left, "", true);
            } else {
                System.out.println("├── null");
            }
            if (root.right != null) {
                printTree(root.right, "", false);
            } else {
                System.out.println("└── null");
            }
        }
    }

    public static void main(String[] args) {
        GetDuplicateTrees solution = new GetDuplicateTrees();

        System.out.println("=== Test Case 1: Classic Example ===");
        System.out.println("Tree structure:");
        System.out.println("       1");
        System.out.println("      / \\");
        System.out.println("     2   3");
        System.out.println("    /   / \\");
        System.out.println("   4   2   4");
        System.out.println("      /");
        System.out.println("     4");
        System.out.println();

        // Build tree: [1,2,3,4,null,2,4,null,null,4]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.left.left = new TreeNode(4);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(2);
        root1.right.left.left = new TreeNode(4);
        root1.right.right = new TreeNode(4);

        List<TreeNode> duplicates1 = solution.getDuplicateTrees(root1);
        System.out.println("Found " + duplicates1.size() + " duplicate subtree(s):");
        for (int i = 0; i < duplicates1.size(); i++) {
            System.out.println("\nDuplicate #" + (i + 1) + ":");
            printTree(duplicates1.get(i));
        }
        System.out.println("Expected: 2 duplicates (subtree [4] and subtree [2,4])");
        System.out.println();

        System.out.println("=== Test Case 2: All Same Values ===");
        System.out.println("Tree structure:");
        System.out.println("     2");
        System.out.println("    / \\");
        System.out.println("   2   2");
        System.out.println("  /   /");
        System.out.println(" 2   2");
        System.out.println();

        // Build tree: [2,2,2,2,null,2]
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.right.left = new TreeNode(2);

        GetDuplicateTrees solution2 = new GetDuplicateTrees();
        List<TreeNode> duplicates2 = solution2.getDuplicateTrees(root2);
        System.out.println("Found " + duplicates2.size() + " duplicate subtree(s):");
        for (int i = 0; i < duplicates2.size(); i++) {
            System.out.println("\nDuplicate #" + (i + 1) + ":");
            printTree(duplicates2.get(i));
        }
        System.out.println("Expected: 1 duplicate (leaf node [2])");
        System.out.println();

        System.out.println("=== Test Case 3: No Duplicates ===");
        System.out.println("Tree structure:");
        System.out.println("     1");
        System.out.println("    / \\");
        System.out.println("   2   3");
        System.out.println();

        // Build tree: [1,2,3]
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);

        GetDuplicateTrees solution3 = new GetDuplicateTrees();
        List<TreeNode> duplicates3 = solution3.getDuplicateTrees(root3);
        System.out.println("Found " + duplicates3.size() + " duplicate subtree(s)");
        System.out.println("Expected: 0 duplicates");
        System.out.println();

        System.out.println("=== Test Case 4: Mirror Duplicates ===");
        System.out.println("Tree structure:");
        System.out.println("       5");
        System.out.println("      / \\");
        System.out.println("     4   4");
        System.out.println("    /     \\");
        System.out.println("   3       3");
        System.out.println();

        // Build tree: [5,4,4,3,null,null,3]
        TreeNode root4 = new TreeNode(5);
        root4.left = new TreeNode(4);
        root4.left.left = new TreeNode(3);
        root4.right = new TreeNode(4);
        root4.right.right = new TreeNode(3);

        GetDuplicateTrees solution4 = new GetDuplicateTrees();
        List<TreeNode> duplicates4 = solution4.getDuplicateTrees(root4);
        System.out.println("Found " + duplicates4.size() + " duplicate subtree(s):");
        for (int i = 0; i < duplicates4.size(); i++) {
            System.out.println("\nDuplicate #" + (i + 1) + ":");
            printTree(duplicates4.get(i));
        }
        System.out.println("Expected: 1 duplicate (leaf node [3])");
        System.out.println();

        System.out.println("=== All Tests Completed ===");
    }
}

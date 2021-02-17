import java.sql.Timestamp;
import java.util.*;

public class StatedTreeNode {

    private TreeNode root;

    public StatedTreeNode() {
        root = new TreeNode();
        root.setAddDate(new Timestamp(new Date().getTime()));
    }

    public TreeNode getRoot() {
        return root;
    }


    public void main(String[] args) {
    }

    public String sayHello() {
        return "Hello";
    }

    public void addNode(String original, String input, TreeNode newNode) {
        TreeNode isParentExist = lookup(root, original);
        TreeNode isNewNodeExist = lookup(root, input);

        if (null == isParentExist && null == isNewNodeExist) {
            if (null == original || original.isEmpty()) {
                root = updateNode(root, input, newNode);
            }
        } else if (null != isParentExist && null == isNewNodeExist) {
            root = updateNode(isParentExist, input, newNode);
        } else if (null == isParentExist && null != isNewNodeExist) {
            if (dateCheck(isNewNodeExist, newNode)) {
                newNode.setRemoved(false);
                newNode.setRemoveDate(isNewNodeExist.getRemoveDate());
                root = updateNode(root, input, newNode);
            }
        } else if (null != isParentExist && null != isNewNodeExist) {
            if (dateCheck(isNewNodeExist, newNode)) {
                newNode.setRemoved(false);
                newNode.setRemoveDate(isNewNodeExist.getRemoveDate());
                root = updateNode(isParentExist, input, newNode);
            }
        }
    }

    public void removeNode(String remove, Date deleteTime) throws Exception {
        TreeNode removeNode = lookup(root, remove);
        if (null != removeNode) {
            if (removeNode.getAddDate().compareTo(deleteTime) < 0 && !removeNode.isRemoved()) {
                removeNode.setRemoved(true);
                removeNode.setRemoveDate(deleteTime);
                if (removeNode.getChildren().size() > 0) {
                    HashMap<String, TreeNode> remainingChildren = removeNode.getChildren();
                    removeNode.setChildren(new HashMap());
                    removeNode.getParent().getChildren().putAll(remainingChildren);
                }
                updateNode(removeNode.getParent(), remove, removeNode);
            }
        } else {
            throw new Exception("No Node found");
        }
    }

    private TreeNode updateNode(TreeNode root, String input, TreeNode newNode) {
        newNode.setParent(root);
        root.getChildren().put(input, newNode);

        if (null != root.getParent()) {
            for (Map.Entry<String, TreeNode> parent : root.getParent().getChildren().entrySet()) {
                if (parent.getValue().equals(root)) {
                    return updateNode(root.getParent(), parent.getKey(), root);
                }
            }
        }

        return root;
    }

    public TreeNode lookup(TreeNode root, String input) {
        for (Map.Entry<String, TreeNode> children : root.getChildren().entrySet()) {
            if (children.getKey().equals(input)) {
                return children.getValue();
            } else {
                if (children.getValue().getChildren().size() > 0) {
                    return lookup(children.getValue(), input);
                }
            }

        }
        return null;
    }

    private boolean dateCheck(TreeNode original, TreeNode newNode) {
        if (original.isRemoved()) {
            if (original.getRemoveDate().compareTo(newNode.getAddDate()) < 0) {
                return true;
            }

        } else {
            if (original.getAddDate().compareTo(newNode.getAddDate()) < 0) {
                return true;
            }
        }
        return false;
    }

    public TreeNode queryForParent(String input) {
        TreeNode result = lookup(root, input);
        if( null != result){
            return result.getParent();
        }
        return null;
    }

    public List<TreeNode> queryForChild(String input) {
        List<TreeNode> resultList = new ArrayList<>();
        if (input == null){
            for (Map.Entry<String, TreeNode> children : root.getChildren().entrySet()) {
                resultList.add(children.getValue());
            }
        } else {
            TreeNode result = lookup(root, input);

            if (null != result) {
                if (result.getChildren().size() > 0) {
                    for (Map.Entry<String, TreeNode> children : root.getChildren().entrySet()) {
                        resultList.add(children.getValue());
                    }
                }

            }
        }
        return resultList;
    }
}

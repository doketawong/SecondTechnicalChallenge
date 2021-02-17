import java.util.Date;
import java.util.HashMap;

public class TreeNode {

    private Date addDate;
    private Date removeDate;
    private TreeNode parent;
    private HashMap<String, TreeNode> children = new HashMap<>();
    private boolean isRemoved = false;

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(Date removeDate) {
        this.removeDate = removeDate;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public HashMap<String, TreeNode> getChildren() {
        return children;
    }

    public void setChildren(HashMap<String, TreeNode> children) {
        this.children = children;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }
}

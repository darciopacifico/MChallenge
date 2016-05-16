import java.util.LinkedList;
import java.util.List;

/**
 * TreeNode
 * Created by dpacif1 on 5/15/16.
 */
public class TreeNode {

    TreeNode parent;

    /**
     * findFirstCommonAncestor implementation:
     * <p/>
     * Estimate amount of time: proportional to Lg N comparisons of TreeNodes   (consider Lg = Log base 2)
     * Estimate amount of memory: (Lg N)*2 of total amount of TreeNodes
     *
     * @param that
     * @return first common ancestor or null, if no common parent was found.
     */
    TreeNode findFirstCommonAncestor(TreeNode that) {
        if (that == null) throw new NullPointerException();

        List<TreeNode> thisParentage = this.getParentage();
        List<TreeNode> thatParentage = that.getParentage();

        TreeNode lca = null;

        for (int i = 0; i < Math.min(thisParentage.size(), thatParentage.size()); i++) {
            TreeNode thisPar = thisParentage.get(i);
            TreeNode thatPar = thatParentage.get(i);

            if (thisPar.equals(thatPar)) {
                lca = thisPar;
            } else {
                break;
            }
        }

        return lca;
    }


    /**
     * Navigate to root, aggregating parents
     *
     * @return
     */
    private List<TreeNode> getParentage() {
        return getParentage(this, new LinkedList<TreeNode>());
    }


    /**
     * Navigate to root, aggregating parents
     *
     * @return
     */
    private List<TreeNode> getParentage(TreeNode treeNode, List<TreeNode> aggParentage) {

        if (treeNode != null) {
            aggParentage.add(0, treeNode);
            return getParentage(treeNode.parent, aggParentage);

        } else {
            return aggParentage;
        }

    }


}

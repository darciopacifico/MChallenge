import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * TreeNode test coverage
 * Created by dpacif1 on 5/15/16.
 */

public class TreeNodeTest {

    /**
     * ...............1............
     * ...........2.......3........
     * .........4...5...6...7......
     * ........8.9.................
     */
    TreeNode n1 = new TreeNode();
    TreeNode n2 = new TreeNode();
    TreeNode n3 = new TreeNode();
    TreeNode n4 = new TreeNode();
    TreeNode n5 = new TreeNode();
    TreeNode n6 = new TreeNode();
    TreeNode n7 = new TreeNode();
    TreeNode n8 = new TreeNode();
    TreeNode n9 = new TreeNode();

    TreeNode noParent = new TreeNode();

    @BeforeClass
    public void setUp() {
        n8.parent = n4;
        n9.parent = n4;
        n4.parent = n2;
        n2.parent = n1;
        n5.parent = n2;
        n6.parent = n3;
        n7.parent = n3;
        n3.parent = n1;
    }

    @Test
    public void testTreeNodeSuccess() {
        TreeNode lca_n4n7 = n4.findFirstCommonAncestor(n7);
        Assert.assertEquals(lca_n4n7, n1, "LCA expect to be n1");

        TreeNode lca_n8n3 = n8.findFirstCommonAncestor(n3);
        Assert.assertEquals(lca_n8n3, n1, "LCA expect to be n1");

        TreeNode lca_n8n5 = n8.findFirstCommonAncestor(n5);
        Assert.assertEquals(lca_n8n5, n2, "LCA expect to be n2");

        TreeNode lca_n8n2 = n8.findFirstCommonAncestor(n2);
        Assert.assertEquals(lca_n8n2, n2, "LCA expect to be n2");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testTreeNodeNullNode() {
        TreeNode lca_n4n7 = n4.findFirstCommonAncestor(null);
        Assert.assertEquals(lca_n4n7, n1, "LCA expect to be n1");
    }

    @Test
    public void testTreeNodeNoParentNode() {
        TreeNode lca_n4noParent = n4.findFirstCommonAncestor(noParent);
        Assert.assertEquals(lca_n4noParent, null, "LCA expect to be null");
    }

    @Test
    public void testTreeNodeNoParenNode2() {
        TreeNode lca_noParentn5 = noParent.findFirstCommonAncestor(n5);
        Assert.assertEquals(lca_noParentn5, null, "LCA expect to be null");
    }

}

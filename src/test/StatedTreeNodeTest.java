import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class StatedTreeNodeTest {

    @Test
    public void test() {
        Assertions.assertEquals("Hello", new StatedTreeNode().sayHello());
    }

    @Test
    public void addNodeCase() {
        StatedTreeNode statedTreeNode1 = new StatedTreeNode();
        Date date = new Date("Wed, 27 July 2021 13:30:00");
        TreeNode expectedNode = statedTreeNode1.getRoot();
        HashMap<String, TreeNode> childNode = new HashMap();
        TreeNode expectedChildNode = new TreeNode();
        expectedChildNode.setParent(expectedNode);
        childNode.put("apple", expectedChildNode);
        expectedNode.setChildren(childNode);

        StatedTreeNode statedTreeNode = new StatedTreeNode();
        TreeNode actualNode = new TreeNode();
        statedTreeNode.addNode(null, "apple", actualNode);
        Assertions.assertEquals(expectedNode.getChildren().get("apple").getAddDate(), statedTreeNode.lookup(statedTreeNode.getRoot(), "apple").getAddDate());
    }

    @Test
    public void addNodeToChildCase() {
        //expect
        StatedTreeNode statedTreeNode1 = new StatedTreeNode();
        Date date = new Date("Wed, 27 July 2021 13:30:00");
        TreeNode expectedNode = statedTreeNode1.getRoot();
        HashMap<String, TreeNode> childNode = new HashMap<>();
        TreeNode expectedChildNode = new TreeNode();
        expectedChildNode.setAddDate(date);
        TreeNode expectedChildNode2 = new TreeNode();
        expectedChildNode2.setAddDate(date);
        expectedChildNode2.setParent(expectedChildNode);
        HashMap<String, TreeNode> childLeafNode = new HashMap<>();
        childLeafNode.put("banana", expectedChildNode2);
        expectedChildNode.setChildren(childLeafNode);
        childNode.put("apple", expectedChildNode);
        expectedNode.setChildren(childNode);

        //Actual
        StatedTreeNode statedTreeNode = new StatedTreeNode();
        TreeNode actualNode = new TreeNode();
        actualNode.setAddDate(date);
        TreeNode actualNode2 = new TreeNode();
        actualNode2.setAddDate(date);
        statedTreeNode.addNode(null, "apple", actualNode);
        statedTreeNode.addNode("apple", "banana", actualNode2);

        Assertions.assertEquals(expectedNode.getChildren().get("apple").getAddDate(), statedTreeNode.lookup(statedTreeNode.getRoot(), "apple").getAddDate());
        Assertions.assertEquals(expectedNode.getChildren().get("apple").getChildren().get("banana").getAddDate(), statedTreeNode.lookup(statedTreeNode.getRoot(), "banana").getAddDate());
    }

    @Test
    public void addTwoNodeToParentCase() {
        StatedTreeNode statedTreeNode1 = new StatedTreeNode();
        Date date = new Date("Wed, 27 July 2021 13:30:00");
        TreeNode expectedNode = statedTreeNode1.getRoot();
        HashMap<String, TreeNode> childNode = new HashMap<>();
        TreeNode expectedChildNode = new TreeNode();
        expectedChildNode.setAddDate(date);
        expectedChildNode.setParent(expectedNode);
        TreeNode expectedChildNode2 = new TreeNode();
        expectedChildNode2.setAddDate(date);
        expectedChildNode2.setParent(expectedNode);
        childNode.put("apple", expectedChildNode);
        childNode.put("banana", expectedChildNode2);
        expectedNode.setChildren(childNode);

        StatedTreeNode statedTreeNode = new StatedTreeNode();
        TreeNode actualNode = new TreeNode();
        actualNode.setAddDate(date);
        TreeNode actualNode2 = new TreeNode();
        actualNode2.setAddDate(date);
        statedTreeNode.addNode(null, "apple", actualNode);
        statedTreeNode.addNode(null, "banana", actualNode);

        Assertions.assertEquals(expectedNode.getChildren().get("apple").getAddDate(), statedTreeNode.lookup(statedTreeNode.getRoot(), "apple").getAddDate());
        Assertions.assertEquals(expectedNode.getChildren().get("banana").getAddDate(), statedTreeNode.lookup(statedTreeNode.getRoot(), "banana").getAddDate());
    }

    @Test
    public void addTwoSameNodeToParentCase() {
        Date date = new Date("Wed, 27 July 2021 13:30:00");
        Date date1 = new Date("Wed, 27 July 2021 13:29:00");


        StatedTreeNode statedTreeNode = new StatedTreeNode();
        TreeNode actualNode = new TreeNode();
        actualNode.setAddDate(date);
        TreeNode actualNode2 = new TreeNode();
        actualNode2.setAddDate(date1);
        statedTreeNode.addNode(null, "banana", actualNode);
        statedTreeNode.addNode(null, "banana", actualNode2);

        Assertions.assertEquals(1, statedTreeNode.getRoot().getChildren().size());
        Assertions.assertEquals(date, statedTreeNode.lookup(statedTreeNode.getRoot(), "banana").getAddDate());
    }

    @Test
    public void addTwoSameNodeToParentUpdatedCase() {
        Date date = new Date("Wed, 27 July 2021 13:30:00");
        Date date1 = new Date("Wed, 27 July 2021 13:29:00");


        StatedTreeNode statedTreeNode = new StatedTreeNode();
        TreeNode actualNode = new TreeNode();
        actualNode.setAddDate(date1);
        TreeNode actualNode2 = new TreeNode();
        actualNode2.setAddDate(date);
        statedTreeNode.addNode(null, "banana", actualNode);
        statedTreeNode.addNode(null, "banana", actualNode2);

        Assertions.assertEquals(1, statedTreeNode.getRoot().getChildren().size());
        Assertions.assertEquals(date, statedTreeNode.lookup(statedTreeNode.getRoot(), "banana").getAddDate());
    }

    @Test
    public void addNodeToChildComplicatedCase() {
        //Expect
        StatedTreeNode statedTreeNode1 = new StatedTreeNode();
        Date date = new Date("Wed, 27 July 2021 13:30:00");
        Date date2 = new Date("Wed, 27 July 2021 13:31:00");
        Date date3 = new Date("Wed, 27 July 2021 13:32:00");
        Date date4 = new Date("Wed, 27 July 2021 13:33:00");
        TreeNode expectedNode = statedTreeNode1.getRoot();
        HashMap<String, TreeNode> childNode = new HashMap<>();
        HashMap<String, TreeNode> thirdLayerNode = new HashMap<>();
        HashMap<String, TreeNode> forthLayerNode = new HashMap<>();
        HashMap<String, TreeNode> secondLayerNode = new HashMap<>();

        TreeNode expectedChildNode = new TreeNode();
        TreeNode expectedChildNode2 = new TreeNode();
        TreeNode expectedChildNode3 = new TreeNode();
        TreeNode expectedChildNode4 = new TreeNode();

        expectedChildNode.setAddDate(date);
        expectedChildNode2.setAddDate(date2);
        expectedChildNode2.setParent(expectedChildNode);
        expectedChildNode3.setAddDate(date3);
        expectedChildNode4.setAddDate(date4);
        expectedChildNode3.setParent(expectedChildNode2);
        expectedChildNode4.setParent(expectedChildNode2);


        thirdLayerNode.put("orange", expectedChildNode3);
        forthLayerNode.put("pear", expectedChildNode4);
        secondLayerNode.put("banana", expectedChildNode2);
        expectedChildNode2.setChildren(thirdLayerNode);
        expectedChildNode3.setChildren(forthLayerNode);
        expectedChildNode.setChildren(secondLayerNode);
        childNode.put("apple", expectedChildNode);
        expectedNode.setChildren(childNode);

        //Actual
        StatedTreeNode statedTreeNode = new StatedTreeNode();
        TreeNode actualNode = new TreeNode();
        actualNode.setAddDate(date);
        TreeNode actualNode2 = new TreeNode();
        actualNode2.setAddDate(date2);
        TreeNode actualNode3 = new TreeNode();
        actualNode3.setAddDate(date3);
        TreeNode actualNode4 = new TreeNode();
        actualNode4.setAddDate(date4);
        statedTreeNode.addNode(null, "apple", actualNode);
        statedTreeNode.addNode("apple", "banana", actualNode2);
        statedTreeNode.addNode("banana", "orange", actualNode3);
        statedTreeNode.addNode("orange", "pear", actualNode4);

        Assertions.assertEquals(expectedNode.getChildren().get("apple").getAddDate(), statedTreeNode.lookup(statedTreeNode.getRoot(), "apple").getAddDate());
        Assertions.assertEquals(expectedNode.getChildren().get("apple").getChildren().get("banana").getAddDate(),statedTreeNode.lookup(statedTreeNode.getRoot(), "banana").getAddDate());
        Assertions.assertEquals(expectedNode.getChildren().get("apple").getChildren().get("banana").getChildren().get("orange").getAddDate(), statedTreeNode.lookup(statedTreeNode.getRoot(), "orange").getAddDate());
        Assertions.assertEquals(expectedNode.getChildren().get("apple").getChildren().get("banana").getChildren().get("orange").getChildren().get("pear").getAddDate(), statedTreeNode.lookup(statedTreeNode.getRoot(), "pear").getAddDate());


    }

    @Test
    public void addNodeToChildComplicated2Case() {
        //Expect
        StatedTreeNode statedTreeNode1 = new StatedTreeNode();
        Date date = new Date("Wed, 27 July 2021 13:30:00");
        Date date2 = new Date("Wed, 27 July 2021 13:31:00");
        Date date3 = new Date("Wed, 27 July 2021 13:32:00");
        Date date4 = new Date("Wed, 27 July 2021 13:33:00");
        TreeNode expectedNode = statedTreeNode1.getRoot();
        HashMap<String, TreeNode> childNode = new HashMap<>();
        HashMap<String, TreeNode> thirdLayerNode = new HashMap<>();
        HashMap<String, TreeNode> childLeafNode = new HashMap<>();

        TreeNode expectedChildNode = new TreeNode();
        TreeNode expectedChildNode2 = new TreeNode();
        TreeNode expectedChildNode3 = new TreeNode();
        TreeNode expectedChildNode4 = new TreeNode();

        expectedChildNode.setAddDate(date);
        expectedChildNode2.setAddDate(date2);
        expectedChildNode2.setParent(expectedChildNode);
        expectedChildNode3.setAddDate(date3);
        expectedChildNode4.setAddDate(date4);
        expectedChildNode3.setParent(expectedChildNode2);
        expectedChildNode4.setParent(expectedChildNode2);


        thirdLayerNode.put("orange", expectedChildNode3);
        thirdLayerNode.put("pear", expectedChildNode4);
        childLeafNode.put("banana", expectedChildNode2);
        expectedChildNode2.setChildren(thirdLayerNode);
        expectedChildNode.setChildren(childLeafNode);
        childNode.put("apple", expectedChildNode);
        expectedNode.setChildren(childNode);

        //Actual
        StatedTreeNode statedTreeNode = new StatedTreeNode();
        TreeNode actualNode = new TreeNode();
        actualNode.setAddDate(date);
        TreeNode actualNode2 = new TreeNode();
        actualNode2.setAddDate(date2);
        TreeNode actualNode3 = new TreeNode();
        actualNode3.setAddDate(date3);
        TreeNode actualNode4 = new TreeNode();
        actualNode4.setAddDate(date4);
        statedTreeNode.addNode(null, "apple", actualNode);
        statedTreeNode.addNode("apple", "banana", actualNode2);
        statedTreeNode.addNode("banana", "orange", actualNode3);
        statedTreeNode.addNode("banana", "pear", actualNode4);

        Assertions.assertEquals(expectedNode.getChildren().get("apple").getAddDate(), statedTreeNode.lookup(statedTreeNode.getRoot(), "apple").getAddDate());
        Assertions.assertEquals(expectedNode.getChildren().get("apple").getChildren().get("banana").getAddDate(), statedTreeNode.lookup(statedTreeNode.getRoot(), "banana").getAddDate());
        Assertions.assertEquals(expectedNode.getChildren().get("apple").getChildren().get("banana").getChildren().get("pear").getAddDate(), statedTreeNode.lookup(statedTreeNode.getRoot(), "pear").getAddDate());
        Assertions.assertEquals(expectedNode.getChildren().get("apple").getChildren().get("banana").getChildren().get("orange").getAddDate(), statedTreeNode.lookup(statedTreeNode.getRoot(), "orange").getAddDate());

    }

    @Test
    public void removeNodeAtRootCase() throws Exception {
        //Expect
        Date date = new Date("Wed, 27 July 2021 13:30:00");
        Date date1 = new Date("Wed, 27 July 2021 13:31:00");

        //Actual
        StatedTreeNode statedTreeNode = new StatedTreeNode();
        TreeNode actualNode = new TreeNode();
        actualNode.setAddDate(date);
        TreeNode actualNode2 = new TreeNode();
        actualNode2.setAddDate(date);
        statedTreeNode.addNode(null, "apple", actualNode);
        statedTreeNode.addNode(null, "banana", actualNode);
        statedTreeNode.removeNode("banana", date1);

        Assertions.assertEquals(true, statedTreeNode.lookup(statedTreeNode.getRoot(), "banana").isRemoved());
    }


    @Test
    public void removeNodeAtChildCase() throws Exception {
        //Expect
        Date date = new Date("Wed, 27 July 2021 13:30:00");
        Date date1 = new Date("Wed, 27 July 2021 13:31:00");

        //Actual
        StatedTreeNode statedTreeNode = new StatedTreeNode();
        TreeNode actualNode = new TreeNode();
        actualNode.setAddDate(date);
        TreeNode actualNode2 = new TreeNode();
        actualNode2.setAddDate(date);
        statedTreeNode.addNode(null, "apple", actualNode);
        statedTreeNode.addNode("apple", "banana", actualNode2);
        statedTreeNode.removeNode("apple", date1);

        Assertions.assertEquals(true, statedTreeNode.lookup(statedTreeNode.getRoot(), "apple").isRemoved());
        Assertions.assertNotNull(statedTreeNode.lookup(statedTreeNode.getRoot(), "banana"));
    }


    @Test
    public void removeNodeAtChildWithMultipleChildrenCase() throws Exception {
        //Expect
        Date date = new Date("Wed, 27 July 2021 13:30:00");
        Date date2 = new Date("Wed, 27 July 2021 13:31:00");
        Date date3 = new Date("Wed, 27 July 2021 13:32:00");
        Date date4 = new Date("Wed, 27 July 2021 13:33:00");
        Date date5 = new Date("Wed, 27 July 2021 13:34:00");
        Date date6 = new Date("Wed, 27 July 2021 13:35:00");

        //Actual
        StatedTreeNode statedTreeNode = new StatedTreeNode();
        TreeNode actualNode = new TreeNode();
        actualNode.setAddDate(date);
        TreeNode actualNode2 = new TreeNode();
        actualNode2.setAddDate(date2);
        TreeNode actualNode3 = new TreeNode();
        actualNode3.setAddDate(date3);
        TreeNode actualNode4 = new TreeNode();
        actualNode4.setAddDate(date4);
        statedTreeNode.addNode(null, "apple", actualNode);
        statedTreeNode.addNode("apple", "banana", actualNode2);
        statedTreeNode.addNode("banana", "orange", actualNode3);
        statedTreeNode.addNode("banana", "pear", actualNode4);
        statedTreeNode.removeNode("banana", date5);

        Assertions.assertEquals(true, statedTreeNode.lookup(statedTreeNode.getRoot(), "banana").isRemoved());
        Assertions.assertEquals(date5, statedTreeNode.lookup(statedTreeNode.getRoot(), "banana").getRemoveDate());

    }

    @Test
    public void reAddNodeAfterDelete() throws Exception {
        //Expect
        Date date = new Date("Wed, 27 July 2021 13:30:00");
        Date date2 = new Date("Wed, 27 July 2021 13:31:00");
        Date date3 = new Date("Wed, 27 July 2021 13:32:00");
        Date date4 = new Date("Wed, 27 July 2021 13:33:00");
        Date date5 = new Date("Wed, 27 July 2021 13:34:00");
        Date date6 = new Date("Wed, 27 July 2021 13:35:00");

        //Actual
        StatedTreeNode statedTreeNode = new StatedTreeNode();
        TreeNode actualNode = new TreeNode();
        actualNode.setAddDate(date);
        TreeNode actualNode2 = new TreeNode();
        actualNode2.setAddDate(date2);
        TreeNode actualNode3 = new TreeNode();
        actualNode3.setAddDate(date3);
        TreeNode actualNode4 = new TreeNode();
        actualNode4.setAddDate(date4);
        statedTreeNode.addNode(null, "apple", actualNode);
        statedTreeNode.addNode("apple", "banana", actualNode2);
        statedTreeNode.addNode("banana", "orange", actualNode3);
        statedTreeNode.addNode("banana", "pear", actualNode4);
        statedTreeNode.removeNode("banana", date5);
        TreeNode actualNode5 = new TreeNode();
        actualNode5.setAddDate(date6);
        statedTreeNode.addNode("apple", "banana", actualNode5);

        Assertions.assertEquals(false, statedTreeNode.lookup(statedTreeNode.getRoot(), "banana").isRemoved());
        Assertions.assertEquals(date5, statedTreeNode.lookup(statedTreeNode.getRoot(), "banana").getRemoveDate());
        Assertions.assertEquals(date6, statedTreeNode.lookup(statedTreeNode.getRoot(), "banana").getAddDate());

    }

    @Test
    public void AddNodeThatIsNotExist()  {
        //Expect
        Date date = new Date("Wed, 27 July 2021 13:30:00");

        //Actual
        StatedTreeNode statedTreeNode = new StatedTreeNode();
        TreeNode actualNode = new TreeNode();
        actualNode.setAddDate(date);
        statedTreeNode.addNode("not exist", "apple", actualNode);

        Assertions.assertEquals(null, statedTreeNode.lookup(statedTreeNode.getRoot(), "apple"));
    }

    @Test
    public void removeNodeThatIsNotExist() throws Exception {
        //Expect
        Date date = new Date("Wed, 27 July 2021 13:30:00");

        //Actual
        StatedTreeNode statedTreeNode = new StatedTreeNode();

        Assertions.assertThrows(Exception.class, () -> {
            statedTreeNode.removeNode("not exist", date);
        });
    }

    @Test
    public void queryForParent() throws Exception {
        //Expect
        Date date = new Date("Wed, 27 July 2021 13:30:00");

        //Actual
        StatedTreeNode statedTreeNode = new StatedTreeNode();
        TreeNode actualNode = new TreeNode();
        actualNode.setAddDate(date);
        TreeNode actualNode2 = new TreeNode();
        actualNode2.setAddDate(date);
        statedTreeNode.addNode(null, "apple", actualNode);
        statedTreeNode.addNode("apple", "banana", actualNode2);
        TreeNode actualResult = statedTreeNode.queryForParent("banana");

        Assertions.assertEquals(date, actualResult.getAddDate());

    }

    @Test
    public void queryForChildren() {
        //Expect
        Date date = new Date("Wed, 27 July 2021 13:30:00");

        //Actual
        StatedTreeNode statedTreeNode = new StatedTreeNode();
        TreeNode actualNode = new TreeNode();
        actualNode.setAddDate(date);
        TreeNode actualNode2 = new TreeNode();
        actualNode2.setAddDate(date);
        statedTreeNode.addNode(null, "banana", actualNode);
        statedTreeNode.addNode(null, "apple", actualNode2);
        List<TreeNode> actualResultList = statedTreeNode.queryForChild(null);

        Assertions.assertEquals(2, actualResultList.size());
    }
}
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dpacif1 on 5/15/16.
 */
public class SeatingManagerTest {


    SeatingManager sm;

    Table t1_2 = new Table(2);
    Table t2_2 = new Table(2);
    Table t3_4 = new Table(4);
    Table t4_4 = new Table(4);
    Table t5_8 = new Table(8);
    Table t6_8 = new Table(8);


    @BeforeClass
    public void setUp() {
        List<Table> tables = new ArrayList<Table>();

        tables.add(t1_2);
        tables.add(t2_2);
        tables.add(t3_4);
        tables.add(t4_4);
        tables.add(t5_8);
        tables.add(t6_8);

        this.sm = new SeatingManager(tables);
    }

    @Test
    public void testAllocationGroups() {


        CustomerGroup cg1 = new CustomerGroup(1);
        this.sm.arrives(cg1);
        Assert.assertEquals(sm.locate(cg1).size,2);

        CustomerGroup cg2 = new CustomerGroup(1);
        this.sm.arrives(cg2);
        Assert.assertEquals(sm.locate(cg2).size, 2);


        CustomerGroup cg3 = new CustomerGroup(1);
        this.sm.arrives(cg3);
        Assert.assertEquals(sm.locate(cg3).size, 4);

        CustomerGroup cg4 = new CustomerGroup(1);
        this.sm.arrives(cg4);
        Assert.assertEquals(sm.locate(cg4).size, 4);


        CustomerGroup cg5 = new CustomerGroup(4);
        this.sm.arrives(cg5);
        Assert.assertEquals(sm.locate(cg5).size, 8);

        CustomerGroup cg6 = new CustomerGroup(8);
        this.sm.arrives(cg6);
        Assert.assertEquals(sm.locate(cg6).size, 8);


        //three groups waiting
        CustomerGroup cg7 = new CustomerGroup(6);
        this.sm.arrives(cg7);
        Assert.assertEquals(sm.locate(cg7), null);

        CustomerGroup cg8 = new CustomerGroup(7);
        this.sm.arrives(cg8);
        Assert.assertEquals(sm.locate(cg7), null);

        CustomerGroup cg9 = new CustomerGroup(4);
        this.sm.arrives(cg9);
        Assert.assertEquals(sm.locate(cg7), null);


        this.sm.leaves(cg4);
        Assert.assertEquals(sm.locate(cg9).size, 4);

        Assert.assertEquals(sm.locate(cg7), null);

        Assert.assertEquals(sm.locate(cg8), null);


        //cg8 leaves the restaurant, booth not allocated yet
        this.sm.leaves(cg7);

        Assert.assertEquals(sm.locate(cg7), null);
        Assert.assertEquals(sm.locate(cg8), null);

        //free one 8 chairs table, cg8 will be allocated
        this.sm.leaves(cg6);

        Assert.assertEquals(sm.locate(cg7), null);
        Assert.assertEquals(sm.locate(cg8).size, 8);

        this.sm.leaves(cg1); // the cg7 is a 7 person group... should wait for a larger table

        Assert.assertEquals(sm.locate(cg7), null);

    }


}

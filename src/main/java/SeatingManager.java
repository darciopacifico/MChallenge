import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Seating Manager implementation
 */
public class SeatingManager {

    private int maxGroupSize = 0;

    //table pq
    private PriorityQueue<Table> pqTable = new PriorityQueue<Table>(new Comparator<Table>() {
        public int compare(Table t1, Table t2) {

            if(t1.size>t2.size){
                return 1;
            }else if (t1.size<t2.size){
                return -1;
            }else{
                return 0;
            }

        }
    });

    //restaurant queue
    private Queue<CustomerGroup> restaurantQueue = new LinkedList<CustomerGroup>();

    //allocation map
    private Map<CustomerGroup, Table> mapTable = new HashMap<CustomerGroup, Table>();

    /* Constructor */
    public SeatingManager(List<Table> tables) {
        if(tables==null) throw new NullPointerException();

        //set the maximum group size for the restaurant
        Consumer<Table> consumer = new Consumer<Table>() {
            public void accept(Table table) {
                if(table.size > maxGroupSize){
                    maxGroupSize = table.size;
                }
            }
        };

        tables.forEach(consumer);

        this.pqTable.addAll(tables);

        this.pqTable.forEach(new Consumer<Table>() {
            public void accept(Table table) {
                System.out.println(table.size);
            }
        });

    }

    /* Group arrives and wants to be seated. */
    public void arrives(CustomerGroup group) {
        if (group==null) throw new NullPointerException();
        if (!allocateCustomerGroups(group))
            restaurantQueue.add(group);
    }

    /* Whether seated or not, the group leaves the restaurant. */
    public void leaves(CustomerGroup group) {
        if (group==null) throw new NullPointerException();
        Table table = this.mapTable.get(group);

        if (table != null){
            if (!allocateTable(table))
                pqTable.add(table);
        }else{
            restaurantQueue.remove(group);
        }
    }

    /* Return the table at which the group is seated, or null if
    they are not seated (whether they're waiting or already left). */
    public Table locate(CustomerGroup group) {
        if (group==null) throw new NullPointerException();
        return this.mapTable.get(group);
    }

    /**
     * @param table
     * @return
     */
    private boolean allocateTable(Table table) {
        AllocateTablePredicate predicate = new AllocateTablePredicate(table);
        return this.restaurantQueue.removeIf(predicate);
    }


    private boolean allocateCustomerGroups(CustomerGroup group) {
        AllocateCustomerPredicate predicate = new AllocateCustomerPredicate(group);
        return pqTable.removeIf(predicate);
    }


    /**
     * Predicate rule for removing an
     */
    class AllocateTablePredicate implements Predicate<CustomerGroup> {
        Table table;
        boolean found = false;

        public AllocateTablePredicate(Table table) {
            this.table = table;
        }

        public boolean test(CustomerGroup customerGroup) {
            if (!found && table.size >= customerGroup.size) {
                mapTable.put(customerGroup, table);
                found=true;
                return true;
            }else{
                return false;
            }
        }
    }


    /**
     * Predicate rule for removing an
     */
    class AllocateCustomerPredicate implements Predicate<Table> {
        CustomerGroup customerGroup;
        boolean found = false;

        public AllocateCustomerPredicate(CustomerGroup customerGroup) {
            this.customerGroup = customerGroup;
        }

        public boolean test(Table table) {

            if (!found && table.size >= customerGroup.size) {
                mapTable.put(customerGroup, table);
                found = true;
                return true;
            }else{
                return false;
            }
        }
    }



}

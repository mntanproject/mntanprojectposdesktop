import com.mntanproject.pos.MyObjectBox;
import com.mntanproject.pos.customer.Customer;
import com.mntanproject.pos.database.ObjectBoxDB;
import com.mntanproject.pos.supplier.Supplier;
import io.objectbox.Box;
import mntanproject.core.server.MnServer;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ObjectBoxDB.init();// objectBoxDB = new ObjectBoxDB();
        MnServer server = new MnServer(9000);
        HashMap<String,String> routes = new HashMap<String,String>();
        routes.put("supplier","com.mntanproject.pos.supplier.SupplierApi");
        routes.put("customer","com.mntanproject.pos.customer.CustomerApi");

        server.getRegisteredRoute().setRegisteredRoutes(routes);
        server.startServer();

        /*Box<Supplier> box = ObjectBoxDB.get().boxFor(Supplier.class);
        Box<Customer> boxCustomer = ObjectBoxDB.get().boxFor(Customer.class);


        Supplier supplier = new Supplier();
        supplier.name = "Martin";
        supplier.company = "Comp";
        supplier.email  = "martin@mntanproject.com";
        box.put(supplier);

        Customer customer = new Customer();
        customer.name = "Custname";
        customer.company = "custcomp";
        boxCustomer.put(customer);

        System.out.println(box.count() + " supplier in ObjectBox database:");
        System.out.println(boxCustomer.count() + " customer in ObjectBox database:");

        for (Supplier note : box.getAll()) {
            System.out.println(note);
        }*/

        //ObjectBoxDB.close();

    }
}

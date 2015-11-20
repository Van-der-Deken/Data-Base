package Interface;

/**
 * Created by Y500 on 16.11.2015.
 */
abstract class DBConstants {
    //Data base configuration
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/";
    public static final String DATABASE_NAME = "AutoService";
    //User`s types
    enum TYPE {ADMIN,WORKER,CLIENT, UNDEF};
    //Tables real names
    public static final String[] realTablesNames = {"clients", "details", "orderdetails", "orders", "servicedetails", "services", "workers", "workerservices"};
    //Tables descriptions
    public static TableDescription[] descriptions = {TablesConfiguration.clients,
                                                    TablesConfiguration.details,
                                                    TablesConfiguration.orderDetails,
                                                    TablesConfiguration.orders,
                                                    TablesConfiguration.serviceDetails,
                                                    TablesConfiguration.services,
                                                    TablesConfiguration.workers,
                                                    TablesConfiguration.workerServices};
}

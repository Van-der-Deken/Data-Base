package Interface;

/**
 * Created by Y500 on 16.11.2015.
 */
abstract class TablesConfiguration {
    //Tables constants
    public static final String SERIAL = "SERIAL";
    public static final String VARCHAR = "VARCHAR";
    public static final String INT= "INT";
    /** Tables descriptions **/
    //Clients
    public static final String[] clientsFieldsWithTypes = {"id SERIAL", "name VARCHAR(20)", "telephone INT", "address VARCHAR(40)", "discount INT"};
    public static final String[] clientsTypes = {VARCHAR, INT, VARCHAR, INT};
    public static TableDescription clients = new TableDescription(clientsFieldsWithTypes, clientsTypes);
    //Workers
    public static final String[] workersFieldsWithTypes = {"id SERIAL", "name VARCHAR(20)", "position VARCHAR(30)", "salary INT"};
    public static final String[] workersTypes = {VARCHAR, VARCHAR, INT};
    public static TableDescription workers = new TableDescription(workersFieldsWithTypes, workersTypes);
    //Details
    public static final String[] detailsFieldsWithTypes = {"id SERIAL", "name VARCHAR(20)", "amount INT"};
    public static final String[] detailsTypes = {VARCHAR, INT};
    public static TableDescription details = new TableDescription(detailsFieldsWithTypes, detailsTypes);
    //Services
    public static final String[] servicesFieldsWithTypes = {"id SERIAL", "name VARCHAR(20)", "price INT", "duration INT"};
    public static final String[] servicesTypes = {VARCHAR, INT, INT};
    public static TableDescription services = new TableDescription(servicesFieldsWithTypes, servicesTypes);
    //Orders
    public static final String[] ordersFieldsWithTypes = {"client_id BIGINT UNSIGNED NOT NULL", "service_id BIGINT UNSIGNED NOT NULL"};
    public static final String[] ordersForeignKeys = {"FOREIGN KEY (client_id) REFERENCES Clients (id)","FOREIGN KEY (service_id) REFERENCES Services (id)"};
    public static final String[] ordersTypes = {SERIAL, SERIAL};
    public static final TableDescription[] ordersRefTables = {clients, services};
    public static TableDescription orders = new TableDescription(ordersFieldsWithTypes, ordersTypes)
            .setForeignKeys(ordersForeignKeys).setRefTables(ordersRefTables);
    //Service details
    public static final String[] serviceDetailsFieldsWithTypes = {"service_id BIGINT UNSIGNED NOT NULL", "detail_id BIGINT UNSIGNED NOT NULL"};
    public static final String[] serviceDetailsForeignKeys = {"FOREIGN KEY (service_id) REFERENCES Services (id)","FOREIGN KEY (detail_id) REFERENCES Details (id)"};
    public static final String[] serviceDetailsTypes = {SERIAL, SERIAL};
    public static final TableDescription[] serviceDetailsRefTables = {services, details};
    public static TableDescription serviceDetails = new TableDescription(serviceDetailsFieldsWithTypes, serviceDetailsTypes)
            .setForeignKeys(serviceDetailsForeignKeys).setRefTables(serviceDetailsRefTables);
    //Worker services
    public static final String[] workerServicesFieldsWithTypes = {"worker_id BIGINT UNSIGNED NOT NULL", "service_id BIGINT UNSIGNED NOT NULL"};
    public static final String[] workerServicesForeignKeys = {"FOREIGN KEY (worker_id) REFERENCES Workers (id)","FOREIGN KEY (service_id) REFERENCES Services (id)"};
    public static final String[] workerServicesTypes = {SERIAL, SERIAL};
    public static final TableDescription[] workerServicesRefTables = {workers, services};
    public static TableDescription workerServices = new TableDescription(workerServicesFieldsWithTypes, workerServicesTypes)
            .setForeignKeys(workerServicesForeignKeys).setRefTables(workerServicesRefTables);
    //Order details
    public static final String[] orderDetailsFieldsWithTypes = {"detail_id BIGINT UNSIGNED NOT NULL", "amount INT"};
    public static final String[] orderDetailsForeignKeys = {"FOREIGN KEY (detail_id) REFERENCES Details (id)"};
    public static final String[] orderDetailsTypes = {SERIAL, INT};
    public static final TableDescription[] orderDetailsRefTables = {details};
    public static TableDescription orderDetails = new TableDescription(orderDetailsFieldsWithTypes, orderDetailsTypes)
            .setForeignKeys(orderDetailsForeignKeys).setRefTables(orderDetailsRefTables);
}

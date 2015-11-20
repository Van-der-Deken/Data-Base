package Interface;

/**
 * Created by Y500 on 17.11.2015.
 */
abstract class RequestsConfiguration {
    //Tables, which can see user with current permission (for "Show table content" request)
    public static TableDescription[] adminTables = DBConstants.descriptions;
    public static TableDescription[] workerTables = {TablesConfiguration.orderDetails, TablesConfiguration.details};
    public static TableDescription[] clientTables = {TablesConfiguration.services};
    //Requests (constructed by default constructor)
    public static ParameterizedRequest tableContent = new ParameterizedRequest();
    //Requests, which can perform user with current permission
    public static final RequestDescription[] adminRequests = {tableContent};
}

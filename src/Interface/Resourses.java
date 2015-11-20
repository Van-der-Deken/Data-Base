package Interface;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Vector;

/**
 * Created by Y500 on 14.11.2015.
 */
abstract class Resourses {

    public static String userName = "Test";
    public static DBConstants.TYPE userType = DBConstants.TYPE.ADMIN;

    public static Connection connection;
    public static Statement statement;
}

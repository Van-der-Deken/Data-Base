package Interface;

import java.sql.*;
import java.util.Vector;

/**
 * Created by Y500 on 15.11.2015.
 */
abstract class SQLActions {
    public static boolean createTable(String title, TableDescription description) {
        String tableDescription = "CREATE TABLE IF NOT EXISTS "+title+"(";
        String[] fieldsWithTypes = description.getColumnsDefinition();
        String[] foreignKeys = description.getForeignKeys();
        for(int i = 0; i < fieldsWithTypes.length; ++i) {
            tableDescription += i+1 == fieldsWithTypes.length ? fieldsWithTypes[i] : fieldsWithTypes[i]+",";
        }
        for(int i = 0; i < foreignKeys.length; ++i) {
            if(i == 0) {
                tableDescription += ",";
            }
            tableDescription += foreignKeys[i] +
                    (i+1 == foreignKeys.length ? " ON DELETE RESTRICT ON UPDATE CASCADE" : " ON DELETE RESTRICT ON UPDATE CASCADE,");
        }
        tableDescription += ")";
        try {
            Resourses.statement.executeUpdate(tableDescription);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean connectToDB(String login, String password) {
        try {
            Class.forName(DBConstants.JDBC_DRIVER);
            Resourses.connection = DriverManager.getConnection(DBConstants.DB_URL, login, password);
            if(Resourses.connection != null) {
                Resourses.statement = Resourses.connection.createStatement();
                Resourses.statement.executeUpdate("USE "+DBConstants.DATABASE_NAME);
            }
        } catch (ClassNotFoundException e1) {
            return false;
        } catch (SQLException e1) {
            return false;
        }
        return true;
    }

    public static Vector<String> getTablesfromDB() {
        Vector<String> tables = new Vector<String>();
        try {
            DatabaseMetaData md = Resourses.connection.getMetaData();
            ResultSet rs = md.getTables (DBConstants.DATABASE_NAME, null, "%", null);
            while (rs.next())
                tables.add(rs.getString(3));
            rs.close ();
        } catch (SQLException e1) {
            return null;
        }
        return tables;
    }

    public static boolean configureDB() {
        boolean retcode = true;
        try {
            Class.forName(DBConstants.JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DBConstants.DB_URL, "gui", "interface");
            if(connection != null) {
                Resourses.statement = connection.createStatement();
                Resourses.statement.executeUpdate("USE "+ DBConstants.DATABASE_NAME);
                for(int i = 0; i < DBConstants.realTablesNames.length; ++i) {
                    if(!SQLActions.createTable(DBConstants.realTablesNames[i],DBConstants.descriptions[i])) {
                        retcode = false;
                        System.out.println("Error with "+DBConstants.realTablesNames[i]);
                    }
                }
            } else {
                System.out.println("Connection failed");
                return false;
            }
            String[] pattern = new String[0];
            if(retcode) {
                for(int i = 0; i < DBConstants.realTablesNames.length; ++i) {
                    String request = "SHOW FIELDS FROM "+DBConstants.realTablesNames[i];
                    ResultSet result = Resourses.statement.executeQuery(request);
                    Vector<String> fields = new Vector<String>();
                    while (result.next()) {
                        fields.add(result.getString(1));
                    }
                    String[] realTitles = fields.toArray(pattern);
                    DBConstants.descriptions[i].setTitle(DBConstants.realTablesNames[i]);
                    DBConstants.descriptions[i].setColumnsTitles(realTitles);
                }
            }
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
            Resourses.statement = null;
            return false;
        } catch (SQLException e1) {
            e1.printStackTrace();
            Resourses.statement = null;
            return false;
        }
        Resourses.statement = null;
        return retcode;
    }

    public static boolean insertRow(String tableName, String[] fields, String[] values) {
        String request = "INSERT INTO " + tableName + " (";
        for(int i = 0; i < fields.length; ++i) {
            request += (i+1 == fields.length) ? fields[i]+") VALUES (" : fields[i]+",";
        }
        for(int i = 0; i < values.length; ++i) {
            request += (i+1 == values.length) ? "'"+values[i]+"')" : "'"+values[i]+"',";
        }
        try {
            Resourses.statement.executeUpdate(request);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Vector<String> getColumn(TableDescription description, String columnName, boolean distinct) {
        String request = distinct ? "SELECT DISTINCT " : "SELECT ";
        request += columnName+" FROM "+description.getTitle();
        Vector<String> output = new Vector<String>();
        try {
            ResultSet result = Resourses.statement.executeQuery(request);
            while (result.next()) {
                output.add(result.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return output;
    }

    public static Vector<String[]> getSpecificDatafromTable(TableDescription description, String[] parameters) {
        String request = "SELECT ";
        String[] columns = description.getColumnsTitles();
        for(int i = 0; i < columns.length; ++i) {
            request += (i+1 == columns.length) ? columns[i]+" FROM " : columns[i]+",";
        }
        request += description.getTitle();
        String requestParameters = "";
        int parametersAdded = 0;
        for(int i = 0; i < columns.length; ++i) {
            if(!parameters[i].equals("Undefined")) {
                if(description.getColumnsDefinition()[i].contains("VARCHAR")) {
                    requestParameters += (parametersAdded > 0) ? " AND "+columns[i]+"='"+parameters[i]+"'" : columns[i]+"='"+parameters[i]+"'";
                } else {
                    requestParameters += (parametersAdded > 0) ? " AND "+columns[i]+"="+parameters[i] : columns[i]+"="+parameters[i];
                }
                ++parametersAdded;
            }
        }
        request += (requestParameters.isEmpty()) ? requestParameters : " WHERE "+requestParameters;
        Vector<String[]> results = new Vector<String[]>();
        try {
            ResultSet result = Resourses.statement.executeQuery(request);
            while (result.next()) {
                Vector<String> row = new Vector<String>();
                for(String current : columns) {
                    row.add(result.getString(current));
                }
                results.add(row.toArray(new String[0]));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return results;
    }
}

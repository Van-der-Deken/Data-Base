package Interface;

import java.util.Vector;

/**
 * Created by Y500 on 16.11.2015.
 */
abstract class Functions {
    public static boolean checkTables() {
        Vector<String> tables = SQLActions.getTablesfromDB();
        if(tables != null) {
            for(String current : DBConstants.realTablesNames) {
                if(!tables.contains(current)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean isInt(String str) {
        try {
            int i = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException num) {
            return false;
        }
    }

    public static void setGUIGemini() {
        //Tables names
        GUIConfiguration.GUIGemini.put("clients","Clients");
        GUIConfiguration.GUIGemini.put("details","Details");
        GUIConfiguration.GUIGemini.put("orderdetails","Order details");
        GUIConfiguration.GUIGemini.put("orders","Orders");
        GUIConfiguration.GUIGemini.put("servicedetails","Service details");
        GUIConfiguration.GUIGemini.put("services","Services");
        GUIConfiguration.GUIGemini.put("workers","Workers");
        GUIConfiguration.GUIGemini.put("workerservices","Worker services");
        //Tables fields
        GUIConfiguration.GUIGemini.put("id","ID");
        GUIConfiguration.GUIGemini.put("name","Name");
        GUIConfiguration.GUIGemini.put("telephone","Telephone");
        GUIConfiguration.GUIGemini.put("address","Address");
        GUIConfiguration.GUIGemini.put("discount", "Discount");
        GUIConfiguration.GUIGemini.put("position","Position");
        GUIConfiguration.GUIGemini.put("salary","Salary");
        GUIConfiguration.GUIGemini.put("amount","Amount");
        GUIConfiguration.GUIGemini.put("price","Price");
        GUIConfiguration.GUIGemini.put("duration","Duration");
        GUIConfiguration.GUIGemini.put("service_id","Service ID");
        GUIConfiguration.GUIGemini.put("client_id","Client ID");
        GUIConfiguration.GUIGemini.put("detail_id","Detail ID");
        GUIConfiguration.GUIGemini.put("worker_id","Worker ID");
    }
}

package Interface;

import java.util.Vector;

/**
 * Created by Y500 on 20.11.2015.
 */
public class ParameterizedRequest extends RequestDescription {
    private TableDescription[] allowedTables;
    private int index;

    public ParameterizedRequest() {
        super("Show table content");
        parametersCount = 1;
        updatedParametersCount = 0;
        mutable = true;
    }

    public Vector<String[]> getParametersItems() {
        String[] titles = new String[allowedTables.length];
        for(int i = 0; i < allowedTables.length; ++i) {
            titles[i] = allowedTables[i].getGuiTitle();
        }
        Vector<String[]> output = new Vector<String[]>();
        output.add(titles);
        return output;
    }

    public Vector<String[]> getUpdatedParametersItems() {
        Vector<String[]> items = getParametersItems();
        String[] titles = allowedTables[index].getColumnsTitles();
        for(int i = 0; i < titles.length; ++i) {
            Vector<String> item = SQLActions.getColumn(allowedTables[index], titles[i], true);
            item.add("Undefined");
            items.add(item.toArray(new String[0]));
        }
        return items;
    }

    public void setUserType(DBConstants.TYPE type) {
        super.setUserType(type);
        switch (userType) {
            case ADMIN:
                allowedTables = RequestsConfiguration.adminTables;
                break;
            case WORKER:
                allowedTables = RequestsConfiguration.workerTables;
                break;
            case CLIENT:
                allowedTables = RequestsConfiguration.clientTables;
                break;
        }
    }

    public Vector<String[]> getRequestResult(int tableIndex) {
        Vector<String[]> result = new Vector<String[]>();
        String[] columnTitles = allowedTables[index].getGuiGetColumnsTitles();
        result.add(columnTitles);
        Vector<String[]> requestResult;
        requestResult = SQLActions.getSpecificDatafromTable(allowedTables[index],parameters);
        for(String[] current : requestResult) {
            result.add(current);
        }
        return result;
    }

    public String[] getLabels() {
        String[] labels = {"Tables"};
        return labels;
    }

    public String[] getUpdatedLabels() {
        String[] newLabels = allowedTables[index].getGuiGetColumnsTitles();
        String[] labels = new String[newLabels.length+1];
        labels[0]="Tables";
        for(int i = 0; i < newLabels.length; ++i) {
            labels[i+1]=newLabels[i];
        }
        return labels;
    }

    public void selectedIndex(int index) {
        this.index = index;
        updatedParametersCount = allowedTables[this.index].getColumnsCount();
    }
}

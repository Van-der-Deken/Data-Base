package Interface;

/**
 * Created by Y500 on 17.11.2015.
 */
public class TableDescription {
    private String title;
    private String[] columnsTitles;
    private String[] visibleColumnsTypes;
    private TableDescription[] refTables;
    private String[] columnsDefinition;
    private String[] foreignKeys;

    public TableDescription(String[] inColumnsDefinition, String[] inVisibleColumnsTypes) {
        columnsDefinition = inColumnsDefinition;
        visibleColumnsTypes = inVisibleColumnsTypes;
        refTables = new TableDescription[0];
        foreignKeys = new String[0];
    }

    public void setTitle(String inTitle) {
        title = inTitle;
    }

    public void setColumnsTitles(String[] inColumnsTitles) {
        columnsTitles = inColumnsTitles;
    }

    public TableDescription setForeignKeys(String[] inForeignKeys) {
        foreignKeys = inForeignKeys;
        return this;
    }

    public TableDescription setRefTables(TableDescription[] inRefTables) {
        refTables = inRefTables;
        return this;
    }

    public String getGuiTitle() {
        return GUIConfiguration.GUIGemini.get(title);
    }

    public String getTitle() {
        return title;
    }

    public String[] getGuiAddColumnsTitles() {
        String[] output = new String[visibleColumnsTypes.length];
        if(columnsTitles.length > visibleColumnsTypes.length) {
            for(int i = 0; i < output.length; ++i) {
                output[i] = GUIConfiguration.GUIGemini.get(columnsTitles[i+1]);
            }
        } else {
            for(int i = 0; i < output.length; ++i) {
                output[i] = GUIConfiguration.GUIGemini.get(columnsTitles[i]);
            }
        }
        return output;
    }

    public String[] getGuiGetColumnsTitles() {
        String[] output = new String[columnsTitles.length];
        for(int i = 0; i < output.length; ++i) {
            output[i] = GUIConfiguration.GUIGemini.get(columnsTitles[i]);
        }
        return output;
    }

    public String[] getColumnsTitles() {
        return columnsTitles;
    }

    public String[] getAddColumnsTitles() {
        if(columnsTitles.length > visibleColumnsTypes.length) {
            String[] output = new String[visibleColumnsTypes.length];
            for(int i = 0; i < output.length; ++i) {
                output[i] = columnsTitles[i+1];
            }
            return output;
        } else {
            return columnsTitles;
        }
    }

    public String[] getColumnsDefinition() {
        return columnsDefinition;
    }

    public String[] getForeignKeys() {
        return foreignKeys;
    }

    public String[] getVisibleColumnsTypes() {
        return visibleColumnsTypes;
    }

    public int getColumnsCount() {
        return columnsDefinition.length;
    }

    public int getFieldsCount() {
        int fieldsCount = 0;
        for(String current : visibleColumnsTypes) {
            if(textfield(current)) {
                ++fieldsCount;
            }
        }
        return fieldsCount;
    }

    public int getComboBoxesCount() {
        int comboBoxesCount = 0;
        for(String current : visibleColumnsTypes) {
            if(comboBox(current)) {
                ++comboBoxesCount;
            }
        }
        return comboBoxesCount;
    }

    public TableDescription[] getRefTables() {
        return refTables;
    }

    public int getGUIElementsCount() {
        return getFieldsCount()+getComboBoxesCount();
    }

    public int currentElement(int index) {
        if(comboBox(visibleColumnsTypes[index])) {
            return GUIConfiguration.COMBOBOX;
        } else {
            return GUIConfiguration.FIELD;
        }
    }

    public int[] getOrder() {
        int[] order = new int[visibleColumnsTypes.length];
        for(int i = 0; i < visibleColumnsTypes.length; ++i) {
            if(comboBox(visibleColumnsTypes[i])) {
                order[i] = GUIConfiguration.COMBOBOX;
            } else {
                order[i] = GUIConfiguration.FIELD;
            }
        }
        return order;
    }

    private boolean comboBox(String value) {
        if(value.equals(TablesConfiguration.SERIAL)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean textfield(String value) {
        return !comboBox(value);
    }
}

package Interface;

import java.util.Vector;

/**
 * Created by Y500 on 17.11.2015.
 */
abstract class RequestDescription {
    protected String requestName;
    protected DBConstants.TYPE userType;
    protected int parametersCount;
    protected int updatedParametersCount;
    protected boolean mutable;
    protected String[] parameters;

    public RequestDescription() {}

    public RequestDescription(String name) {
        requestName = name;
    }

    public String getRequestName() {
        return requestName;
    }

    public int getParametersCount() {
        return parametersCount;
    }

    public int getUpdatedParametersCount() {
        return updatedParametersCount+parametersCount;
    }

    public void setUserType(DBConstants.TYPE type) {
        userType = type;
    }

    public int[] getOrder() {
        int[] order = new int[parametersCount];
        for(int i = 0; i < parametersCount; ++i) {
            order[i] = GUIConfiguration.COMBOBOX;
        }
        return order;
    }

    public int[] getUpdatedOrder() {
        int[] order = new int[parametersCount + updatedParametersCount];
        for(int i = 0; i < parametersCount + updatedParametersCount; ++i) {
            order[i] = GUIConfiguration.COMBOBOX;
        }
        return order;
    }

    public void setParameters(String[] requestParameters) {
        parameters = requestParameters;
    }

    public boolean mutable() {
        return mutable;
    }

    public abstract Vector<String[]> getParametersItems();

    public abstract String[] getLabels();

    public abstract Vector<String[]> getUpdatedParametersItems();

    public abstract String[] getUpdatedLabels();

    public abstract Vector<String[]> getRequestResult(int tableIndex);

    public abstract void selectedIndex(int index);
}

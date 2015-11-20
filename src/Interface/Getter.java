package Interface;

import com.jgoodies.forms.layout.CellConstraints;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Y500 on 14.11.2015.
 */
public class Getter extends GUIWindow {
    private JScrollPane tablePane;
    private JTable table;
    private JButton back;

    private TableModel model;
    private Dimension frameSize = new Dimension();
    private Dimension tableSize = new Dimension();
    private int columnsCount;
    private RequestDescription description;

    public class DBTableMode implements TableModel {

        private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
        private Vector<String[]> rows;

        public DBTableMode(Vector<String[]> requestResult) {
            rows = requestResult;
        }

        public void addTableModelListener(TableModelListener listener) {
            listeners.add(listener);
        }

        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        public int getColumnCount() {
            return rows.get(0).length;
        }

        public String getColumnName(int columnIndex) {
            return rows.get(0)[columnIndex];
        }

        public int getRowCount() {
            return rows.size()-1;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return rows.get(rowIndex+1)[columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        public void removeTableModelListener(TableModelListener listener) {
            listeners.remove(listener);
        }

        public void setValueAt(Object value, int rowIndex, int columnIndex) {

        }
    }

    public Getter(Vector<String[]> data, RequestDescription inDescription) {
        Dimension buttonSize = new Dimension(100,20);
        String[] panelSpecs = {"10dlu, center:pref, 10dlu",
                "10dlu, center:pref, 10dlu, center:pref, 10dlu"};
        columnsCount = data.get(0).length;
        description = inDescription;
        createFrameTableSize();
        model = new DBTableMode(data);
        mainFrame = GUIElements.createFrame(frameSize);
        framePanel = GUIElements.createPanel(frameSize, panelSpecs);
        table = GUIElements.createTable(model);
        tablePane = GUIElements.createScrollPane(tableSize, table);
        back = GUIElements.createButton(buttonSize,"Back");

        CellConstraints cc = new CellConstraints();
        framePanel.add(tablePane, cc.xy(2,2));
        framePanel.add(back, cc.xy(2,4));
        mainFrame.setContentPane(framePanel);
    }

    public void show() {
        mainFrame.pack();
        mainFrame.setVisible(true);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(description != null) {
                    close();
                    Parameterising parameterising = new Parameterising(description);
                    parameterising.show();
                } else {
                    Manager manager = new Manager();
                    close();
                    manager.show();
                }
            }
        });
    }

    private void createFrameTableSize() {
        frameSize.setSize(100+100*columnsCount, 100+100*columnsCount);
        tableSize.setSize(57+100*columnsCount, 100*columnsCount);
    }
}

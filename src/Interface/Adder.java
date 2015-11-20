package Interface;

import com.jgoodies.forms.layout.CellConstraints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by Y500 on 14.11.2015.
 */
public class Adder extends Configurable {
    private JButton add;
    private JButton back;
    private TableDescription description;

    public Adder(TableDescription inDescription) {
        super();
        description = inDescription;
        Dimension buttonSize = new Dimension(100,30);
        add = GUIElements.createButton(buttonSize,"Add");
        back = GUIElements.createButton(buttonSize,"Back");
        createFields(description.getFieldsCount());
        createComboBoxes(description.getComboBoxesCount());
        createFrameAndPanel();
        createLabels(description.getGuiAddColumnsTitles());
        addElementsToPanel(description.getOrder());
        addMouseAdapters();

        CellConstraints cc = new CellConstraints();
        framePanel.add(add, cc.xy(4,3));
        framePanel.add(back, cc.xy(4,6));
        mainFrame.setContentPane(framePanel);
    }

    public void show() {
        setComboBoxesItems();
        addItemsToComboBox();
        mainFrame.setTitle("Add record");
        mainFrame.pack();
        mainFrame.setVisible(true);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messages.setText("");
                String[] typesArray = description.getVisibleColumnsTypes();
                String[] fieldsArray = description.getAddColumnsTitles();
                String name = description.getTitle();
                int fieldsIndex = 0;
                int comboBoxIndex = 0;
                String[] values = new String[typesArray.length];
                if(typesArray != null && fields != null && name != null) {
                    for(int i = 0; i < typesArray.length; ++i) {
                        if(typesArray[i].equals(TablesConfiguration.INT)) {
                            if(Functions.isInt(fields.get(fieldsIndex).getText())) {
                                values[i] = fields.get(fieldsIndex).getText();
                                ++fieldsIndex;
                            } else {
                                return;
                            }
                        } else if(typesArray[i].equals(TablesConfiguration.SERIAL)) {
                            values[i] = (String)comboBoxes.get(comboBoxIndex).getSelectedItem();
                            ++comboBoxIndex;
                        } else {
                            values[i] = fields.get(fieldsIndex).getText();
                            ++fieldsIndex;
                        }
                    }
                    if(!SQLActions.insertRow(name, fieldsArray, values)) {
                        messages.setText("Illegal value(s)");
                    } else {
                        messages.setText("Record added");
                    }
                } else {
                    System.out.println("Critical error");
                    close();
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
                Manager manager = new Manager();
                manager.show();
            }
        });
    }

    protected void setComboBoxesItems() {
        comboBoxesItems = new Vector<String[]>();
        int comboBoxesIndex = 0;
        for(int i = 0; i < description.getGUIElementsCount(); ++i) {
            if(description.currentElement(i) == GUIConfiguration.COMBOBOX) {
                String[] items = SQLActions.getColumn(description.getRefTables()[comboBoxesIndex], "id", false).toArray(new String[0]);
                comboBoxesItems.add(items);
                ++comboBoxesIndex;
            }
        }
    }
}

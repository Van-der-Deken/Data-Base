package Interface;

import com.jgoodies.forms.layout.CellConstraints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created by Y500 on 15.11.2015.
 */
public class Configurable extends GUIWindow {
    protected Vector<JTextField> fields;
    protected Vector<JComboBox> comboBoxes;
    protected Vector<JLabel> labels;
    protected JLabel messages;

    protected int fieldsCount;
    protected int comboBoxesCount;
    protected Vector<String[]> comboBoxesItems;

    public Configurable() {
        Dimension messagesSize = new Dimension(100,30);
        messages = GUIElements.createLabel(messagesSize, "");
    }

    protected void createFrameAndPanel() {
        int size = fieldsCount+comboBoxesCount;
        if(size == 1) {
            ++size;
        }
        Dimension frameSize = new Dimension(380,70+size*70);
        String columnSpecs = "center:pref, center:pref, center:pref,";
        for(int i = 1; i < size; ++i) {
            columnSpecs += " 5dlu, center:pref, center:pref,";
        }
        columnSpecs += " 10dlu";
        String[] panelSpecs = {"10dlu, center:pref, 20dlu, center:pref, 10dlu",
                columnSpecs};

        mainFrame = GUIElements.createFrame(frameSize);
        framePanel = GUIElements.createPanel(frameSize,panelSpecs);
    }

    protected void createFields(int inFieldsCount) {
        fieldsCount = inFieldsCount;
        Dimension fieldSize = new Dimension(200,30);
        fields = new Vector<JTextField>();
        for(int i = 0; i < fieldsCount; ++i) {
            fields.add(GUIElements.createTextField(fieldSize));
        }
    }

    protected void createComboBoxes(int inComboBoxesCount) {
        comboBoxesCount = inComboBoxesCount;
        Dimension comboBoxSize = new Dimension(200,30);
        comboBoxes = new Vector<JComboBox>();
        for(int i = 0; i < comboBoxesCount; ++i) {
            comboBoxes.add(GUIElements.createComboBox(comboBoxSize));
        }
    }

    protected void createLabels(String[] values) {
        int size = fieldsCount+comboBoxesCount;
        Dimension labelSize = new Dimension(100,30);
        labels = new Vector<JLabel>();
        for(int i = 0; i < size; ++i) {
            labels.add(GUIElements.createLabel(labelSize, values[i]));
        }
    }

    protected void addElementsToPanel(int[] order) {
        CellConstraints cc = new CellConstraints();
        framePanel.add(messages, cc.xy(2,1));
        int fieldIndex = 0;
        int comboBoxIndex = 0;
        for(int i = 0; i < fieldsCount+comboBoxesCount; ++ i) {
            framePanel.add(labels.get(i), cc.xy(2,2+3*i));
            switch(order[i]) {
                case GUIConfiguration.FIELD:
                    framePanel.add(fields.get(fieldIndex), cc.xy(2,3+3*i));
                    ++fieldIndex;
                    break;
                case GUIConfiguration.COMBOBOX:
                    framePanel.add(comboBoxes.get(comboBoxIndex), cc.xy(2,3+3*i));
                    ++comboBoxIndex;
                    break;
            }
        }
    }

    protected void addItemsToComboBox() {
        for(int i = 0; i < comboBoxes.size(); ++i) {
            for(int j = 0; j < comboBoxesItems.get(i).length; ++j) {
                comboBoxes.get(i).addItem(comboBoxesItems.get(i)[j]);
            }
        }
    }

    protected void addMouseAdapters() {
        for(JTextField current : fields) {
            current.addMouseListener(createMouseAdapter());
        }
        for(JComboBox current : comboBoxes) {
            current.addMouseListener(createMouseAdapter());
        }
    }

    private MouseAdapter createMouseAdapter() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messages.setText("");
            }
        };
        return mouseAdapter;
    }

    protected void setComboBoxesItems() {}
}

package Interface;

import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Created by Y500 on 13.11.2015.
 */
abstract class GUIElements {

    public static JFrame createFrame(Dimension size) {
        JFrame output = new JFrame();
        //Convert inputs
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Configure frame
        output.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        output.setMinimumSize(size);
        output.setPreferredSize(size);
        output.setMaximumSize(size);
        output.setSize(size);
        output.setResizable(false);
        output.setLocation(screenSize.width/2-output.getSize().width/2, screenSize.height/2-output.getSize().height/2);
        return output;
    }

    public static JPanel createPanel(Dimension size, String[] specs) {
        JPanel output = new JPanel();
        //Convert inputs
        FormLayout layout = new FormLayout(specs[0],specs[1]);
        //Configure panel
        output.setLayout(layout);
        output.setMinimumSize(size);
        output.setPreferredSize(size);
        output.setMaximumSize(size);
        output.setSize(size);
        output.setBackground(GUIConfiguration.background);
        output.setForeground(GUIConfiguration.foreground);
        return output;
    }

    public static JTextField createTextField(Dimension size) {
        JTextField output = new JTextField();
        //Configure textfield
        output.setMinimumSize(size);
        output.setPreferredSize(size);
        output.setMaximumSize(size);
        output.setSize(size);
        output.setBackground(GUIConfiguration.fieldBackground);
        output.setForeground(GUIConfiguration.foreground);
        output.setDisabledTextColor(GUIConfiguration.disabledTextColor);
        output.setSelectedTextColor(GUIConfiguration.selectedTextColor);
        output.setSelectionColor(GUIConfiguration.selectionColor);
        output.setCaretColor(GUIConfiguration.caretColor);
        output.setCaretPosition(0);
        return output;
    }

    public static JPasswordField createPasswordField(Dimension size) {
        JPasswordField output = new JPasswordField();
        //Configure passwordfield
        output.setMinimumSize(size);
        output.setPreferredSize(size);
        output.setMaximumSize(size);
        output.setSize(size);
        output.setBackground(GUIConfiguration.fieldBackground);
        output.setForeground(GUIConfiguration.foreground);
        output.setDisabledTextColor(GUIConfiguration.disabledTextColor);
        output.setSelectedTextColor(GUIConfiguration.selectedTextColor);
        output.setSelectionColor(GUIConfiguration.selectionColor);
        output.setCaretColor(GUIConfiguration.caretColor);
        output.setCaretPosition(0);
        return output;
    }

    public static JButton createButton(Dimension size, String title) {
        JButton output = new JButton();
        //Configure button
        output.setMinimumSize(size);
        output.setPreferredSize(size);
        output.setMaximumSize(size);
        output.setSize(size);
        output.setBackground(GUIConfiguration.fieldBackground);
        output.setForeground(GUIConfiguration.foreground);
        output.setText(title);
        return output;
    }

    public static JLabel createLabel(Dimension size, String text) {
        JLabel output = new JLabel();
        //Configure label
        output.setMinimumSize(size);
        output.setPreferredSize(size);
        output.setMaximumSize(size);
        output.setSize(size);
        output.setBackground(GUIConfiguration.fieldBackground);
        output.setForeground(GUIConfiguration.foreground);
        output.setHorizontalAlignment(SwingConstants.CENTER);
        output.setVerticalAlignment(SwingConstants.CENTER);
        output.setText(text);
        return output;
    }

    public static JComboBox createComboBox(Dimension size) {
        JComboBox output = new JComboBox();
        //Configure combo box
        output.setMinimumSize(size);
        output.setPreferredSize(size);
        output.setMaximumSize(size);
        output.setSize(size);
        output.setBackground(GUIConfiguration.fieldBackground);
        output.setForeground(GUIConfiguration.foreground);
        return output;
    }

    public static JTable createTable(TableModel model) {
        JTable output = new JTable(model);
        //Configure table
        output.setDefaultRenderer(String.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.setHorizontalAlignment(SwingConstants.CENTER);
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                return this;
            }
        });
        output.setBackground(GUIConfiguration.foreground);
        output.setForeground(GUIConfiguration.fieldBackground);
        return output;
    }

    public static JScrollPane createScrollPane(Dimension size, Component in) {
        JScrollPane output = new JScrollPane(in);
        //Configure pane
        output.createHorizontalScrollBar();
        output.createVerticalScrollBar();
        output.setMinimumSize(size);
        output.setPreferredSize(size);
        output.setMaximumSize(size);
        output.setSize(size);
        output.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        output.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        return output;
    }
}

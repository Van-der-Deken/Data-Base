package Interface;

import com.jgoodies.forms.layout.CellConstraints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by Y500 on 15.11.2015.
 */
public class Parameterising extends Configurable {
    private JButton next;
    private JButton back;

    private String[] choosenItems;
    private RequestDescription description;

    public Parameterising(RequestDescription inDescription) {
        super();
        Dimension buttonSize = new Dimension(100,30);
        description = inDescription;
        next = GUIElements.createButton(buttonSize,"Next");
        back = GUIElements.createButton(buttonSize, "Back");
        createFields(0);
        createComboBoxes(description.getParametersCount());
        createFrameAndPanel();
        createLabels(description.getLabels());
        addElementsToPanel(description.getOrder());
        addMouseAdapters();
        description.setUserType(Resourses.userType);
        setComboBoxesItems();
        addItemsToComboBox();

        CellConstraints cc = new CellConstraints();
        framePanel.add(next, cc.xy(4,3));
        framePanel.add(back, cc.xy(4,6));
        mainFrame.setContentPane(framePanel);
    }

    public void show() {
        mainFrame.setTitle("Choose parameters");
        mainFrame.pack();
        mainFrame.setVisible(true);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
                String[] parameters = (labels.get(0).getText().equals("Tables")) ? new String[comboBoxes.size()-1] : new String[comboBoxes.size()];
                for(int i = 0; i < comboBoxes.size(); ++i) {
                    if(labels.get(0).getText().equals("Tables")) {
                        if(i > 0) {
                            parameters[i-1]=(String)comboBoxes.get(i).getSelectedItem();
                        }
                    } else {
                        parameters[i]=(String)comboBoxes.get(i).getSelectedItem();
                    }
                }
                description.setParameters(parameters);
                Getter getter = new Getter(description.getRequestResult(comboBoxes.get(0).getSelectedIndex()), description);
                getter.show();
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

        comboBoxes.get(0).addActionListener(forZeroComboBox());
        /*comboBoxes.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(description.mutable()) {
                    mainFrame.setVisible(false);
                    description.selectedIndex(comboBoxes.get(0).getSelectedIndex());
                    *//*updateComboBoxes();
                    updateLabels();
                    createFrameAndPanel();
                    addElementsToPanel(description.getUpdatedOrder());
                    addMouseAdapters();
                    updateComboBoxesItems();
                    addItemsToComboBox();
                    addListeners();*//*
                    createComboBoxes(description.getUpdatedParametersCount());
                    createLabels(description.getUpdatedLabels());
                    createFrameAndPanel();
                    addElementsToPanel(description.getUpdatedOrder());
                    addMouseAdapters();
                    updateComboBoxesItems();
                    addItemsToComboBox();
                    addListeners();
                    CellConstraints cc = new CellConstraints();
                    framePanel.add(next, cc.xy(4,3));
                    framePanel.add(back, cc.xy(4,6));
                    mainFrame.setContentPane(framePanel);
                    mainFrame.pack();
                    mainFrame.setVisible(true);
                }
                *//**TODO: Текущая идея: Если запрос изменяемый, то мы устанавливаем слушатель на нулевой комбобокс, который в случае выбора
                 * TODO: определённой таблицы конфигурирует под неё фрейм (выводит дополнительные комбобоксы, добавляет в них слушателей)
                 * TODO: Всё это происходит внутри 0 на основании описания таблицы.
                 *//*
            }
        });*/
    }

    protected void setComboBoxesItems() {
        comboBoxesItems = description.getParametersItems();
    }

    private void updateComboBoxesItems() {
        comboBoxesItems = description.getUpdatedParametersItems();
    }

    private void updateComboBoxes() {
        comboBoxesCount += description.getUpdatedParametersCount();
        Dimension comboBoxSize = new Dimension(200,30);
        for(int i = 0; i < description.getUpdatedParametersCount(); ++i) {
            comboBoxes.add(GUIElements.createComboBox(comboBoxSize));
        }
    }

    private void updateLabels() {
        Dimension labelSize = new Dimension(100,30);
        String[] values = description.getUpdatedLabels();
        for(int i = 0; i < description.getUpdatedParametersCount(); ++i) {
            labels.add(GUIElements.createLabel(labelSize, values[i]));
        }
    }

    private ActionListener forZeroComboBox() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(description.mutable()) {
                    mainFrame.setVisible(false);
                    description.selectedIndex(comboBoxes.get(0).getSelectedIndex());
                    createComboBoxes(description.getUpdatedParametersCount());
                    createLabels(description.getUpdatedLabels());
                    createFrameAndPanel();
                    addElementsToPanel(description.getUpdatedOrder());
                    addMouseAdapters();
                    updateComboBoxesItems();
                    addItemsToComboBox();
                    CellConstraints cc = new CellConstraints();
                    framePanel.add(next, cc.xy(4,3));
                    framePanel.add(back, cc.xy(4, 6));
                    mainFrame.setContentPane(framePanel);
                    comboBoxes.get(0).addActionListener(forZeroComboBox());
                    mainFrame.setTitle("Choose parameters");
                    mainFrame.pack();
                    mainFrame.setVisible(true);
                }
            }
        };
    }
}

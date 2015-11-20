package Interface;

import com.jgoodies.forms.layout.CellConstraints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Y500 on 14.11.2015.
 */
public class Manager extends GUIWindow {
    private JComboBox requests;
    private JComboBox tables;
    private JButton chooseRequest;
    private JButton chooseTable;

    private RequestDescription[] allowedRequests;

    public Manager() {
        Dimension frameSize = new Dimension(480,230);
        Dimension comboBoxSize = new Dimension(200,20);
        Dimension titleSize = new Dimension(150,40);
        Dimension buttonSize = new Dimension(100,20);
        Dimension labelSize = new Dimension(100,30);
        String[] panelSpecs = {"10dlu, center:pref, 5dlu, center:pref, 10dlu, center:pref, 10dlu",
                                "center:pref, center:pref, 10dlu, center:pref, center:pref, 10dlu"};

        mainFrame = GUIElements.createFrame(frameSize);
        framePanel = GUIElements.createPanel(frameSize, panelSpecs);
        JLabel requestsTitle = GUIElements.createLabel(titleSize,"Get information");
        requestsTitle.setFont(GUIConfiguration.titleFont);
        JLabel changeTitle = GUIElements.createLabel(titleSize,"Add information");
        changeTitle.setFont(GUIConfiguration.titleFont);
        requests = GUIElements.createComboBox(comboBoxSize);
        configureRequestsComboBox();
        tables = GUIElements.createComboBox(comboBoxSize);
        configureTablesComboBox();
        JLabel requestsLabel = GUIElements.createLabel(labelSize,"Choose request");
        requestsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel changeLabel = GUIElements.createLabel(labelSize,"Choose table");
        changeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        chooseRequest = GUIElements.createButton(buttonSize,"Next");
        chooseTable = GUIElements.createButton(buttonSize,"Next");

        CellConstraints cc = new CellConstraints();
        framePanel.add(requestsTitle, cc.xy(4,1));
        framePanel.add(changeTitle, cc.xy(4,4));
        framePanel.add(requests, cc.xy(4,2));
        framePanel.add(tables, cc.xy(4,5));
        framePanel.add(requestsLabel, cc.xy(2,2));
        framePanel.add(changeLabel, cc.xy(2,5));
        framePanel.add(chooseRequest, cc.xy(6, 2));
        framePanel.add(chooseTable, cc.xy(6, 5));
        mainFrame.setContentPane(framePanel);
    }

    public void show() {
        mainFrame.setTitle("Choose action");
        mainFrame.pack();
        mainFrame.setVisible(true);

        chooseRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(allowedRequests[requests.getSelectedIndex()].getParametersCount() > 0) {
                    Parameterising param = new Parameterising(allowedRequests[requests.getSelectedIndex()]);
                    close();
                    param.show();
                }
            }
        });

        chooseTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tables.getSelectedIndex();
                Adder adder = new Adder(DBConstants.descriptions[index]);
                if(adder != null) {
                    close();
                    adder.show();
                } else {
                    System.out.println("Error occured during creation of Adder");
                }
            }
        });
    }

    public void configureTablesComboBox() {
        if(Functions.checkTables()) {
            for(TableDescription current : DBConstants.descriptions) {
                tables.addItem(current.getGuiTitle());
            }
        } else {
            System.out.println("DB don`t equals it`s configuration");
            close();
        }
    }

    public void configureRequestsComboBox() {
        switch (Resourses.userType) {
            case ADMIN:
                for(RequestDescription current : RequestsConfiguration.adminRequests) {
                    requests.addItem(current.getRequestName());
                }
                allowedRequests = RequestsConfiguration.adminRequests;
                break;
            case WORKER:
                /*for(RequestDescription current : RequestsConfiguration.adminRequests) {
                    requests.addItem(current.getRequestName());
                }
                allowedRequests = RequestsConfiguration.adminRequests;*/
                break;
            case CLIENT:
                /*for(RequestDescription current : RequestsConfiguration.adminRequests) {
                    requests.addItem(current.getRequestName());
                }
                allowedRequests = RequestsConfiguration.adminRequests;*/
                break;
        }
    }
}

package Interface;

import com.jgoodies.forms.layout.CellConstraints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Y500 on 13.11.2015.
 */
public class LogIn extends GUIWindow {
    private JLabel messagesLabel;
    private JTextField login;
    private JPasswordField password;
    private JButton enter;

    public LogIn() {
        Dimension frameSize = new Dimension(343,200);
        Dimension fieldsSize = new Dimension(200,30);
        Dimension buttonSize = new Dimension(100,30);
        Dimension labelSize = new Dimension(75,30);
        String[] panelSpecs = {"10dlu, center:pref, 5dlu, center:pref, 10dlu",
                                "center:pref, center:pref, 10dlu, center:pref, 10dlu, center:pref, 10dlu"};
        mainFrame = GUIElements.createFrame(frameSize);
        framePanel = GUIElements.createPanel(frameSize, panelSpecs);
        messagesLabel = GUIElements.createLabel(fieldsSize,"");
        JLabel loginLabel = GUIElements.createLabel(labelSize,"Login:");
        loginLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel passwordLabel = GUIElements.createLabel(labelSize,"Password:");
        passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        login = GUIElements.createTextField(fieldsSize);
        password = GUIElements.createPasswordField(fieldsSize);
        enter = GUIElements.createButton(buttonSize, "Enter");

        CellConstraints cc = new CellConstraints();
        framePanel.add(messagesLabel, cc.xy(4,1));
        framePanel.add(loginLabel, cc.xy(2,2));
        framePanel.add(passwordLabel, cc.xy(2, 4));
        framePanel.add(login, cc.xy(4,2));
        framePanel.add(password, cc.xy(4, 4));
        framePanel.add(enter, cc.xy(4, 6));
        mainFrame.setContentPane(framePanel);
    }

    public void show() {
        mainFrame.setTitle("Log in");
        mainFrame.pack();
        mainFrame.setVisible(true);

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messagesLabel.setText("");
                if(SQLActions.connectToDB(login.getText(), new String(password.getPassword()))) {
                    close();
                    Manager managerWindow = new Manager();
                    managerWindow.show();
                } else {
                    messagesLabel.setText("Invalid login or password");
                }
            }
        });

        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messagesLabel.setText("");
            }
        });

        password.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messagesLabel.setText("");
            }
        });
    }
}

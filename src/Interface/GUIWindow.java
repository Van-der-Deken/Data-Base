package Interface;

import javax.swing.*;

/**
 * Created by Y500 on 13.11.2015.
 */
abstract class GUIWindow {
    protected JFrame mainFrame;
    protected JPanel framePanel;

    public void show() {}

    public void close() {
        mainFrame.dispose();
    }
}

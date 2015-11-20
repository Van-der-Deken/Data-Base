package Interface;

/**
 * Created by Y500 on 13.11.2015.
 */
public class Main {
    public static void main(String[] args) {
        SQLActions.configureDB();
        Functions.setGUIGemini();
        LogIn logIn = new LogIn();
        logIn.show();
    }
}

package ic.doc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiApp {
    private View view = new View(new Controller());

    private ReversePolishCalculator calculator = new ReversePolishCalculator(view);
    
    class Controller implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("+")) {
                calculator.performOperation('+');
            } else if (e.getActionCommand().equals("-")) {
                calculator.performOperation('-');
            } else {
                calculator.addOperand(Integer.parseInt(e.getActionCommand()));
            }
        }
    }

    public static void main(String[] args) {
        new GuiApp();
    }
}

package ic.doc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.WindowConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class View implements Updatable {
    private final JFrame frame;
    private final JPanel panel;
    private final JTextField textField = new JTextField(15);
    private JButton[] numberButtons = new JButton[10];
    private final JButton buttonPlus = new JButton("+");
    private final JButton buttonMinus = new JButton("-");


    public View(ActionListener controller) {
        frame = new JFrame("Reverse Polish Calculator");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel();

        for (Integer i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(i.toString());
            panel.add(numberButtons[i]);
            numberButtons[i].addActionListener(controller);
        }

        panel.add(buttonPlus);
        panel.add(buttonMinus);
        buttonPlus.addActionListener(controller);
        buttonMinus.addActionListener(controller);

        panel.add(textField);

        frame.getContentPane().add(panel);

        frame.setVisible(true);
    }

    @Override
    public void update(ReversePolishCalculator calculator) {
        if (calculator.operationFailed()) {
            textField.setText("Insufficient operand");
            return;
        }

        String stackTopValue = calculator.getValue().toString();
        textField.setText(stackTopValue);
    }

}

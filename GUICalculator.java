import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICalculator extends JFrame implements ActionListener {
    private final JTextField inputField;  // Text field for user input
    private double result = 0;
    private String operator = "=";
    private boolean start = true;

    public GUICalculator() {
        inputField = new JTextField("0", 12);
        inputField.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));  // 4x4 Grid
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "=", "C", "+"
        };

        for (String buttonText : buttons) {
            JButton button = new JButton(buttonText);
            button.addActionListener(this);
            panel.add(button);
        }

        setLayout(new BorderLayout());
        add(inputField, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        initUI();
    }

    private void initUI() {
        setTitle("Calculator");
        setSize(200, 250);
        setLocationRelativeTo(null);  // Center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) == 'C') {
            start = true;
            inputField.setText("0");
            operator = "=";
            result = 0;
        } else if ("0123456789".indexOf(command) >= 0) {
            if (start) {
                inputField.setText(command);
            } else {
                inputField.setText(inputField.getText() + command);
            }
            start = false;
        } else {
            if (start) {
                if (command.equals("-")) {
                    inputField.setText(command);
                    start = false;
                } else {
                    operator = command;
                }
            } else {
                double x = Double.parseDouble(inputField.getText());
                calculate(x);
                operator = command;
                start = true;
            }
        }
    }

    private void calculate(double n) {
        switch (operator) {
            case "+":
                result += n;
                break;
            case "-":
                result -= n;
                break;
            case "*":
                result *= n;
                break;
            case "/":
                result /= n;
                break;
            case "=":
                result = n;
                break;
        }
        inputField.setText("" + result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUICalculator calculator = new GUICalculator();
            calculator.setVisible(true);
        });
    }
}

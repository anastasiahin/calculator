import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private String operator = "";
    private boolean newCalculation = true;

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "√", "^", "C", "CE"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            switch (command) {
                case "C":
                    display.setText("");
                    firstNumber = 0;
                    secondNumber = 0;
                    operator = "";
                    newCalculation = true;
                    break;
                case "CE":
                    display.setText("");
                    newCalculation = true;
                    break;
                case "=":
                    secondNumber = Double.parseDouble(display.getText());
                    calculateResult();
                    newCalculation = true;
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                    firstNumber = Double.parseDouble(display.getText());
                    operator = command;
                    display.setText("");
                    break;
                case "√":
                    firstNumber = Double.parseDouble(display.getText());
                    display.setText(String.valueOf(Math.sqrt(firstNumber)));
                    newCalculation = true;
                    break;
                default: // numbers and decimal point
                    if (newCalculation) {
                        display.setText("");
                        newCalculation = false;
                    }
                    display.setText(display.getText() + command);
                    break;
            }
        } catch (Exception ex) {
            display.setText("Error");
            newCalculation = true;
        }
    }

    private void calculateResult() {
        double result = 0;

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber == 0) {
                    display.setText("Error: Division by zero");
                    return;
                }
                result = firstNumber / secondNumber;
                break;
            case "^":
                result = Math.pow(firstNumber, secondNumber);
                break;
        }
        display.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}

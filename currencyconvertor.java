import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CurrencyConverter extends JFrame implements ActionListener {
    private JComboBox<String> fromCurrency, toCurrency;
    private JTextField amountField;
    private JLabel resultLabel;
    private HashMap<String, Double> exchangeRates;

    public CurrencyConverter() {
        // Set up the frame
        setTitle("Currency Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Define supported currencies
        String[] currencies = {"USD", "EUR", "GBP", "INR"};
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0);    // Base currency
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.75);
        exchangeRates.put("INR", 74.0);

        // Set up combo boxes for currency selection
        fromCurrency = new JComboBox<>(currencies);
        fromCurrency.setBounds(50, 30, 100, 30);
        add(fromCurrency);

        toCurrency = new JComboBox<>(currencies);
        toCurrency.setBounds(250, 30, 100, 30);
        add(toCurrency);

        // Set up input field for amount
        amountField = new JTextField();
        amountField.setBounds(50, 80, 100, 30);
        add(amountField);

        // Set up convert button
        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(250, 80, 100, 30);
        convertButton.addActionListener(this);
        add(convertButton);

        // Set up label for displaying results
        resultLabel = new JLabel("Result:");
        resultLabel.setBounds(50, 130, 300, 30);
        add(resultLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get selected currencies and amount
        String from = (String) fromCurrency.getSelectedItem();
        String to = (String) toCurrency.getSelectedItem();
        try {
            double amount = Double.parseDouble(amountField.getText());
            double result = convertCurrency(from, to, amount);
            resultLabel.setText("Result: " + result + " " + to);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid amount.");
        }
    }

    private double convertCurrency(String from, String to, double amount) {
        // Convert the amount from one currency to another
        double fromRate = exchangeRates.getOrDefault(from, 1.0);
        double toRate = exchangeRates.getOrDefault(to, 1.0);
        return (amount / fromRate) * toRate;
    }

    public static void main(String[] args) {
        // Run the application
        SwingUtilities.invokeLater(() -> {
            CurrencyConverter converter = new CurrencyConverter();
            converter.setVisible(true);
        });
    }
}
dgdrhgrethe
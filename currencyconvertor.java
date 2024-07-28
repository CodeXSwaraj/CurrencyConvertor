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
        setTitle("Currency Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        String[] currencies = {"USD", "EUR", "GBP", "INR"};
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0);    // Base currency
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.75);
        exchangeRates.put("INR", 74.0);
        
        fromCurrency = new JComboBox<>(currencies);
        fromCurrency.setBounds(50, 30, 100, 30);
        add(fromCurrency);
        
        toCurrency = new JComboBox<>(currencies);
        toCurrency.setBounds(250, 30, 100, 30);
        add(toCurrency);
        
        amountField = new JTextField();
        amountField.setBounds(50, 80, 100, 30);
        add(amountField);
        
        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(250, 80, 100, 30);
        convertButton.addActionListener(this);
        add(convertButton);
        
        resultLabel = new JLabel("Result:");
        resultLabel.setBounds(50, 130, 300, 30);
        add(resultLabel);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String from = (String) fromCurrency.getSelectedItem();
        String to = (String) toCurrency.getSelectedItem();
        double amount = Double.parseDouble(amountField.getText());
        double result = convertCurrency(from, to, amount);
        resultLabel.setText("Result: " + result + " " + to);
    }
    
    private double convertCurrency(String from, String to, double amount) {
        double fromRate = exchangeRates.get(from);
        double toRate = exchangeRates.get(to);
        return (amount / fromRate) * toRate;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverter converter = new CurrencyConverter();
            converter.setVisible(true);
        });
    }
}

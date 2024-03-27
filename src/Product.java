import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.Comparator;

public class Product extends JFrame {
    private JTextField productName, productDescription, productPrice;
    private JComboBox<String> available;
    private JButton button;
    private JTable productList;

    public Product() {
        setTitle("PRODUCT MANAGEMENT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        setVisible(true);

        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(6, 4));
        formPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 5, 5));
        // Define a cor de fundo do JPanel
        formPanel.setBackground(Color.LIGHT_GRAY);

        productName = new JTextField();
        formPanel.add(new JLabel("Product Name:"));
        formPanel.add(productName);

        productDescription = new JTextField();
        formPanel.add(new JLabel("Product Description:"));
        formPanel.add(productDescription);

        productPrice = new JTextField();
        formPanel.add(new JLabel("Product Price:"));
        formPanel.add(productPrice);

        available = new JComboBox<>(new String[]{"Yes", "No"});
        formPanel.add(new JLabel("Available for Sale:"));
        formPanel.add(available);

        button = new JButton("Add Product");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setProductList();
            }
        });

        available.setBackground(new Color(169,169,169));
        button.setBackground(new Color(169,169,169));

        formPanel.add(button);

        add(formPanel, BorderLayout.WEST);

        // Listagem de produtos
        JPanel productListPanel = new JPanel(new BorderLayout());
        productList = new JTable(new DefaultTableModel(new Object[]{"Name", "Price",}, 0));
        productListPanel.add(new JScrollPane(productList), BorderLayout.CENTER);

        JButton newButton = new JButton("Add New Product");
        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearForm();

            }
        });

        newButton.setBackground(new Color(169,169,169));


        productListPanel.add(newButton, BorderLayout.SOUTH);

        add(productListPanel, BorderLayout.CENTER);

        setVisible(true);
    }
    

    private void setProductList() {
        String productName = this.productName.getText();
        String productDescription = this.productDescription.getText();
        double productPrice = Double.parseDouble(this.productPrice.getText());
        String availableForSale = (String) available.getSelectedItem();

        DefaultTableModel template = (DefaultTableModel) productList.getModel();
        template.addRow(new Object[]{productName, productPrice});


        // Ordenação por valor
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(productList.getModel());
        sorter.setComparator(1, new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return Double.compare(o1, o2);
            }
        });
        productList.setRowSorter(sorter);

    }

    private void clearForm() {
        productName.setText("");
        productDescription.setText("");
        productPrice.setText("");
        available.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Product();
            }
        });
    }
}

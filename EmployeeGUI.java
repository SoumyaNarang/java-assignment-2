import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class EmployeeGUI {

    static ArrayList<Employee> employees = new ArrayList<>();

    JTextField idField;
    JTextField nameField;
    JTextField deptField;
    JTextField salaryField;

    JButton addButton;
    JButton updateButton;
    JButton deleteButton;
    JButton clearButton;

    JTable table;
    DefaultTableModel model;

    public EmployeeGUI() {

        JFrame frame = new JFrame("Employee Management System");

        frame.setSize(850, 600);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        // MAIN PANEL

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        // FONTS

        Font titleFont = new Font("Arial", Font.BOLD, 28);

        Font labelFont = new Font("Arial", Font.BOLD, 16);

        Font textFont = new Font("Arial", Font.PLAIN, 15);

        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        // TITLE LABEL

        JLabel titleLabel = new JLabel(
                "Employee Management System",
                JLabel.CENTER
        );

        titleLabel.setFont(titleFont);

        // INPUT PANEL

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 15, 15));

        inputPanel.setBackground(new Color(245, 245, 245));

        inputPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        JLabel idLabel = new JLabel("ID:");

        idLabel.setFont(labelFont);

        inputPanel.add(idLabel);

        idField = new JTextField(20);

        idField.setFont(textFont);

        inputPanel.add(idField);

        JLabel nameLabel = new JLabel("Name:");

        nameLabel.setFont(labelFont);

        inputPanel.add(nameLabel);

        nameField = new JTextField(20);

        nameField.setFont(textFont);

        inputPanel.add(nameField);

        JLabel deptLabel = new JLabel("Department:");

        deptLabel.setFont(labelFont);

        inputPanel.add(deptLabel);

        deptField = new JTextField(20);

        deptField.setFont(textFont);

        inputPanel.add(deptField);

        JLabel salaryLabel = new JLabel("Salary:");

        salaryLabel.setFont(labelFont);

        inputPanel.add(salaryLabel);

        salaryField = new JTextField(20);

        salaryField.setFont(textFont);

        inputPanel.add(salaryField);

        // INPUT WRAPPER

        JPanel inputWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));

        inputWrapper.setBackground(new Color(245, 245, 245));

        inputWrapper.add(inputPanel);

        // BUTTON PANEL

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        buttonPanel.setBackground(new Color(245, 245, 245));

        addButton = new JButton("Add");

        updateButton = new JButton("Update");

        deleteButton = new JButton("Delete");

        clearButton = new JButton("Clear");

        Dimension buttonSize = new Dimension(120, 40);

        addButton.setPreferredSize(buttonSize);

        updateButton.setPreferredSize(buttonSize);

        deleteButton.setPreferredSize(buttonSize);

        clearButton.setPreferredSize(buttonSize);

        addButton.setFont(buttonFont);

        updateButton.setFont(buttonFont);

        deleteButton.setFont(buttonFont);

        clearButton.setFont(buttonFont);

        addButton.setBackground(new Color(76, 175, 80));

        updateButton.setBackground(new Color(255, 193, 7));

        deleteButton.setBackground(new Color(244, 67, 54));

        clearButton.setBackground(new Color(158, 158, 158));

        addButton.setForeground(Color.WHITE);

        deleteButton.setForeground(Color.WHITE);

        buttonPanel.add(addButton);

        buttonPanel.add(updateButton);

        buttonPanel.add(deleteButton);

        buttonPanel.add(clearButton);

        // TOP PANEL

        JPanel topPanel = new JPanel(new BorderLayout());

        topPanel.setBackground(new Color(245, 245, 245));

        topPanel.add(titleLabel, BorderLayout.NORTH);

        topPanel.add(inputWrapper, BorderLayout.CENTER);

        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // TABLE

        String[] columns = {"ID", "Name", "Department", "Salary"};

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        table.setFont(textFont);

        table.setRowHeight(30);

        table.getTableHeader().setFont(labelFont);

        JScrollPane scrollPane = new JScrollPane(table);

        // ADD COMPONENTS TO MAIN PANEL

        mainPanel.add(topPanel, BorderLayout.NORTH);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // ADD MAIN PANEL TO FRAME

        frame.add(mainPanel);

        // TABLE ROW SELECTION

        table.getSelectionModel().addListSelectionListener(e -> {

            int selectedRow = table.getSelectedRow();

            if(selectedRow != -1) {

                idField.setText(model.getValueAt(selectedRow, 0).toString());

                nameField.setText(model.getValueAt(selectedRow, 1).toString());

                deptField.setText(model.getValueAt(selectedRow, 2).toString());

                salaryField.setText(model.getValueAt(selectedRow, 3).toString());
            }
        });

        // ADD BUTTON FUNCTIONALITY

        addButton.addActionListener(e -> {

            if(idField.getText().isEmpty() ||
               nameField.getText().isEmpty() ||
               deptField.getText().isEmpty() ||
               salaryField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(frame,
                        "Please fill all fields");

                return;
            }

            try {

                int id = Integer.parseInt(idField.getText());

                String name = nameField.getText();

                String dept = deptField.getText();

                double salary = Double.parseDouble(salaryField.getText());

                employees.add(new Employee(id, name, dept, salary));

                model.addRow(new Object[]{id, name, dept, salary});

                clearFields();

            }

            catch(NumberFormatException ex) {

                JOptionPane.showMessageDialog(frame,
                        "ID must be integer and Salary must be numeric");
            }
        });

        // UPDATE BUTTON FUNCTIONALITY

        updateButton.addActionListener(e -> {

            int selectedRow = table.getSelectedRow();

            if(selectedRow == -1) {

                JOptionPane.showMessageDialog(frame,
                        "Please select a row to update");

                return;
            }

            if(idField.getText().isEmpty() ||
               nameField.getText().isEmpty() ||
               deptField.getText().isEmpty() ||
               salaryField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(frame,
                        "Please fill all fields");

                return;
            }

            try {

                int id = Integer.parseInt(idField.getText());

                String name = nameField.getText();

                String dept = deptField.getText();

                double salary = Double.parseDouble(salaryField.getText());

                employees.get(selectedRow).id = id;

                employees.get(selectedRow).name = name;

                employees.get(selectedRow).department = dept;

                employees.get(selectedRow).salary = salary;

                model.setValueAt(id, selectedRow, 0);

                model.setValueAt(name, selectedRow, 1);

                model.setValueAt(dept, selectedRow, 2);

                model.setValueAt(salary, selectedRow, 3);

                clearFields();

            }

            catch(NumberFormatException ex) {

                JOptionPane.showMessageDialog(frame,
                        "Invalid ID or Salary");
            }
        });

        // DELETE BUTTON FUNCTIONALITY

        deleteButton.addActionListener(e -> {

            int selectedRow = table.getSelectedRow();

            if(selectedRow == -1) {

                JOptionPane.showMessageDialog(frame,
                        "Please select a row to delete");

                return;
            }

            employees.remove(selectedRow);

            model.removeRow(selectedRow);

            clearFields();
        });

        // CLEAR BUTTON FUNCTIONALITY

        clearButton.addActionListener(e -> clearFields());

        // FRAME SETTINGS

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    // CLEAR METHOD

    void clearFields() {

        idField.setText("");

        nameField.setText("");

        deptField.setText("");

        salaryField.setText("");
    }

    public static void main(String[] args) {

        new EmployeeGUI();
    }
}
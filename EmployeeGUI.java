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

        frame.setSize(700, 500);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        // INPUT PANEL

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        inputPanel.add(new JLabel("ID:"));

        idField = new JTextField();

        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));

        nameField = new JTextField();

        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Department:"));

        deptField = new JTextField();

        inputPanel.add(deptField);

        inputPanel.add(new JLabel("Salary:"));

        salaryField = new JTextField();

        inputPanel.add(salaryField);

        // BUTTON PANEL

        JPanel buttonPanel = new JPanel(new FlowLayout());

        addButton = new JButton("Add");

        updateButton = new JButton("Update");

        deleteButton = new JButton("Delete");

        clearButton = new JButton("Clear");

        buttonPanel.add(addButton);

        buttonPanel.add(updateButton);

        buttonPanel.add(deleteButton);

        buttonPanel.add(clearButton);

        // TABLE

        String[] columns = {"ID", "Name", "Department", "Salary"};

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        // ADD COMPONENTS TO FRAME

        frame.add(inputPanel, BorderLayout.NORTH);

        frame.add(scrollPane, BorderLayout.CENTER);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // ADD BUTTON FUNCTIONALITY

        addButton.addActionListener(e -> {

            int id = Integer.parseInt(idField.getText());

            String name = nameField.getText();

            String dept = deptField.getText();

            double salary = Double.parseDouble(salaryField.getText());

            employees.add(new Employee(id, name, dept, salary));

            model.addRow(new Object[]{id, name, dept, salary});

            clearFields();
        });

        // CLEAR BUTTON FUNCTIONALITY

        clearButton.addActionListener(e -> clearFields());

        // UPDATE BUTTON FUNCTIONALITY

        updateButton.addActionListener(e -> {

            int selectedRow = table.getSelectedRow();

            if (selectedRow != -1) {

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
        });

        // DELETE BUTTON FUNCTIONALITY

        deleteButton.addActionListener(e -> {

            int selectedRow = table.getSelectedRow();

            if (selectedRow != -1) {

                employees.remove(selectedRow);

                model.removeRow(selectedRow);

                clearFields();
            }
        });

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
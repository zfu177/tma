
import com.github.lgooddatepicker.components.DatePicker;
import dao.TaskDao;

import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;

public class AddTask extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameField;
    private JTextArea descriptionField;
    private JComboBox priorityField;
    private DatePicker dueDateField;

    private final TaskDao taskDao = new TaskDao();

    public AddTask() {
        setContentPane(contentPane);
        setModal(true);

        setTitle("Task Management - Add Task (Zhongyi Fu)");
        setSize(500, 500);

        priorityField.addItem("High");
        priorityField.addItem("Medium");
        priorityField.addItem("Low");

        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String priority = Objects.requireNonNull(priorityField.getSelectedItem()).toString();
                String dueDate = dueDateField.getText();
                String description = descriptionField.getText();

                if (name.length() == 0 || priority.length() == 0 || dueDate.length() == 0 || description.length() == 0) {
                    JOptionPane.showMessageDialog(null, "One or more fields cannot be empty");
                    return;
                }

                taskDao.add(name, priority, dueDate, description);
                JOptionPane.showMessageDialog(null, "Success");
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        AddTask dialog = new AddTask();

        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}

import javax.swing.*;

import dao.TaskDao;
import io.jsondb.*;

import java.awt.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import model.Task;


public class Main extends JFrame {

    final static Logger logger = LoggerFactory.getLogger(Main.class);
    private final TaskDao taskDao = new TaskDao();

    private JPanel MainPanel;
    private JPanel headerPanel;
    private JButton refresh;
    private JButton addBtn;
    private JPanel menuPanel;
    private JPanel bodyPanel;
    private JList taskList;
    private JButton saveBtn;
    private JButton deleteBtn;
    private JTextField textField1;
    private JTextArea textArea1;
    private JComboBox comboBox1;

    public Main(){
        setContentPane(MainPanel);
        setTitle("Task Management (Zhongyi Fu)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 700);
        setLocationRelativeTo(null);
        setVisible(true);

        List<Task> taskListData = taskDao.getAll();
        System.out.println(taskListData.size());
        String[] taskNameList = new String[taskListData.size()];

        for (int i = 0; i < taskListData.size(); i++) {
            taskNameList[i] = taskListData.get(i).getName();
        }

        DefaultListModel listModel = new DefaultListModel();
        for (String taskName : taskNameList) {
            listModel.addElement(taskName);
        }

        taskList = new JList(listModel);
        taskList.setPreferredSize(new Dimension(400, 600)); // Adjust size as needed

        bodyPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);


        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTask atDialog = new AddTask();
                atDialog.setVisible(true);
            }
        });
    }


    public static void main(String[] args) {
        new Main();

    }
}

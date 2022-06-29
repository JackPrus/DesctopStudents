package by.prus.univercity.view;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RowAddDialog extends JDialog {

    private MyTableModel myTableModel; // MytableModel в которую добавляются / удаляются записи
    private JButton buttonOK = new JButton("Добавить");
    private JButton buttonCancel = new JButton("Отмена");
    private JPanel panel = new JPanel(new GridLayout(6, 2));

    private JLabel nameLabel = new JLabel("Имя");
    private JLabel secondNameLabel = new JLabel("Фамилия");
    private JLabel fatherNameLabel = new JLabel("Отчество");
    private JLabel birthDateLabel = new JLabel("Дата рождения");
    private JLabel groupLabel = new JLabel("Группа");

    private JTextField nameField = new JTextField(10);
    private JTextField secondNameField = new JTextField(10);
    private JTextField fatherNameField = new JTextField(10);
    private JTextField birthDateField = new JTextField(10);
    private JTextField groupField = new JTextField(10);

    public RowAddDialog(MyTableModel myTableModel) {
        this.myTableModel = myTableModel;

        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
        UIManager.put("OptionPane.cancelButtonText", "Отмена");

        this.setTitle("Добавление данных");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //завершение работы приложения при нажатии на кнопку
        this.setVisible(true);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(dimension.width / 2 - 300, dimension.height / 2 - 150, 300, 200);
        init();
    }

    private void init() {
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(secondNameLabel);
        panel.add(secondNameField);
        panel.add(fatherNameLabel);
        panel.add(fatherNameField);
        panel.add(birthDateLabel);
        panel.add(birthDateField);
        panel.add(groupLabel);
        panel.add(groupField);

        panel.add(buttonOK);
        panel.add(buttonCancel);
        getContentPane().add(panel);
        addButtonsActions();
        panel.revalidate();
    }

    private void addButtonsActions() {
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        getContentPane(),
                        "Вы уверены что хотите создать нового пользователя",
                        "Окно подтверждения",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    addLineToTable();
                }
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void addLineToTable() {
        String[] textToAdd = new String[]{
                nameField.getText(),
                secondNameField.getText(),
                fatherNameField.getText(),
                birthDateField.getText(),
                groupField.getText(),
        };

        boolean resultSave = myTableModel.addData(textToAdd);

        if (resultSave) {
            myTableModel.fireTableDataChanged();
            revalidate();
            dispose();
        } else {
            int result = JOptionPane.showConfirmDialog(
                    getContentPane(),
                    "Некорректные данные. Убедитьесь, что дата в формате дд.мм.гггг",
                    "Ошибка ввода данных",
                    JOptionPane.OK_CANCEL_OPTION);
        }
    }
}

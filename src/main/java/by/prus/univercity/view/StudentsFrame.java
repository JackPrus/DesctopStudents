package by.prus.univercity.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class StudentsFrame extends JFrame {

    private MyTableModel myTableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton addButton = new JButton("Добавить");
    private JButton delegeButton = new JButton("Удалить");

    public StudentsFrame(MyTableModel myTableModel) {
        this.myTableModel = myTableModel;

        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.cancelButtonText", "Отмена");

        this.setTitle("Студенты факультета");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //завершение работы приложения при нажатии на кнопку
        this.setVisible(true);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(dimension.width / 2 - 300, dimension.height / 2 - 150, 500, 300);
        init();
    }

    private void init() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        setJMenuBar(menuBar);

        table = new JTable(myTableModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 400));

        GridBagConstraints tableConsstrains = new GridBagConstraints(
                0, 0, 3, 1, 1, 6,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1),
                0, 0);
        GridBagConstraints addButtonCons = new GridBagConstraints(
                0, 7, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1),
                0, 0);
        GridBagConstraints deleteButtonCons = new GridBagConstraints(
                2, 7, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1),
                0, 0);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(scrollPane, tableConsstrains);
        panel.add(addButton, addButtonCons);
        panel.add(delegeButton, deleteButtonCons);
        getContentPane().add(panel);
        addButton.addActionListener(new AddAction());
        delegeButton.addActionListener(new DeleteAction());

        revalidate();
    }

    private JMenu createFileMenu() {
        // Создание выпадающего меню
        JMenu file = new JMenu("Файл");
        // Пункт меню "Открыть" с изображением
        JMenuItem revalidate = new JMenuItem(new RevalidateAction(this));
        // Пункт меню из команды с выходом из программы
        JMenuItem exit = new JMenuItem(new ExitAction());
        // Добавление к пункту меню изображения
        // Добавим в меню пункта open
        file.add(revalidate);
        // Добавление разделителя
        file.addSeparator();
        file.add(exit);
        return file;
    }

    class AddAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new RowAddDialog(myTableModel);
            revalidate();
        }
    }

    class DeleteAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int result = JOptionPane.showConfirmDialog(
                    getContentPane(),
                    "Удалить выбранного пользователя?",
                    "Окно подтверждения",
                    JOptionPane.YES_NO_CANCEL_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                if (myTableModel == null ||
                        table.getSelectedRow() <= 0 // не удалит первую (заглавную) строчку, потому что невыбранная строка помечается "-1"
                ) {
                    JOptionPane.showMessageDialog(
                            getContentPane(),
                            new String[]{"Ошибка:", "нет данных для удаления"}, // построчный вывод текста
                            "Error", // В шапке окошка
                            JOptionPane.ERROR_MESSAGE); //
                } else {
                    boolean deleteResult = myTableModel.deleteData(table.getSelectedRow());
                    if (deleteResult){
                        myTableModel.update();
                        myTableModel.fireTableDataChanged();
                    }else{
                        JOptionPane.showMessageDialog(
                                getContentPane(),
                                new String[]{"Ошибка при удалении:"}, // построчный вывод текста
                                "Error", // В шапке окошка
                                JOptionPane.ERROR_MESSAGE); //
                    }
                }
            }
        }
    }

    class ExitAction extends AbstractAction {
        private static final long serialVersionUID = 1L;
        ExitAction() {
            putValue(NAME, "Выход");
        }
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }
    class RevalidateAction extends AbstractAction {
        private static final long serialVersionUID = 1L;
        Frame frame;
        RevalidateAction(Frame frame) {
            putValue(NAME, "Обновить");
            this.frame = frame;
        }
        public void actionPerformed(ActionEvent e) {
            System.out.println("Таблица обновлена");
            frame.revalidate();
        }
    }
}

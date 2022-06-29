package by.prus.univercity.view;

import by.prus.univercity.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MyTableModel extends AbstractTableModel {

    @Autowired
    private StudentServiceImpl studentServiceImpl;
    private List<String[]> rowList;


    public MyTableModel() {
        rowList = new ArrayList<>();
        rowList.add(createHeaderOfColumns());
    }

    public void update() {
        String[] firstLine = rowList.get(0);
        rowList.clear();
        rowList.add(firstLine);
        rowList.addAll(studentServiceImpl.getAllStudents());
    }

    @Override
    public int getRowCount() {
        return rowList != null ? rowList.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return rowList != null && rowList.size() > 0 ? rowList.get(0).length : 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (
                rowList.size() > 0 && rowList.get(0).length > 0 &&
                        rowIndex >= 0 && rowIndex < rowList.size() &&
                        columnIndex >= 0 && columnIndex < rowList.get(0).length
        ) {
            return rowList.get(rowIndex)[columnIndex];
        } else {
            System.out.println("Индекс вне зоны видимости");
            return null;
        }
    }

    private String[] createHeaderOfColumns() {
        return new String[]{"ID", "Имя", "Фамилия", "Отчество", "Дата рождения", "Группа"};
    }

    public boolean addData(String[] newLine) {
        boolean reusult = studentServiceImpl.saveStudent(newLine);
        if (reusult) {
            update();
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteData(int i) {
        if (i < rowList.size()) {
            return studentServiceImpl.deleteStudent(rowList.get(i));
        }
        return false;
    }
}

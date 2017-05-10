package by.bsuir.dbms.dao.Impl;

import by.bsuir.dbms.dao.Update;
import by.bsuir.dbms.exceptions.DAOException;

import java.util.Arrays;
import java.util.List;

public class UpdateImpl implements Update {
    private static UpdateImpl ourInstance = new UpdateImpl();

    public static UpdateImpl getInstance() {
        return ourInstance;
    }

    private UpdateImpl() {
    }

    @Override
    public void updateRow(String table, int id, String[] columns, String[] values) throws DAOException {
        List<String[]> data;
        data = SelectImpl.getInstance().selectAllFromTable(table);
        int line = 0;
        String[] row = null;
        for (int i = 1; i < data.size()-1; i++){
            String one = data.get(i)[0];
            String two = String.valueOf(id);
            if (data.get(i)[0].equals(String.valueOf(id))){
                line = i;
                row = data.get(i);
                break;
            }
        }
        if (row == null)
            throw new DAOException();

        DeleteImpl.getInstance().deleteLine(table, line);
        row = updateValues(data.get(0), row, columns, values);
        data.remove(line);
        data.add(row);
        InsertImpl.getInstance().insertByValues(table, data.get(0), row);
    }

    private String[] updateValues(String[] columns, String[] initialValues, String[] keys, String[] values) throws DAOException{
        if (columns == null || columns.length == 0 ||
                initialValues == null || initialValues.length == 0 ||
                keys == null || keys.length == 0 ||
                values == null || values.length == 0 ||
                columns.length != initialValues.length ||
                keys.length != values.length)
            throw new DAOException();
        for (String col : keys){
            if (!Arrays.asList(columns).contains(col))
                throw new DAOException();
        }
        for (int i = 0; i < columns.length; i++){
            for (int j = 0; j < keys.length; j++){
                if (columns[i].equals(keys[j])){
                    initialValues[i] = values[j];
                }
            }
        }
        return initialValues;
    }
}

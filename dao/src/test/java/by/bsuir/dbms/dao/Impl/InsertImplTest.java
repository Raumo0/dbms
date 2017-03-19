package by.bsuir.dbms.dao.Impl;

import by.bsuir.dbms.constants.DAOConstants;
import by.bsuir.dbms.dao.Insert;
import by.bsuir.dbms.tools.FileWorker;
import com.opencsv.CSVReader;
import org.junit.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InsertImplTest {
    private static String assertMsg;
    private File file;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from InsertImplTest failed: ";
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void insertByValues() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;

        String table = DAOConstants.PATH_DB_TEST + "test_insert.csv";
        String[] columns = new String[]{"id", "name"};
        String[] values = new String[]{"1", "Steve"};
        boolean completed;
        CSVReader reader;
        List<String[]> actual = new ArrayList<String[]>();
        String[] line;
        FileWorker fileWorker = new FileWorker(table);

        Insert insert = InsertImpl.getInstance();
        file = new File(table);
        if (file.exists())
            file.delete();
        file.getParentFile().mkdirs();
        file.createNewFile();
        fileWorker.write("\"id\",\"name\"\n");

        completed = insert.insertByValues(table, columns, values);
        try {
            reader = new CSVReader(new FileReader(table));
            while ((line = reader.readNext()) != null) {
                actual.add(line);
            }
        } catch (IOException e) {
            throw new Exception(e);
        }

        Assert.assertTrue(msg, completed);
        Assert.assertTrue(msg, actual.size() == 2);
        Assert.assertArrayEquals(msg, columns, actual.get(0));
        Assert.assertArrayEquals(msg, values, actual.get(1));

        actual.clear();
        values[0] = "2";
        values[1] = "Pete";
        completed = insert.insertByValues(table, columns, values);
        try {
            reader = new CSVReader(new FileReader(table));
            while ((line = reader.readNext()) != null) {
                actual.add(line);
            }
        } catch (IOException e) {
            throw new Exception(e);
        }

        Assert.assertTrue(msg, completed);
        Assert.assertTrue(msg, actual.size() == 3);
        Assert.assertArrayEquals(msg, columns, actual.get(0));
        Assert.assertArrayEquals(msg, values, actual.get(2));
    }

}
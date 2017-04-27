package by.bsuir.dbms.dao.Impl;

import by.bsuir.dbms.constants.DAOConstants;
import by.bsuir.dbms.dao.Table;
import by.bsuir.dbms.tools.FileWorker;
import com.opencsv.CSVReader;
import org.junit.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableImplTest {
    private static String assertMsg;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from TableImplTest failed: ";
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void create() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;

        String filePath = DAOConstants.PATH_DB_TEST +
                Thread.currentThread().getStackTrace()[1].getClassName() +
                "_" +
                Thread.currentThread().getStackTrace()[1].getMethodName();
        String[] header = "first,second,third".split(",");
        Table table = TableImpl.getInstance();
        List<String[]> expected = new ArrayList<String[]>();
        CSVReader reader;
        String[] line;
        List<String[]> actual = new ArrayList<String[]>();
        FileReader fileReader;

        if (FileWorker.fileExists(filePath))
            FileWorker.delete(filePath);
        expected.add("1,2,3".split(","));
        expected.add("4,5,6".split(","));
        expected.add("7,8,9".split(","));
        expected.add("10,11,12".split(","));
        expected.add("13,14,15".split(","));
        expected.add(2, null);
        table.create(filePath, header, expected);
        expected.remove(2);
        fileReader = new FileReader(filePath);

        try {
            reader = new CSVReader(fileReader);
            while ((line = reader.readNext()) != null) {
                actual.add(line);
            }
        } catch (IOException e) {
            throw new Exception(e);
        }
        finally {
            fileReader.close();
        }

        expected.add(0, header);
        Assert.assertEquals(msg, expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++){
            Assert.assertArrayEquals(msg, expected.get(i), actual.get(i));
        }
        reader.close();
        Assert.assertTrue(FileWorker.delete(filePath));
    }

    @Test
    public void delete() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        String filePath = DAOConstants.PATH_DB_TEST +
                Thread.currentThread().getStackTrace()[1].getClassName() +
                "_" +
                Thread.currentThread().getStackTrace()[1].getMethodName();
        FileWorker fileWorker = new FileWorker(filePath, "rw");
        TableImpl table = TableImpl.getInstance();

        fileWorker.create(filePath);
        Assert.assertTrue(msg, fileWorker.fileExists(filePath));
        fileWorker.close();
        table.delete(filePath);
        Assert.assertFalse(msg, FileWorker.fileExists(filePath));
    }
}
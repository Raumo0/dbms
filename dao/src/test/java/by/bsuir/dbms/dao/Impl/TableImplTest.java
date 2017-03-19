package by.bsuir.dbms.dao.Impl;

import by.bsuir.dbms.constants.DAOConstants;
import by.bsuir.dbms.dao.Table;
import by.bsuir.dbms.tools.FileWorker;
import com.opencsv.CSVReader;
import org.junit.*;

import java.io.File;
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

        String filePath = DAOConstants.PATH_DB_TEST + "yourfile.csv";
        String[] header = "first,second,third".split(",");
        Table table = TableImpl.getInstance();
        List<String[]> expected = new ArrayList<String[]>();
        CSVReader reader;
        String[] line;
        List<String[]> actual = new ArrayList<String[]>();

        expected.add("1,2,3".split(","));
        expected.add("4,5,6".split(","));
        expected.add("7,8,9".split(","));
        expected.add("10,11,12".split(","));
        expected.add("13,14,15".split(","));
        expected.add(2, null);
        table.create(filePath, header, expected);
        expected.remove(2);

        try {
            reader = new CSVReader(new FileReader(filePath));
            while ((line = reader.readNext()) != null) {
                actual.add(line);
            }
        } catch (IOException e) {
            throw new Exception(e);
        }

        expected.add(0, header);
        Assert.assertEquals(msg, expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++){
            Assert.assertArrayEquals(msg, expected.get(i), actual.get(i));
        }
    }

    @Test
    public void delete() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;
        String name = DAOConstants.PATH_DB_TEST + "yourfile.csv";
        FileWorker fileWorker = new FileWorker(name);
        File file = new File(name);
        TableImpl table = TableImpl.getInstance();

        fileWorker.create(name);

        Assert.assertTrue(msg, file.exists());

        table.delete(name);

        Assert.assertFalse(msg, file.exists());
    }
}
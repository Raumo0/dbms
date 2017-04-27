package by.bsuir.dbms.dao.Impl;

import by.bsuir.dbms.constants.DAOConstants;
import by.bsuir.dbms.dao.Insert;
import by.bsuir.dbms.tools.FileWorker;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class InsertImplTest {
    private static String assertMsg;

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

        boolean completed;
        List<String[]> actual;
        List<String[]> expected = new ArrayList<>();
        String table = DAOConstants.PATH_DB_TEST + "insert.csv";
        Insert insert = InsertImpl.getInstance();
        FileWorker fileWorker;
        if (FileWorker.fileExists(table))
             FileWorker.delete(table);
        FileWorker.create(table);
        fileWorker = new FileWorker(table, "rw");

        fileWorker.write("\"id\",\"name\",\"sex\"\n");
        fileWorker.close();
        expected.add(new String[]{"id", "name", "sex"});//columns
        expected.add(new String[]{"1", "Steve", "male"});//values
        completed = insert.insertByValues(table, expected.get(0), expected.get(1));
        fileWorker = new FileWorker(table, "rw");
        actual = fileWorker.readCSV();

        Assert.assertTrue(msg, completed);
        Assert.assertEquals(msg, expected.size(), actual.size());
        Assert.assertArrayEquals(msg, expected.get(1), actual.get(1));

        actual.clear();
        expected.add(new String[]{"2", "Pete", "male"});
        completed = insert.insertByValues(table, expected.get(0), expected.get(2));
        actual = fileWorker.readCSV();

        Assert.assertTrue(msg, completed);
        Assert.assertEquals(msg, expected.size(), actual.size());
        Assert.assertArrayEquals(msg, expected.get(1), actual.get(1));
        Assert.assertArrayEquals(msg, expected.get(2), actual.get(2));
        fileWorker.close();
        FileWorker.delete(table);
    }

    @Test
    public void insertByValuesDifferentCount() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;

        boolean completed;
        List<String[]> actual;
        List<String[]> expected = new ArrayList<>();
        String table = DAOConstants.PATH_DB_TEST + "insert_different.csv";
        FileWorker fileWorker;
        Insert insert = InsertImpl.getInstance();

        if (FileWorker.fileExists(table))
            FileWorker.delete(table);

        fileWorker = new FileWorker(table, "rw");
        fileWorker.write("\"id\",\"name\",\"sex\"\n");
        expected.add(new String[]{"id", "sex"});//columns
        expected.add(new String[]{"1", "male"});//values
        completed = insert.insertByValues(table, expected.get(0), expected.get(1));
        actual = fileWorker.readCSV();
        expected.set(1, new String[]{expected.get(1)[0], "", expected.get(1)[1]});

        Assert.assertTrue(msg, completed);
        Assert.assertEquals(msg, expected.size(), actual.size());
        Assert.assertArrayEquals(msg, expected.get(1), actual.get(1));

        actual.clear();
        expected.add(new String[]{"2", "female"});
        completed = insert.insertByValues(table, expected.get(0), expected.get(2));
        actual = fileWorker.readCSV();
        expected.set(2, new String[]{expected.get(2)[0], "", expected.get(2)[1]});

        Assert.assertTrue(msg, completed);
        Assert.assertEquals(msg, expected.size(), actual.size());
        Assert.assertArrayEquals(msg, expected.get(1), actual.get(1));
        Assert.assertArrayEquals(msg, expected.get(2), actual.get(2));
        fileWorker.close();
        Assert.assertTrue(FileWorker.delete(table));
    }
}
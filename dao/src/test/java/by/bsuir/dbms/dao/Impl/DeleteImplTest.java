package by.bsuir.dbms.dao.Impl;

import by.bsuir.dbms.constants.DAOConstants;
import by.bsuir.dbms.dao.Delete;
import by.bsuir.dbms.tools.FileWorker;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class DeleteImplTest {
    private static String assertMsg;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from DeleteImplTest failed: ";
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void deleteLine() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;

        String table = DAOConstants.PATH_DB_TEST +
                Thread.currentThread().getStackTrace()[1].getClassName() +
                "_" +
                Thread.currentThread().getStackTrace()[1].getMethodName();
        List<String[]> actual;
        List<String[]> expected = new ArrayList<>();
        Delete delete = DeleteImpl.getInstance();
        FileWorker fileWorker;
        if (FileWorker.fileExists(table))
            FileWorker.delete(table);
        FileWorker.create(table);
        fileWorker = new FileWorker(table, "rw");

        expected.add(new String[]{"id", "title", "page_count"});//columns
        expected.add(new String[]{"1", "Coombe", "319"});//values
        expected.add(new String[]{"2", "My land", "543"});//values
        expected.add(new String[]{"3", "Disoptico", "1306"});//values
        fileWorker.writeCSV(expected);
        fileWorker.close();
        delete.deleteLine(table, 2);
        expected.remove(2);
        fileWorker = new FileWorker(table, "rw");
        actual = fileWorker.readCSV();

        Assert.assertEquals(msg, expected.size(), actual.size());
        Assert.assertArrayEquals(msg, expected.get(0), actual.get(0));
        Assert.assertArrayEquals(msg, expected.get(1), actual.get(1));
        Assert.assertArrayEquals(msg, expected.get(2), actual.get(2));
        fileWorker.close();
        FileWorker.delete(table);
    }

    @Test
    public void delete() throws Exception {
    }
}
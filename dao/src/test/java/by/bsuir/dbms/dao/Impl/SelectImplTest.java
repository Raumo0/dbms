package by.bsuir.dbms.dao.Impl;

import by.bsuir.dbms.constants.DAOConstants;
import by.bsuir.dbms.dao.Select;
import by.bsuir.dbms.tools.FileWorker;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class SelectImplTest {
    private static String assertMsg;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from SelectImplTest failed: ";
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void selectAllFromTable() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;

        String table = DAOConstants.PATH_DB_TEST +
                Thread.currentThread().getStackTrace()[1].getClassName() +
                "_" +
                Thread.currentThread().getStackTrace()[1].getMethodName();
        FileWorker fileWorker = new FileWorker(table, "rw");
        List<String[]> expected = new ArrayList<>();
        List<String[]> actual;
        Select select = SelectImpl.getInstance();

        expected.add(new String[]{"id", "title", "page_count"});//columns
        expected.add(new String[]{"1", "Coombe", "319"});//values
        expected.add(new String[]{"2", "My land", "543"});//values
        expected.add(new String[]{"3", "Disoptico", "1306"});//values
        fileWorker.writeCSV(expected);
        actual = select.selectAllFromTable(table);

        Assert.assertEquals(msg, expected.size(), actual.size());
        Assert.assertArrayEquals(msg, expected.get(0), actual.get(0));
        Assert.assertArrayEquals(msg, expected.get(1), actual.get(1));
        Assert.assertArrayEquals(msg, expected.get(2), actual.get(2));
        Assert.assertArrayEquals(msg, expected.get(3), actual.get(3));
        fileWorker.close();
        FileWorker.delete(table);
    }

}
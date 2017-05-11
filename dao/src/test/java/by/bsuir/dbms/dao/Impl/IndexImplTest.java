package by.bsuir.dbms.dao.Impl;

import by.bsuir.dbms.constants.DAOConstants;
import org.junit.*;

public class IndexImplTest {
    private static String assertMsg;

    @BeforeClass
    public static void setUpClass() throws Exception {
        assertMsg = "() method from IndexImplTest failed: ";
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Ignore
    @Test
    public void createIndexByColumn() throws Exception {
        String msg = Thread.currentThread().getStackTrace()[1].getMethodName() + assertMsg;

        String table = DAOConstants.PATH_DB_TEST +
                Thread.currentThread().getStackTrace()[1].getClassName() +
                "_" +
                Thread.currentThread().getStackTrace()[1].getMethodName();
    }

    @Ignore
    @Test
    public void deleteIndexByColumn() throws Exception {
    }

    @Ignore
    @Test
    public void updateIndexByColumn() throws Exception {
    }

}
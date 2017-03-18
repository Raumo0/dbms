package by.bsuir.dbms.dao.Impl;

import by.bsuir.dbms.dao.Database;
import by.bsuir.dbms.tools.FileWorker;

public class DatabaseImpl implements Database {
    private FileWorker fileWorker;

    private DatabaseImpl() {
    }

    public static DatabaseImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void create(String name) {
        fileWorker = new FileWorker(name);
        fileWorker.makeDirectory(name);
    }

    public void delete(String name) {
        fileWorker = new FileWorker(name);
        fileWorker.removeDirectory(name);
    }

    private static class SingletonHolder {
        private static final DatabaseImpl INSTANCE = new DatabaseImpl();
    }
}

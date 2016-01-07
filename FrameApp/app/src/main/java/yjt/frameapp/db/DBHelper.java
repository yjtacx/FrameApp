package yjt.frameapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库帮助类
 *
 * @author yujiangtao
 * @created 2015-09-13
 */

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper dbHelper = null;

    protected DBHelper(Context context) {
        super(context, DBConstant.DB_NAME, null, DBConstant.VERSION);
    }

    public static DBHelper getInstance(Context context) {
        if (null == dbHelper) {
            synchronized (DBHelper.class) {
                //sqlite存储到sdcard上
                DatabaseContext dbcontext=new DatabaseContext(context);
                dbHelper = new DBHelper(dbcontext);
            }
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBConstant.SQL_MSG_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBConstant.SQL_MSG_DROP);
        db.execSQL(DBConstant.SQL_MSG_CREATE);
    }

}

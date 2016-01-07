package yjt.frameapp.db.tables;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import yjt.frameapp.bean.MsgBean;
import yjt.frameapp.db.DBConstant;
import yjt.frameapp.db.DBHelper;
import yjt.frameapp.utils.SharePrefUtil;

/**
 * Created by yujiangtao on 2015/9/13.
 */
public class MsgDaoImpl implements MsgDao {
    private DBHelper mHelper = null;
    public MsgDaoImpl(Context context) {
        mHelper = DBHelper.getInstance(context);
    }

    @Override
    public synchronized void insertMsg(String userid, MsgBean msg) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        //"msgid integer,userid text,msgtype text,content text,title text,img text,extraa text,extrab text,time text,readable
        db.execSQL("insert into " + DBConstant.TABLE_NAME_MSG +
                        "( msgid,userid,msgtype,content,title,img,extraa,extrab,time,readable)" +
                        " values(?,?,?,?,?,?,?,?,?,?)",
                new Object[]{msg.getMsgid(), userid, msg.getMsgtype(), msg.getContent(),
                        msg.getTitle(), msg.getImg(),
                         msg.getExtraa(),
                        msg.getExtrab(), msg.getTime(), msg.getReadable()});
        db.close();
    }

    @Override
    public synchronized void deleteMsg(String userid, int msgid) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "delete from " + DBConstant.TABLE_NAME_MSG + " where userid = ? and msgid = ?";
        db.execSQL(sql, new Object[]{userid, msgid});
        db.close();
    }

    public synchronized void clearGateway(String userid) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "delete from " + DBConstant.TABLE_NAME_MSG +
                " where userid = ? ";
        db.execSQL(sql, new Object[]{userid});
        db.close();
    }

    public void updateMsg(String userid, String time, String img) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "update " + DBConstant.TABLE_NAME_MSG + " set img = ? where userid = ? and time = ?";
        db.execSQL(sql,
                new Object[]{img, userid, time});
        db.close();
    }

    public synchronized int getUnreadableNum(String userid,int readable) {
        if(SharePrefUtil.getTocken()==null)return 0;
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String sql = "select count(*) from " + DBConstant.TABLE_NAME_MSG + " where userid = ? " +
                "  and readable = ?";
        int count=0;
        try {
            Cursor cursor = db.rawQuery(sql, new String[]{userid, readable+""});
//            if(cursor.moveToNext())count=cursor.getCount();
            if(cursor.moveToFirst())count=cursor.getInt(0);
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return  count;
    }
    public synchronized void updateMsgReadable(String userid,int msgtype) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "update " + DBConstant.TABLE_NAME_MSG + " set readable = ? " +
                "where userid = ?  and msgtype = ?";
        db.execSQL(sql,
                new Object[]{1, userid,msgtype});
        db.close();
    }

    @Override
    public synchronized void updateMsg(String userid, String time, int readable) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "update " + DBConstant.TABLE_NAME_MSG + " set readable = ? where userid = ? and time = ?";
        db.execSQL(sql,
                new Object[]{readable, userid, time});
        db.close();
    }

    public synchronized void updateMsg(String userid, MsgBean mb, int readable) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "update " + DBConstant.TABLE_NAME_MSG + " set readable = ? where userid = ? " +
                "and title = ? and content = ? ";
        db.execSQL(sql,
                new Object[]{readable, userid, mb.getTitle(), mb.getContent()});
        db.close();
    }

    public synchronized int getMsgId(String title, String deviceflag, String userid) {
        String sql = "select * from " + DBConstant.TABLE_NAME_MSG + " where userid = ? and " +
                "title = ? and content = ?";
        SQLiteDatabase db = mHelper.getReadableDatabase();
        int id = -1;
        try {
            Cursor cursor = db.rawQuery(sql, new String[]{userid, title, deviceflag,
                    });
            if (cursor.moveToNext()) {
                id = cursor.getInt(cursor.getColumnIndex("msgid"));
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return id;
    }

    /**
     * msgid integer,userid text,msgtype text,content text,title text,
     * img text,extraa text,extrab text,time text,readable integer
     */
    public synchronized MsgBean getMsg(String title, String deviceflag, String userid, int readable) {
        String sql = "select * from " + DBConstant.TABLE_NAME_MSG + " " +
                "where userid = ? and title = ? and content = ? and readable= ?";
        SQLiteDatabase db = mHelper.getReadableDatabase();
        MsgBean mb = null;
        try {
            Cursor cursor = db.rawQuery(sql, new String[]{userid, title, deviceflag, readable + ""});
            while (cursor.moveToNext()) {
                mb = new MsgBean();
                int id = cursor.getInt(cursor.getColumnIndex("msgid"));
                mb.setMsgid(id);
                mb.setContent(cursor.getString(cursor.getColumnIndex("content")));
                mb.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                mb.setImg(cursor.getString(cursor.getColumnIndex("img")));
                mb.setMsgtype(cursor.getInt(cursor.getColumnIndex("msgtype")));
                mb.setTime(cursor.getString(cursor.getColumnIndex("time")));
                mb.setExtraa(cursor.getString(cursor.getColumnIndex("extraa")));
                mb.setExtrab(cursor.getString(cursor.getColumnIndex("extrab")));
                mb.setReadable(cursor.getInt(cursor.getColumnIndex("readable")));
                break;
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return mb;
    }

    /**
     * 获取所有未读的添加设备消息
     */
    public synchronized List<MsgBean> getMsgList(String userid, String title, int readable) {
        List<MsgBean> list = new ArrayList<MsgBean>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String sql = "select * from " + DBConstant.TABLE_NAME_MSG +
                " where userid = ?  and readable= ? and title = ?";
        try {
            Cursor cursor = db.rawQuery(sql, new String[]{userid, readable + "", title});
            while (cursor.moveToNext()) {
                MsgBean mb = new MsgBean();
                mb.setContent(cursor.getString(cursor.getColumnIndex("content")));
                mb.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                mb.setImg(cursor.getString(cursor.getColumnIndex("img")));
                mb.setMsgtype(cursor.getInt(cursor.getColumnIndex("msgtype")));
                mb.setExtraa(cursor.getString(cursor.getColumnIndex("extraa")));
                mb.setExtrab(cursor.getString(cursor.getColumnIndex("extrab")));
                mb.setReadable(cursor.getInt(cursor.getColumnIndex("readable")));
                mb.setTime(cursor.getString(cursor.getColumnIndex("time")));
                mb.setMsgid(cursor.getInt(cursor.getColumnIndex("msgid")));
                list.add(mb);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return list;
    }

    /***
     * 获取所有在当前网关下的msgtype的消息
     */
    public synchronized List<MsgBean> getMsgList(String userid, int msgtype) {
        List<MsgBean> list = new ArrayList<MsgBean>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String sql = "select * from " + DBConstant.TABLE_NAME_MSG + " where userid = ? and msgtype = ? ";
        try {
            Cursor cursor = db.rawQuery(sql, new String[]{userid, msgtype + ""});
            while (cursor.moveToNext()) {
                MsgBean mb = new MsgBean();
                mb.setContent(cursor.getString(cursor.getColumnIndex("content")));
                mb.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                mb.setImg(cursor.getString(cursor.getColumnIndex("img")));
                mb.setMsgtype(cursor.getInt(cursor.getColumnIndex("msgtype")));
                mb.setExtraa(cursor.getString(cursor.getColumnIndex("extraa")));
                mb.setExtrab(cursor.getString(cursor.getColumnIndex("extrab")));
                mb.setReadable(cursor.getInt(cursor.getColumnIndex("readable")));
                mb.setTime(cursor.getString(cursor.getColumnIndex("time")));
                mb.setMsgid(cursor.getInt(cursor.getColumnIndex("msgid")));
                list.add(mb);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return list;
    }

    public synchronized void clear(String userid) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "delete from " + DBConstant.TABLE_NAME_MSG + " where userid = ?";
        db.execSQL(sql, new Object[]{userid});
        db.close();
    }

    @Override
    public synchronized boolean isExists(String userid, MsgBean msgBean) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String sql = "select * from " + DBConstant.TABLE_NAME_MSG + " where userid = ? " +
                "and title = ? and content = ? and readable = 0";
        Cursor cursor = db.rawQuery(sql, new String[]{userid, msgBean.getTitle(), msgBean.getContent()});
        boolean exists = cursor.moveToNext();
        cursor.close();
        db.close();
        return exists;
    }

    /***
     * is exsit AD
     * @param userid
     * @param title
     * @return
     */
    public synchronized boolean isExistsAD(String userid, String title) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String sql = "select * from " + DBConstant.TABLE_NAME_MSG + " where userid = ? " +
                "and title = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{userid, title});
        boolean exists = cursor.moveToNext();
        cursor.close();
        db.close();
        return exists;
    }
    /***
     * is exsit Bill
     * @param userid
     * @param bid
     * @return
     */
    public synchronized boolean isExitBill(String userid, String bid) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String sql = "select * from " + DBConstant.TABLE_NAME_MSG + " where userid = ? " +
                "and msgid = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{userid, bid});
        boolean exists = cursor.moveToNext();
        cursor.close();
        db.close();
        return exists;
    }
}

package yjt.frameapp.db.tables;


import yjt.frameapp.bean.MsgBean;

/**
 * 消息数据库操作接口
 * @author yujiangtao
 * @created 2015-09-13
 *
 */
public interface MsgDao {
    /**
     * 插入消息
     * @param userid
     * @param
     * @return void
     * @author yujiangtao
     * @date 2015-8-13 上午10:56:18
     */
    public void insertMsg(String userid, MsgBean msg);
    /**
     * 删除消息
     * @param userid
     * @param msgid
     * @return void
     * @author yujiangtao
     * @date 2015-8-13 上午10:56:18
     */
    public void deleteMsg(String userid, int msgid);
//    /**
//     * 更新消息
//     * @param time
//     * @param extraa
//     * @param userid
//     * @return void
//     * @author yujiangtao
//     * @date 2015-8-13 上午10:56:18
//     */
//    public void updateMsg(String userid,String time,String extraa);
    /**
     * 更新消息
     * @param time
     * @param userid
     * @param readable
     * @return void
     * @author yujiangtao
     * @date 2015-8-13 上午10:56:18
     */
    public void updateMsg(String userid, String time, int readable);
    /**
     * 查询所有的消息
     * @param userid
     * @return
     * @return List<MsgBean>
     * @author yujiangtao
     * @date 2015-8-8 上午10:58:48
     */
//    public List<MsgBean> getMsgList(String userid);
    /**
     * 消息是否存在
     * @param userid
     * @param msgBean
     * @return
     * @return boolean
     * @author yujiangtao
     * @date 2015-8-8 上午10:58:48
     */
    public boolean isExists(String userid, MsgBean msgBean);


}

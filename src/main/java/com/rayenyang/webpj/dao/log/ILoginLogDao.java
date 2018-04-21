package com.rayenyang.webpj.dao.log;

import java.util.Date;

/**
 * Created by rayenyang on 2017/2/9.
 */
public interface ILoginLogDao {
    void insertLog(int user_id, String ip, Date loginTime);
}

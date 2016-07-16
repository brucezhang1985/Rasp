package com.myself.rasp.common.vo;

import java.util.Date;

/**
 * Created by bruce.zhang on 7/15/16.
 */
public class Hlogs {
    private int id;
    private String log;
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Hlogs{");
        sb.append("id=").append(id);
        sb.append(", log='").append(log).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }
}

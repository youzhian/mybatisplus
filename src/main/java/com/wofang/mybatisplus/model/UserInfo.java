package com.wofang.mybatisplus.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user")
public class UserInfo {

    @TableId("user_id")
    private Long userId;

    @TableField("user_name")
    private String userName;

    /**
     * 获取
     *
     * @return userId
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * 设置
     *
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取
     *
     * @return userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 设置
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}

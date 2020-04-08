package com.wofang.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.ibatis.type.JdbcType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 请求统计
 */
@TableName("request_statistics")
public class RequestStatistics {
    /**
     * 物理主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    Long id;
    /**
     * 名称，作为key
     */
    String name;
    /**
     * 描述
     */
    String rsDesc;
    /**
     * 请求IP
     */
    String requestIp;
    /**
     * 创建时间 yyyy-MM-dd HH:mm:SS
     */
    @TableField(value="create_time",jdbcType = JdbcType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createTime;
    /**
     * 创建日期 yyyy-MM-dd
     */
    @TableField(value="create_date",jdbcType = JdbcType.DATE)
    Date createDate;

    /**
     * 获取 物理主键
     *
     * @return id 物理主键
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置 物理主键
     *
     * @param id 物理主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 名称，作为key
     *
     * @return name 名称，作为key
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 名称，作为key
     *
     * @param name 名称，作为key
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 描述
     *
     * @return rsDesc 描述
     */
    public String getRsDesc() {
        return this.rsDesc;
    }

    /**
     * 设置 描述
     *
     * @param rsDesc 描述
     */
    public void setRsDesc(String rsDesc) {
        this.rsDesc = rsDesc;
    }

    /**
     * 获取 请求IP
     *
     * @return requestIp 请求IP
     */
    public String getRequestIp() {
        return this.requestIp;
    }

    /**
     * 设置 请求IP
     *
     * @param requestIp 请求IP
     */
    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    /**
     * 获取 创建时间 yyyy-MM-dd HH:mm:SS
     *
     * @return createTime 创建时间 yyyy-MM-dd HH:mm:SS
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置 创建时间 yyyy-MM-dd HH:mm:SS
     *
     * @param createTime 创建时间 yyyy-MM-dd HH:mm:SS
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 创建日期 yyyy-MM-dd
     *
     * @return createDate 创建日期 yyyy-MM-dd
     */
    public Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 设置 创建日期 yyyy-MM-dd
     *
     * @param createDate 创建日期 yyyy-MM-dd
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


}

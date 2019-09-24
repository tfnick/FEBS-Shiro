package cc.mrbird.febs.approve.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *  Entity
 *
 * @author MrBird
 * @date 2019-09-24 18:05:39
 */
@Data
@TableName("dataset")
public class Dataset {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 编码
     */
    @TableField("code")
    private String code;

    /**
     * 描述
     */
    @TableField("comments")
    private String comments;

    /**
     * 数据源编码
     */
    @TableField("data_source_code")
    private String dataSourceCode;

    /**
     * 数据源类型
     */
    @TableField("data_source_type")
    private String dataSourceType;

    /**
     * 
     */
    @TableField("url")
    private String url;

    /**
     * 
     */
    @TableField("uat_url")
    private String uatUrl;

    /**
     * 
     */
    @TableField("req_type")
    private String reqType;

    /**
     * 
     */
    @TableField("content_type")
    private String contentType;

    /**
     * 
     */
    @TableField("timeout")
    private Integer timeout;

    /**
     * 
     */
    @TableField("retry_times")
    private Integer retryTimes;

    /**
     * 
     */
    @TableField("sleep_interval")
    private Integer sleepInterval;

    /**
     * 
     */
    @TableField("select_sql")
    private String selectSql;

    /**
     * 
     */
    @TableField("status")
    private Integer status;

    /**
     * 
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 
     */
    @TableField("update_time")
    private Date updateTime;

}

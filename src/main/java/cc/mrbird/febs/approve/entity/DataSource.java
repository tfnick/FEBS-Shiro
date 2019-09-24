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
 * @date 2019-09-24 18:05:37
 */
@Data
@TableName("data_source")
public class DataSource {

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
     * http mysql postgre hbase mongodb
     */
    @TableField("type")
    private String type;

    /**
     * http域名或者数据库连接url
     */
    @TableField("url")
    private String url;

    /**
     * 
     */
    @TableField("user")
    private String user;

    /**
     * 
     */
    @TableField("pwd")
    private String pwd;

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

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
 * @date 2019-09-24 18:00:42
 */
@Data
@TableName("ru_fact")
public class RuFact {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField("detail")
    private String detail;

    /**
     * 
     */
    @TableField("gid")
    private String gid;

    /**
     * 
     */
    @TableField("sn")
    private String sn;

    /**
     * 
     */
    @TableField("cid")
    private String cid;

    /**
     * 
     */
    @TableField("uuid")
    private String uuid;

    /**
     * 
     */
    @TableField("mobid")
    private String mobid;

    /**
     * 
     */
    @TableField("project_code")
    private String projectCode;

    /**
     * 
     */
    @TableField("xml_process_id")
    private String xmlProcessId;

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

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
 * @date 2019-09-24 18:05:47
 */
@Data
@TableName("process")
public class Process {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField("project_id")
    private Long projectId;

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
    @TableField("xml_process_version")
    private String xmlProcessVersion;

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

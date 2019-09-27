package cc.mrbird.febs.approve.entity;

import java.util.Date;

import lombok.Data;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import cc.mrbird.febs.common.converter.TimeConverter;

/**
 *  Entity
 *
 * @author YangXiao
 * @date 2019-09-26 18:53:51
 */
@Excel(value = "process")
@Data
@TableName("process")
public class Process {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ExcelField(value = "id")
    private Long id;

    /**
     * 项目id
     */
    @TableField("project_id")
    @ExcelField(value = "project_id")
    private Long projectId;

    /**
     * 项目编码-私有
     */
    @TableField("project_code")
    @ExcelField(value = "project_code")
    private String projectCode;

    /**
     * 关联的xml流程标识
     */
    @TableField("xml_process_id")
    @ExcelField(value = "xml_process_id")
    private String xmlProcessId;

    /**
     * 关联的xml流程版本
     */
    @TableField("xml_process_version")
    @ExcelField(value = "xml_process_version")
    private String xmlProcessVersion;

    /**
     * 0:下线状态 1:在线状态
     */
    @TableField("status")
    @ExcelField(value = "status")
    private Integer status;

    /**
     * 描述
     */
    @TableField("comments")
    @ExcelField(value = "comments")
    private String comments;

    /**
     * 
     */
    @TableField("create_time")
    @ExcelField(value = "create_time", writeConverter = TimeConverter.class)
    private Date createTime;

    /**
     * 
     */
    @TableField("update_time")
    @ExcelField(value = "update_time", writeConverter = TimeConverter.class)
    private Date updateTime;


    @TableField(exist = false)
    private String xmlProcessIdVersion;

    @TableField(exist = false)
    private String createTimeFrom;
    @TableField(exist = false)
    private String createTimeTo;

    public Process transformViewFields(){
        xmlProcessIdVersion = xmlProcessId + "/" + xmlProcessId + "__" + xmlProcessVersion;
        /*


        TODO Set value for all field with annotation @TableField(exist = false)
        And the field will display on client page instead of dictionary code

        */

        return this;
    }
}

package cc.mrbird.febs.approve.entity;

import java.util.Date;

import lombok.Data;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *  Entity
 *
 * @author YangXiao
 * @date 2019-09-25 23:37:42
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
     * 关联的xml流程id
     */
    @TableField("xml_process_id")
    @ExcelField(value = "xml_process_id")
    private String xmlProcessId;

    /**
     * 关联的xml流程version
     */
    @TableField("xml_process_version")
    @ExcelField(value = "xml_process_version")
    private String xmlProcessVersion;

    /**
     * 0:不可用 1:正常
     */
    @TableField("status")
    @ExcelField(value = "status")
    private Integer status;

    /**
     * 
     */
    @TableField("create_time")
    @ExcelField(value = "create_time")
    private Date createTime;

    /**
     * 
     */
    @TableField("update_time")
    @ExcelField(value = "update_time")
    private Date updateTime;


    public Process transformViewFields(){
        /*
        @TableField(exist = false)
        private String someViewField;

        TODO Set value for all field with annotation @TableField(exist = false)
        And the field will display on client page instead of dictionary code

        */

        return this;
    }
}

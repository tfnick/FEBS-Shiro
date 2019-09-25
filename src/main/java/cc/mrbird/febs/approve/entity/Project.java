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
 * @date 2019-09-25 23:33:25
 */
@Excel(value = "project")
@Data
@TableName("project")
public class Project {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ExcelField(value = "id")
    private Long id;

    /**
     * 唯一编码,32位长度
     */
    @TableField("code")
    @ExcelField(value = "code")
    private String code;

    /**
     * 描述
     */
    @TableField("comments")
    @ExcelField(value = "comments")
    private String comments;

    /**
     * 日审批限额,[-1,99999999]
     */
    @TableField("daily_limit")
    @ExcelField(value = "daily_limit")
    private Long dailyLimit;

    /**
     * 审批单价，[0,99999999]
     */
    @TableField("price")
    @ExcelField(value = "price")
    private Integer price;

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


    public Project transformViewFields(){
        /*
        @TableField(exist = false)
        private String someViewField;

        TODO Set value for all field with annotation @TableField(exist = false)
        And the field will display on client page instead of dictionary code

        */

        return this;
    }
}

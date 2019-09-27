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
 * @date 2019-09-27 11:47:09
 */
@Excel(value = "data_source")
@Data
@TableName("data_source")
public class DataSource {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ExcelField(value = "id")
    private Long id;

    /**
     * 数据源编码，可以是系统或者数据库
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
     * 数据源类型，http mysql postgre hbase mongodb
     */
    @TableField("type")
    @ExcelField(value = "type")
    private String type;

    /**
     * http域名或者数据库连接url
     */
    @TableField("url")
    @ExcelField(value = "url")
    private String url;

    /**
     * 
     */
    @TableField("user")
    @ExcelField(value = "user")
    private String user;

    /**
     * 
     */
    @TableField("pwd")
    @ExcelField(value = "pwd")
    private String pwd;

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


    public DataSource transformViewFields(){
        /*
        @TableField(exist = false)
        private String someViewField;

        TODO Set value for all field with annotation @TableField(exist = false)
        And the field will display on client page instead of dictionary code

        */

        return this;
    }
}

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
 * @date 2019-09-27 11:47:02
 */
@Excel(value = "dataset")
@Data
@TableName("dataset")
public class Dataset {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ExcelField(value = "id")
    private Long id;

    /**
     * 数据源编码
     */
    @TableField("data_source_code")
    @ExcelField(value = "data_source_code")
    private String dataSourceCode;

    /**
     * 数据集唯一编码
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
     * 数据集类型，http sql
     */
    @TableField("type")
    @ExcelField(value = "type")
    private String type;

    /**
     * http请求path
     */
    @TableField("path")
    @ExcelField(value = "path")
    private String path;

    /**
     * http请求method
     */
    @TableField("method")
    @ExcelField(value = "method")
    private String method;

    /**
     * http请求contentType
     */
    @TableField("content_type")
    @ExcelField(value = "content_type")
    private String contentType;

    /**
     * http请求客户端超时时间
     */
    @TableField("timeout")
    @ExcelField(value = "timeout")
    private Integer timeout;

    /**
     * http请求失败重试次数
     */
    @TableField("retry_times")
    @ExcelField(value = "retry_times")
    private Integer retryTimes;

    /**
     * http请求失败重试间隔，毫秒
     */
    @TableField("sleep_interval")
    @ExcelField(value = "sleep_interval")
    private Integer sleepInterval;

    /**
     * select请求sql语句，支持springEL
     */
    @TableField("select_sql")
    @ExcelField(value = "select_sql")
    private String selectSql;

    /**
     * 数据集状态，0 停用，1 正常
     */
    @TableField("status")
    @ExcelField(value = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @ExcelField(value = "create_time", writeConverter = TimeConverter.class)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    @ExcelField(value = "update_time", writeConverter = TimeConverter.class)
    private Date updateTime;


    public Dataset transformViewFields(){
        /*
        @TableField(exist = false)
        private String someViewField;

        TODO Set value for all field with annotation @TableField(exist = false)
        And the field will display on client page instead of dictionary code

        */

        return this;
    }
}

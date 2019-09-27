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
 * @date 2019-09-27 15:50:33
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
     * 数据源类型，webservice mysql postgre hbase mongodb
     */
    @TableField("type")
    @ExcelField(value = "type")
    private String type;

    /**
     * 数据源编码，可以是webservice系统名或者数据库名
     */
    @TableField("code")
    @ExcelField(value = "code")
    private String code;

    /**
     * type=webservice时必填，动态Host，开启则host参数失效，1 是 0 否
     */
    @TableField("dynamic_host")
    @ExcelField(value = "dynamic_host")
    private Integer dynamicHost;

    /**
     * type=webservice时必填，http请求host地址，以http://开头
     */
    @TableField("host")
    @ExcelField(value = "host")
    private String host;

    /**
     * type!=webservice时必填，数据库连接URL
     */
    @TableField("url")
    @ExcelField(value = "url")
    private String url;

    /**
     * type!=webservice时必填，数据库登录用户名
     */
    @TableField("subject")
    @ExcelField(value = "subject")
    private String subject;

    /**
     * type!=webservice时必填，数据库登录密码
     */
    @TableField("certificate")
//    @ExcelField(value = "certificate")
    private String certificate;

    /**
     * 描述
     */
    @TableField("comments")
    @ExcelField(value = "comments")
    private String comments;

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

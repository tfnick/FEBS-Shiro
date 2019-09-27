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
 * @date 2019-09-27 23:59:10
 */
@Excel(value = "output")
@Data
@TableName("output")
public class Output {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ExcelField(value = "id")
    private Long id;

    /**
     * 关联的数据集
     */
    @TableField("dataset_id")
    @ExcelField(value = "dataset_id")
    private Long datasetId;

    /**
     * 键值
     */
    @TableField("key")
    @ExcelField(value = "key")
    private String key;

    /**
     * 取值表达式，$.foo.bar
     */
    @TableField("val_expression")
    @ExcelField(value = "val_expression")
    private String valExpression;

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


    public Output transformViewFields(){
        /*
        @TableField(exist = false)
        private String someViewField;

        TODO Set value for all field with annotation @TableField(exist = false)
        And the field will display on client page instead of dictionary code

        */

        return this;
    }
}

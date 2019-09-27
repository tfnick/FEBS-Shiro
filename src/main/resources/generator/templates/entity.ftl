package ${basePackage}.${entityPackage};

<#if hasDate = true>
import java.util.Date;
</#if>
<#if hasBigDecimal = true>
import java.math.BigDecimal;
</#if>

import lombok.Data;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import cc.mrbird.febs.common.converter.TimeConverter;

/**
 * ${tableComment} Entity
 *
 * @author ${author}
 * @date ${date}
 */
@Excel(value = "${tableName}")
@Data
@TableName("${tableName}")
public class ${className} {

    <#if columns??>
        <#list columns as column>
    /**
     * ${column.remark}
     */
    <#if column.isKey = true>
    @TableId(value = "${column.name}", type = IdType.AUTO)
    <#else>
    @TableField("${column.name}")
    </#if>
    <#if (column.type = 'varchar' || column.type = 'text' || column.type = 'uniqueidentifier'
        || column.type = 'varchar2' || column.type = 'nvarchar' || column.type = 'VARCHAR2'
        || column.type = 'VARCHAR'|| column.type = 'CLOB' || column.type = 'char')>
    @ExcelField(value = "${column.name}")
    private String ${column.field?uncap_first};

    </#if>
    <#if column.type = 'timestamp' || column.type = 'date' || column.type = 'datetime'||column.type = 'TIMESTAMP' || column.type = 'DATE' || column.type = 'DATETIME'>
    @ExcelField(value = "${column.name}", writeConverter = TimeConverter.class)
    private Date ${column.field?uncap_first};

    </#if>
    <#if column.type = 'int' || column.type = 'smallint'>
    @ExcelField(value = "${column.name}")
    private Integer ${column.field?uncap_first};

    </#if>
    <#if column.type = 'bigint'>
    @ExcelField(value = "${column.name}")
    private Long ${column.field?uncap_first};

    </#if>
    <#if column.type = 'double'>
    @ExcelField(value = "${column.name}")
    private Double ${column.field?uncap_first};

    </#if>
    <#if column.type = 'tinyint'>
    private Byte ${column.field?uncap_first};

    </#if>
    <#if column.type = 'decimal' || column.type = 'numeric'>
    @ExcelField(value = "${column.name}")
    private BigDecimal ${column.field?uncap_first};
    </#if>
        </#list>
    </#if>

    public ${className} transformViewFields(){
        /*
        @TableField(exist = false)
        private String someViewField;

        TODO Set value for all field with annotation @TableField(exist = false)
        And the field will display on client page instead of dictionary code

        */

        return this;
    }
}

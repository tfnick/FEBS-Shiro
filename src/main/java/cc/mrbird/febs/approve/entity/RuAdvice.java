package cc.mrbird.febs.approve.entity;

import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *  Entity
 *
 * @author MrBird
 * @date 2019-09-24 18:00:30
 */
@Data
@TableName("ru_advice")
public class RuAdvice {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField("order_id")
    private String orderId;

    /**
     * 
     */
    @TableField("serial_number")
    private String serialNumber;

    /**
     * 
     */
    @TableField("req_time")
    private String reqTime;

    /**
     * 
     */
    @TableField("parent_order_id")
    private String parentOrderId;

    /**
     * 
     */
    @TableField("etype")
    private String etype;

    /**
     * 
     */
    @TableField("advice")
    private String advice;

    /**
     * 
     */
    @TableField("amount")
    private BigDecimal amount;
    /**
     * 
     */
    @TableField("reason")
    private String reason;

    /**
     * 
     */
    @TableField("rule_output_detail")
    private String ruleOutputDetail;

    /**
     * 
     */
    @TableField("hit_count")
    private Integer hitCount;

    /**
     * 
     */
    @TableField("hit_stop")
    private Integer hitStop;

    /**
     * 
     */
    @TableField("hit_rule")
    private String hitRule;

    /**
     * 
     */
    @TableField("tid")
    private String tid;

    /**
     * 
     */
    @TableField("icc")
    private String icc;

    /**
     * 
     */
    @TableField("icc_sign")
    private String iccSign;

    /**
     * 
     */
    @TableField("channel")
    private String channel;

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

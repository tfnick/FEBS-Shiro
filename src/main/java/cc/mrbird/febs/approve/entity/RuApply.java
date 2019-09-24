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
 * @date 2019-09-24 18:00:32
 */
@Data
@TableName("ru_apply")
public class RuApply {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
    @TableField("etype")
    private String etype;

    /**
     * 申请人姓名
     */
    @TableField("name")
    private String name;

    /**
     * 
     */
    @TableField("idcard")
    private String idcard;

    /**
     * 
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 
     */
    @TableField("bankcard_num")
    private String bankcardNum;

    /**
     * 
     */
    @TableField("detail")
    private String detail;

    /**
     * 
     */
    @TableField("credit_score")
    private String creditScore;

    /**
     * 
     */
    @TableField("tid")
    private String tid;

    /**
     * 
     */
    @TableField("loan_cont_no")
    private String loanContNo;

    /**
     * 
     */
    @TableField("amount")
    private BigDecimal amount;
    /**
     * 
     */
    @TableField("channel")
    private String channel;

    /**
     * 
     */
    @TableField("parent_order_id")
    private String parentOrderId;

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

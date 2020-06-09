package cn.itcast.loan.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "tb_loan")
public class Loan {

    @TableId(type = IdType.ID_WORKER_STR)
    private long id;

    private long loanType;
    private long accountId;
    private Double loanAmount;
    private Double interest;
    private Double payAmount;
    private long userId;
    private Date beginDate;
    private Date endDate;
    private Integer period;
    private Integer state;
    private Integer overdue;
    private Integer finish;
    private Date finishDate;
    private Integer prePay;
}

package cn.itcast.loan.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "tb_loan_detail")
public class LoanDetail {

    @TableId(type = IdType.AUTO)
    private long id;

    private long loanId;
    private Double interest;
    private Double payAmount;
    private Date date;
    private Integer finish;
    private Date finishDate;
    private long userId;
}

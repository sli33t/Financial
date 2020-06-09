package cn.itcast.loan.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tb_loan_type")
public class LoanType {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer state;
    private Double amount;
    private Double interest;

}

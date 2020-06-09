package cn.itcast.loan.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tb_account")
public class Account {

    @TableId(type = IdType.AUTO)
    private long id;
    private String name;
    private Double balance;
    private Integer state;
}

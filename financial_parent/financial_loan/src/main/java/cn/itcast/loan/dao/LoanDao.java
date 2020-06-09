package cn.itcast.loan.dao;

import cn.itcast.loan.domain.Loan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

public interface LoanDao extends BaseMapper<Loan> {

    @Update("update tb_loan set account_id = #{accountId} where id = #{id}")
    int updateAccountId(Loan loan);

}

package cn.itcast.loan.dao;

import cn.itcast.loan.domain.LoanDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LoanDetailDao extends BaseMapper<LoanDetail> {

    @Select("select * from tb_loan_detail where user_id = #{userId} and Coalesce(finish, 0) = 0 order by date")
    List<LoanDetail> selectByUserId(long userId);
}

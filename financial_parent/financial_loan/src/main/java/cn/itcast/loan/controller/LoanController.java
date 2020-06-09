package cn.itcast.loan.controller;

import cn.itcast.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    /**
     * 会员申请借款时，此时需要写入借款表
     * @param userId
     * @param loanType
     * @return
     */
    @RequestMapping(value = "/userApply", method = RequestMethod.POST)
    public Map userApply(long userId, long loanType){
        return loanService.userApply(userId, loanType);
    }

    /**
     * 会员申请借款之后，需要选择从哪个账户出款
     * @param loanId
     * @return
     */
    @RequestMapping(value = "/setAccount", method = RequestMethod.POST)
    public Map setAccount(long loanId, long accountId){
        return loanService.setAccount(loanId, accountId);
    }

    /**
     * 放款之前，写入借款表与借款明细表
     * @param loanId
     * @param loanTypeId
     * @param loanAmount
     * @return
     */
    @RequestMapping(value = "/calcLoan", method = RequestMethod.POST)
    public Map calcLoan(long loanId, long loanTypeId, long userId, Double loanAmount){
        return loanService.clacLoan(loanId, loanTypeId, userId, loanAmount);
    }


    /**
     * 会员还款
     * @param userId
     * @param type 还款类型：当期还款=1；提前还款=2
     * @return
     */
    @RequestMapping(value = "/payAmount", method = RequestMethod.POST)
    public Map payAmount(long userId, Integer type){
        return loanService.payAmount(userId, type);
    }


}

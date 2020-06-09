package cn.itcast.loan.service;

import java.util.Map;

public interface LoanService {

    Map userApply(long userId, long loanType);

    Map setAccount(long loanId, long accountId);

    Map clacLoan(long loanId, long loanTypeId, long userId, Double loanAmount);

    Map payAmount(long userId, Integer type);
}

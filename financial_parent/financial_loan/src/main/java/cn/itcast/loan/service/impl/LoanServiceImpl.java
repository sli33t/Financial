package cn.itcast.loan.service.impl;

import cn.itcast.loan.dao.LoanDao;
import cn.itcast.loan.dao.LoanDetailDao;
import cn.itcast.loan.dao.LoanTypeDao;
import cn.itcast.loan.dao.PayHistoryDao;
import cn.itcast.loan.domain.Loan;
import cn.itcast.loan.domain.LoanDetail;
import cn.itcast.loan.domain.LoanType;
import cn.itcast.loan.domain.PayHistory;
import cn.itcast.loan.service.LoanService;
import cn.itcast.loan.utils.ResultMapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@ControllerAdvice
public class LoanServiceImpl implements LoanService{

    @Autowired
    private LoanDao loanDao;

    @Autowired
    private LoanTypeDao loanTypeDao;

    @Autowired
    private LoanDetailDao loanDetailDao;

    @Autowired
    private PayHistoryDao payHistoryDao;

    @Transactional
    public Map userApply(long userId, long loanType) {
        Loan loan = new Loan();
        loan.setUserId(userId);
        loan.setLoanType(loanType);

        if (loanDao.insert(loan)==1){
            Map returnMap = ResultMapUtil.returnSuccessed(loan.getId());
            returnMap.put("newId", loan.getId());
            return returnMap;
        }else {
            return ResultMapUtil.returnFailed("写入借款表时失败！");
        }
    }

    @Transactional
    public Map setAccount(long loanId, long accountId) {
        Loan loan = new Loan();
        //loan.setId(loanId);
        loan.setAccountId(accountId);

        UpdateWrapper<Loan> wrapper = new UpdateWrapper<>();

        QueryWrapper<Loan> queryWrapper = new QueryWrapper<>();
        wrapper.eq("id", loanId);
        if (loanDao.update(loan, wrapper)==1){
            return ResultMapUtil.returnSuccessed();
        }else {
            return ResultMapUtil.returnFailed("更新借款表账户时失败！");
        }
    }


    @Transactional
    public Map clacLoan(long loanId, long loanTypeId, long userId, Double loanAmount) {
        Loan loan = new Loan();
        //loan.setId(loanId);
        loan.setLoanAmount(loanAmount);

        //1、通过借款类型得到利息金额
        LoanType loanType = loanTypeDao.selectById(loanTypeId);
        Double interest = loanType.getInterest();
        Double loanInterest = loanAmount * interest;
        loan.setInterest(loanInterest);

        //2、获取当前日期与12个月后的日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;
        try {
            beginDate = dateFormat.parse(dateFormat.format(new Date()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(beginDate);
            //默认12期
            calendar.add(Calendar.MONTH, 12);
            endDate = dateFormat.parse(dateFormat.format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        loan.setBeginDate(beginDate);
        loan.setEndDate(endDate);
        loan.setPeriod(12);

        //3、设置状态为已放款
        loan.setState(1);

        //4、更新借款表
        if (loanDao.updateById(loan)==1){

            //5、借款表写成功之后写入借款明细表
            for (int i = 1; i <= 12; i++) {
                LoanDetail loanDetail = new LoanDetail();
                loanDetail.setLoanId(loanId);

                //这里忽略除不尽的
                loanDetail.setPayAmount(loanAmount/12);
                loanDetail.setInterest(loanInterest/12);

                Date date = null;
                try {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(beginDate);
                    calendar.add(Calendar.MONTH, i);
                    date = dateFormat.parse(dateFormat.format(calendar.getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                loanDetail.setDate(date);
                loanDetail.setUserId(userId);

                if (loanDetailDao.insert(loanDetail)==1){
                    return ResultMapUtil.returnSuccessed();
                }else {
                    return ResultMapUtil.returnFailed("第"+i+"次写入借款明细表时失败！");
                }
            }

            return ResultMapUtil.returnSuccessed();
        }else {
            return ResultMapUtil.returnFailed("更新借款表放款时失败！");
        }

        //6、写入资金状况表
    }

    //type 还款类型：当期还款=1；提前还款=2
    @Transactional
    public Map payAmount(long userId, Integer type) {
        //1、查找当前用户需还款明细
        List<LoanDetail> loanDetails = loanDetailDao.selectByUserId(userId);

        //2、循环还款
        int index = 0;
        for (LoanDetail loanDetail : loanDetails) {
            PayHistory payHistory = new PayHistory();
            payHistory.setLoanId(loanDetail.getLoanId());
            payHistory.setLoanDetailId(loanDetail.getId());
            payHistory.setPayAmount(loanDetail.getPayAmount());
            payHistory.setInterest(loanDetail.getInterest());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = dateFormat.parse(dateFormat.format(new Date()));
                payHistory.setDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (payHistoryDao.insert(payHistory)!=1){
                return ResultMapUtil.returnFailed("写入还款表时失败！");
            }

            LoanDetail newLoanDetail = new LoanDetail();
            newLoanDetail.setId(loanDetail.getId());
            newLoanDetail.setFinish(1);
            newLoanDetail.setFinishDate(date);
            if (loanDetailDao.updateById(newLoanDetail)!=1){
                return ResultMapUtil.returnFailed("更新借款明细表时失败！");
            }

            //当更新为最后一笔时，写入借款营销表
            if (loanDetails.size()==1||loanDetails.size()==index){

            }

            //当期还款
            if (type==1){
                break;
            }

            index++;
        }

        return ResultMapUtil.returnSuccessed();
    }
}

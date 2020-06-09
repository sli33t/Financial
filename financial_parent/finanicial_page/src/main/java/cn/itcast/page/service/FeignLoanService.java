package cn.itcast.page.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "financial-loan")
public interface FeignLoanService {

    @RequestMapping(value = "/userApply", method = RequestMethod.POST)
    Map userApply(@RequestParam("userId") long userId, @RequestParam("loanType") long loanType);

    @RequestMapping(value = "/setAccount", method = RequestMethod.POST)
    Map setAccount(@RequestParam("loanId") long loanId, @RequestParam("accountId") long accountId);
}

package cn.itcast.page.controller;

import cn.itcast.page.service.FeignLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class PageController {

    @RequestMapping(value = "/")
    public String index(HttpServletRequest request){
        request.setAttribute("text", "测试功能页面");
        return "index";
    }

    @Autowired
    private FeignLoanService feignLoanService;

    @RequestMapping(value = "/userApply", method = RequestMethod.POST)
    @ResponseBody
    public Map userApply(long userId, long loanType){
        Map map = feignLoanService.userApply(userId, loanType);
        return map;
    }


    @RequestMapping(value = "/setAccount", method = RequestMethod.POST)
    @ResponseBody
    public Map setAccount(long loanId, long accountId){
        Map map = feignLoanService.setAccount(loanId, accountId);
        return map;
    }
}

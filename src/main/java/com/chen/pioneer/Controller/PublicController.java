package com.chen.pioneer.Controller;

import com.chen.pioneer.util.UserAgentUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * java类简单作用描述
 *
 * @ProjectName: pioneer
 * @Package: com.chen.pioneer.Controller
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: guolin.chen
 * @CreateDate: 2018/6/29 13:12
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/6/29 13:12
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
@RequestMapping("/public")
@Controller
public class PublicController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(HttpServletRequest request, HttpServletResponse response) {
        UserAgentUtils.service(request);
        return "Hello 123 abc 123 123 !!!";
    }
}

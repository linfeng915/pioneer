package com.chen.pioneer.util;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * java类简单作用描述
 *
 * @ProjectName: pioneer
 * @Package: com.chen.pioneer.util
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: guolin.chen
 * @CreateDate: 2018/6/29 13:07
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/6/29 13:07
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
public class UserAgentUtils {
    public static void service(HttpServletRequest request) {
        String agentStr = request.getHeader("user-agent");
        System.out.println(agentStr);
        UserAgent agent = UserAgent.parseUserAgentString(agentStr);
        //浏览器
        Browser browser = agent.getBrowser();
        System.out.println("类型：" + browser.getBrowserType() +
                "\n名称：" + browser.getName() +
                "\n厂商：" + browser.getManufacturer() +
                "\n产品系列：" + browser.getGroup() +
                "\n引擎：" + browser.getRenderingEngine());

        //浏览器版本
        Version version = agent.getBrowserVersion();
        System.out.println("========================");
        System.out.println("主版本：" + version.getMajorVersion() +
                "\n小版本：" + version.getMinorVersion() +
                "\n完整版本：" + version.getVersion());
        //操作系统
        System.out.println("========================");
        OperatingSystem os = agent.getOperatingSystem();
        System.out.println("名称：" + os.getName() +
                "\n设备类型：" + os.getDeviceType() +
                "\n产品系列：" + os.getGroup() +
                "\n生成厂商：" + os.getManufacturer());
    }
}

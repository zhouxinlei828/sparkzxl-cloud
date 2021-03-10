package com.github.sparkzxl.report;

import com.github.sparkzxl.boot.SparkBootApplication;
import org.jeecg.modules.jmreport.common.util.oConvertUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication(scanBasePackages = {"org.jeecg.modules.jmreport"})
public class ReportApplication extends SparkBootApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(ReportApplication.class, args);
        Environment env = application.getEnvironment();
        String port = env.getProperty("server.port");
        String path = oConvertUtils.getString(env.getProperty("server.servlet.context-path"));
        System.out.print("\n----------------------------------------------------------\n\t" +
                "Application JimuReport Demo is running! Access URL:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/jmreport/list\n\t" +
                "----------------------------------------------------------");
    }
}

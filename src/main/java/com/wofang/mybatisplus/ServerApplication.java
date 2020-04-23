package com.wofang.mybatisplus;

import com.wofang.mybatisplus.util.PatternUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.regex.Pattern;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        //动态设置端口
        if(args != null && args.length > 0){
            String port = args[0];
            if(PatternUtils.isNumber(port)){
                System.setProperty("server.port",port);
            }
        }
        SpringApplication.run(ServerApplication.class, args);
    }

}

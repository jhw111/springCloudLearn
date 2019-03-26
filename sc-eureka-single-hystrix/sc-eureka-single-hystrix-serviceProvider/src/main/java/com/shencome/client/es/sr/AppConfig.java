///*
// * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
// * FileName: AppConfig.java
// * Author:   16040985
// * Date:     2018年12月13日 上午11:43:25
// * Description: //模块目的、功能描述      
// * History: //修改记录
// * <author>      <time>      <version>    <desc>
// * 修改人姓名             修改时间            版本号                  描述
// */
//package com.shencome.client.es.sr;
//
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
//
///**
// * 〈一句话功能简述〉
// * <p>    〈功能详细描述〉
// * @author     16040985
// * @see        [相关类/方法]（可选）
// * @since      [产品/模块版本] （可选）
// */
//@Configuration
//public class AppConfig {
//    @Bean
//    public ServletRegistrationBean getServlet() {
//        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
//        registrationBean.setLoadOnStartup(1);
//        registrationBean.addUrlMappings("/actuator/hystrix.stream");
//        registrationBean.setName("HystrixMetricsStreamServlet");
//        return registrationBean;
//    }
//}

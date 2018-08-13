package com.wgdetective.projectstartdemo.config;

import com.wgdetective.projectstartdemo.ProjectStartDemoApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class WebInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProjectStartDemoApplication.class);
    }
}

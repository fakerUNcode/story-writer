package com.easylive.entity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${project.folder:}")
    private String projectFolder;

    @Value("${admin.account:}")
    private String adminAccount;

    @Value("${admin.password:}")
    private String adminPassword;

    @Value("${showFFmpegLog:true}")
    private boolean showFFmpegLog;

    @Value("${es.host.port:127.0.0.1:9200}")
    private String esHostPort;

    @Value("${es.index.video.name:easylive_video}")
    private String esIndexVideoName;

    public boolean isShowFFmpegLog() {
        return showFFmpegLog;
    }

    public String getEsHostPort() {
        return esHostPort;
    }

    public String getEsIndexVideoName() {
        return esIndexVideoName;
    }

    public String getProjectFolder() {
        return projectFolder;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public boolean getShowFFmpegLog() {
        return showFFmpegLog;
    }
}

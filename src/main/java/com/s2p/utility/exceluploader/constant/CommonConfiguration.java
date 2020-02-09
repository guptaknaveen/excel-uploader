package com.s2p.utility.exceluploader.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class CommonConfiguration {

    @Value("${config-service.excelFile.maxUploadSize:1048576}") // 1 MB
    private long maxUploadSize;

    @Value("${config-service.excelFile.fileUploadFolderPath:/tmp/}")
    private String fileUploadFolderPath;

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(maxUploadSize);
        return multipartResolver;
    }

    public long getMaxUploadSize() {
        return maxUploadSize;
    }

    public String getFileUploadFolderPath() {
        return fileUploadFolderPath;
    }
}

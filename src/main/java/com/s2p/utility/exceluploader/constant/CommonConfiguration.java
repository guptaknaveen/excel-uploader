package com.s2p.utility.exceluploader.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    @Value("${config-service.excelFile.maxUploadSize:1048576}") // 1 MB
    private long maxUploadSize;

    @Value("${config-service.excelFile.fileUploadFolderPath:/tmp")
    private String fileUploadFolderPath;

    @Value("${config-service.excelFile.fileCreateFolderPath:/tmp}")
    private String fileCreateFolderPath;

    public long getMaxUploadSize() {
        return maxUploadSize;
    }

    public String getFileUploadFolderPath() {
        return fileUploadFolderPath;
    }

    public String getFileCreateFolderPath() {
        return fileCreateFolderPath;
    }
}

package com.s2p.utility.exceluploader.controller;

import com.s2p.utility.exceluploader.constant.CommonConfiguration;
import com.s2p.utility.exceluploader.logger.Logger;
import com.s2p.utility.exceluploader.model.Data;
import com.s2p.utility.exceluploader.model.Field;
import com.s2p.utility.exceluploader.model.MetaData;
import com.s2p.utility.exceluploader.model.TableData;
import com.s2p.utility.exceluploader.service.DataManager;
import com.s2p.utility.exceluploader.service.MetaDataManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/metadata")
public class MetaDataController {

    private static Logger logger = Logger.getLogger();

    @Autowired
    private MetaDataManager manager;

    @Autowired
    private CommonConfiguration configuration;

    @GetMapping("/")
    public String list(Model model) {
        List<MetaData> metaDataList = manager.fetchAllMetaData();
        model.addAttribute("metaDataList", metaDataList);
        return "metaDataList";
    }

    @GetMapping("/upload")
    public String upload() {
        return "addMetaDataFromExcel";
    }

    @PostMapping("/uploadExcel")
    public String excelFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("metaDataName") String metaDataName,
                                   @RequestParam("metaDataDescription") String metaDataDescription,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/metadata/uploadStatus";
        }

        if (StringUtils.isEmpty(metaDataName)) {
            metaDataName = file.getOriginalFilename();
        }

        try {
            String filePath = configuration.getFileUploadFolderPath() + file.getOriginalFilename();

            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);

            logger.info("Successfully saved file to location : " + filePath);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

            MetaData metaData = manager.readMetaDataFromExcel(new File(filePath));
            metaData.setName(metaDataName);
            metaData.setDescription(metaDataDescription);

            manager.saveMetaData(metaData);

            logger.info("Metadata saved : "+metaData);

        } catch (IOException e) {
            logger.info("Exception occurred during file upload process due to " + e.getMessage());
        }

        return "redirect:/metadata/";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    @GetMapping("/data/{metaDataId}")
    public String metaDataDetail(Model model, @PathVariable("metaDataId") String metaDataId) {
        TableData tableData = new TableData();
        model.addAttribute("tableData", tableData);
        return "metaDataDetails";
    }
}
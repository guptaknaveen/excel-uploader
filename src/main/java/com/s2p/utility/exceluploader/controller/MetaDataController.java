package com.s2p.utility.exceluploader.controller;

import com.s2p.utility.exceluploader.logger.Logger;
import com.s2p.utility.exceluploader.model.Data;
import com.s2p.utility.exceluploader.model.Field;
import com.s2p.utility.exceluploader.model.MetaData;
import com.s2p.utility.exceluploader.service.DataManager;
import com.s2p.utility.exceluploader.service.MetaDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/metadata-module/addNew")
@SessionAttributes("metadata")
public class MetaDataController {

    private static Logger logger = Logger.getLogger();

    @Autowired
    private MetaDataManager manager;

    @Autowired
    private DataManager dataManager;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model) {
        try {
            manager.saveMetaDataFromExcel("MetaData2", new File("/Users/naveen/Projects/excel-uploader/src/main/resources/MetaData.xls"));

            logger.info("Testing" + manager.fetchMetaData("MetaData"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        MetaData metaData = new MetaData("");
        metaData.setFields(createEmptyList());
        model.addAttribute("metadata", metaData);
        model.addAttribute("metadataFields", metaData.getFields());
        return "addMetaData";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("metadata") MetaData metadata,
                             BindingResult result, SessionStatus status) {
        logger.info(metadata);

        manager.addMetaData(metadata);

        status.setComplete();
        return "redirect:addNew/success";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success(Model model) {
        return "addSuccess";
    }

    private List<Field> createEmptyList() {
        List<Field> fields = new ArrayList<>();
        for (int i=0; i<10; i++) {
            fields.add(new Field());
        }
        return fields;
    }
}
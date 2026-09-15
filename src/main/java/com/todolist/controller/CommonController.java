package com.todolist.controller;

import com.todolist.entity.Record;
import com.todolist.entity.RecordStatus;
import com.todolist.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CommonController {

    private final RecordService recordService;

    @Autowired
    public CommonController(RecordService recordService){
        this.recordService = recordService;
    }

    @RequestMapping("/home")
    public String getMainPage(Model model){
        List<Record> records = recordService.findAllRecords();
        int numberOfActiveRecords = (int) records.stream().filter(record -> record.getStatus() == RecordStatus.ACTIVE).count();
        int numberOfDoneRecords = (int) records.stream().filter(record -> record.getStatus() == RecordStatus.DONE).count();
        model.addAttribute("numberOfDoneRecords", numberOfDoneRecords);
        model.addAttribute("numberOfActiveRecords", numberOfActiveRecords);
        model.addAttribute("records", records);
        return "main-page";
    }
}

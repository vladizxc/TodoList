package com.todolist.controller;

import com.todolist.entity.Record;
import com.todolist.entity.RecordStatus;
import com.todolist.entity.dto.RecordsContainerDto;
import com.todolist.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CommonController {

    private final RecordService recordService;

    @Autowired
    public CommonController(RecordService recordService){
        this.recordService = recordService;
    }

    @RequestMapping("/")
    public String redirectToHomePage(){
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String getMainPage(Model model, @RequestParam(name="filter", required = false) String filterMode){
        RecordsContainerDto container = recordService.findAllRecords(filterMode);

        model.addAttribute("numberOfDoneRecords", container.getNumberOfDoneRecords());
        model.addAttribute("numberOfActiveRecords", container.getNumberOfActiveRecords());
        model.addAttribute("records", container.getRecords());

        return "main-page";
    }

    @RequestMapping(value = "/add-record", method = RequestMethod.POST)
    public String addRecord(@RequestParam String title){
        recordService.saveRecord(title);
        return "redirect:/home";
    }

    @RequestMapping(value = "/make-record-done", method = RequestMethod.POST)
    public String makeRecordDone(@RequestParam int id,
                                 @RequestParam(name="filter", required = false) String filterMode){
        recordService.setRecordStatus(id, RecordStatus.DONE);
        return "redirect:/home" + (!filterMode.isBlank() && filterMode != null ? "?filter=" + filterMode : "");
    }

    @RequestMapping(value = "/delete-record", method = RequestMethod.POST)
    public String deleteRecord(@RequestParam int id,
                               @RequestParam(name="filter", required = false) String filterMode ){
        recordService.deleteRecord(id);
        return "redirect:/home" + (!filterMode.isBlank() && filterMode != null ? "?filter=" + filterMode : "");
    }
}

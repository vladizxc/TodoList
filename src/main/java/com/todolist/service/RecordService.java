package com.todolist.service;

import com.todolist.dao.RecordDao;
import com.todolist.entity.Record;
import com.todolist.entity.RecordStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    private final RecordDao recordDao;

    @Autowired
    public RecordService(RecordDao recordDao){
        this.recordDao = recordDao;
    }

    public List<Record> findAllRecords(){
        return recordDao.findALlRecords();
    }

    public void saveRecord(String title){
        if(title != null && !title.isBlank()) {
            recordDao.saveRecord(new Record(title));
        }
    }
}

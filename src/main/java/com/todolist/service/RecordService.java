package com.todolist.service;

import com.todolist.dao.RecordDao;
import com.todolist.entity.Record;
import com.todolist.entity.RecordStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class RecordService {
    private final RecordDao recordDao;

    @Autowired
    public RecordService(RecordDao recordDao){
        this.recordDao = recordDao;
    }

    public List<Record> findAllRecords(String filterMode){
        List<Record> records = recordDao.findALlRecords();
        if (filterMode == null || filterMode.isBlank()) return records;

        String filterModeInUpperCase = filterMode.toUpperCase();

        List<String> allowedFilterModes = Arrays.stream(RecordStatus.values())
                .map(Enum::name)
                .toList();
        if (allowedFilterModes.contains(filterModeInUpperCase)){
            return records.stream().filter(record -> record.getStatus() == RecordStatus.valueOf(filterModeInUpperCase))
                    .toList();
        } else {
            return records;
        }
    }

    public void saveRecord(String title){
        if(title != null && !title.isBlank()) {
            recordDao.saveRecord(new Record(title));
        }
    }

    public void setRecordStatus(int id, RecordStatus newStatus){
        recordDao.updateRecordStatus(id, newStatus);
    }

    public void deleteRecord(int id){
        recordDao.deleteRecord(id);
    }
}

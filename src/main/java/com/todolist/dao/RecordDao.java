package com.todolist.dao;

import com.todolist.entity.Record;
import com.todolist.entity.RecordStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RecordDao {
    private final List<Record> records = new ArrayList<>(
            Arrays.asList(
                    new Record("Take a shower", RecordStatus.ACTIVE),
                    new Record("Buy flowers", RecordStatus.DONE),
                    new Record("Go to the gym", RecordStatus.ACTIVE)
            ));

    public List<Record> findALlRecords(){
        return new ArrayList<>(records);
    }

    public void saveRecord(Record record){
        records.add(record);
    }

    public void updateRecordStatus(String title, RecordStatus status){
        for (Record item : records){
           if (item.getTitle().equals(title)){
               item.setStatus(status);
               break;
           }
        }
    }

    public void deleteRecord(String title){
        records.removeIf(item -> item.getTitle().equals(title));
    }
}

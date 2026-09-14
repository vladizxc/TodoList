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
                    new Record("Buy flowers", RecordStatus.ACTIVE),
                    new Record("Go to the gym", RecordStatus.ACTIVE)
            ));

    public List<Record> findALlRecords(){
        return new ArrayList<>(records);
    }
}

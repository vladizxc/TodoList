package com.todolist.dao;

import com.todolist.entity.Record;
import com.todolist.entity.RecordStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class RecordDao {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public RecordDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Record> findALlRecords(){

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("SELECT r FROM Record r");
            List<Record> records = query.getResultList();

            entityManager.getTransaction().commit();
            return records;
        }catch (Exception exception){
            exception.printStackTrace();
            entityManager.getTransaction().rollback();
            return Collections.emptyList();
        }finally {
            entityManager.close();
        }

    }

    public void saveRecord(Record record){
        records.add(record);
    }

    public void updateRecordStatus(int id, RecordStatus status){
        for (Record item : records){
           if (item.getId() == id){
               item.setStatus(status);
               break;
           }
        }
    }

    public void deleteRecord(int id){
        records.removeIf(item -> item.getId() == id);
    }
}

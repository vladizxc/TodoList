package com.todolist.dao;

import com.todolist.entity.Record;
import com.todolist.entity.RecordStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class RecordDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Record> findALlRecords(){
        Query query = entityManager.createQuery("SELECT r FROM Record r ORDER BY r.id ASC");
        List<Record> records = query.getResultList();
        return records;
    }

    @Transactional
    public void saveRecord(Record record){
        entityManager.persist(record);
    }

    @Transactional
    public void updateRecordStatus(int id, RecordStatus status){
        Query query = entityManager.createQuery("UPDATE Record SET status = :status WHERE id = :id");
        query.setParameter("status", status);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public void deleteRecord(int id){
        Query query = entityManager.createQuery("DELETE FROM Record WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}

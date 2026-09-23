package com.todolist.dao;

import com.todolist.entity.Record;
import com.todolist.entity.RecordStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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


    public List<Record> findALlRecords(){
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
        }
    }

    public void saveRecord(Record record){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(record);
            entityManager.getTransaction().commit();
        }catch (Exception exception){
            exception.printStackTrace();
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
    }

    public void updateRecordStatus(int id, RecordStatus status){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

//            Record record = entityManager.find(Record.class, id);
//            record.setStatus(status);
//            record = entityManager.merge(record);

            Query query = entityManager.createQuery("UPDATE Record SET status = :status WHERE id = :id");
            query.setParameter("status", status);
            query.setParameter("id", id);
            query.executeUpdate();

            entityManager.getTransaction().commit();
        }catch (Exception exception){
            exception.printStackTrace();
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
    }

    public void deleteRecord(int id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("DELETE FROM Record WHERE id = :id");
            query.setParameter("id", id);
            query.executeUpdate();

            entityManager.getTransaction().commit();
        }catch (Exception exception){
            exception.printStackTrace();
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
    }
}

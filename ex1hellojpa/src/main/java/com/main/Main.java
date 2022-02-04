package com.main;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.vo.*;
import org.hibernate.Criteria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("jpabook");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{

            Member member = new Member();
            member.setName("member1");
            em.persist(member);

            List<Member> resultList = em.createNativeQuery("select MEMBER_ID, city, street, zipcode from Member m").getResultList();

            System.out.println("=============================");
            for(Member m : resultList){
                System.out.println("member1= " + m.getName());
            }
            System.out.println("=============================");


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}

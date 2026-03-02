package com.mukesh.hibernatetask.repository;

import com.mukesh.hibernatetask.entity.Staff;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class StaffRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    // finding all the staff members
    public List<Staff> findAll() {
        return getSession().createQuery("from Staff", Staff.class).list();
    }

    // finding staff member with given ID
    public Staff findById(Integer id) {
        return getSession().find(Staff.class, id);
    }

    // Saving the staff member
    public Staff save(Staff staff) {
        getSession().persist(staff);
        return staff;
    }

    // Salary > ?
    public List<Staff> findBySalaryGreaterThan(Integer salary) {
        String hql = "from Staff where salary > :salary";
        Query<Staff> query = getSession().createQuery(hql, Staff.class);
        query.setParameter("salary", salary);
        return query.list();
    }

    // update Staff Salary
    public int updateStaffSalary(Integer id, Double amount) {
        String hql = "update Staff set salary = :amount where staffId = :id";
        Query<Staff> query = getSession().createQuery(hql);
        query.setParameter("amount", amount);
        query.setParameter("id", id);
        return query.executeUpdate();
    }


    // Experience between
    public List<Staff> findByExperienceBetween(Integer min, Integer max) {
        String hql = "from Staff where experience between :min and :max";
        Query<Staff> query = getSession().createQuery(hql, Staff.class);
        query.setParameter("min", min);
        query.setParameter("max", max);
        return query.list();
    }

    // Profile equals
    public List<Staff> findByProfile(String profile) {
        String hql = "from Staff where profile = :profile";
        Query<Staff> query = getSession().createQuery(hql, Staff.class);
        query.setParameter("profile", profile);
        return query.list();
    }

    //Profile not equals
    public List<Staff> findByProfileNotEquals(String profile) {
        String hql = "from Staff where profile != :profile";
        Query<Staff> query = getSession().createQuery(hql, Staff.class);
        query.setParameter("profile", profile);
        return query.list();
    }

}

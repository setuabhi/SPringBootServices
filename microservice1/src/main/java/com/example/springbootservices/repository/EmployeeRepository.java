package com.example.springbootservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("Select e from Employee e where e.id=?1 and e.name=?2")
    public List<Employee> findByIdAndName(Long id, String name);

    @Modifying
    @Transactional
    @Query("Delete from Employee e")
    public void deleteAllData();
}


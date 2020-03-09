package com.kforce.rewards.repository;

import com.kforce.rewards.entity.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<CustomersEntity, Integer> {

    CustomersEntity findCustomersEntityByUserName(String userName);

//    Page<CustomersRepository> findAllByPage(Pageable pageable);
}

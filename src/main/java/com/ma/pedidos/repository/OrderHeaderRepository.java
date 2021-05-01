package com.ma.pedidos.repository;

import com.ma.pedidos.entity.OrderHeaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderHeaderRepository extends JpaRepository<OrderHeaderEntity, Long> {

    @Query(value = "SELECT * FROM pedidos_cabecera WHERE fecha_alta = :date", nativeQuery = true)
    List<OrderHeaderEntity> listFromDate(@Param("date") LocalDate date);

}

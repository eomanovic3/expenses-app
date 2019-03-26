package com.expenses.demo.repositories;

import com.expenses.demo.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    Location findByLocationId(Integer id);

}

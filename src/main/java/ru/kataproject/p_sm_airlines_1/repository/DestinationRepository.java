package ru.kataproject.p_sm_airlines_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kataproject.p_sm_airlines_1.entity.Destination;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    @Query("SELECT d FROM Destination d WHERE d.city in :city")
    List<Destination> getByCity(@Param("city") String city);

    @Query("SELECT d FROM Destination d WHERE d.countryName in :countryName")
    List<Destination> getByCountry(@Param("countryName") String countryName);
}
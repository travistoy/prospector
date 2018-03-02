package org.launchcode.prospector6.models.data;


import org.launchcode.prospector6.models.Prospect;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProspectDao extends CrudRepository<Prospect, Integer>{

    public long count();

    @Query(value = "SELECT coalesce(SUM(p.premium),0) from Prospect p", nativeQuery = true)
        double getTotalPremium();

    @Query(value = "SELECT coalesce(SUM(p.commission),0) from Prospect p", nativeQuery = true)
    double getTotalCommission();

    List<Prospect> findByPremium(double premium);




}

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

    Prospect findByIdAndUserId(Integer id, Integer userid);

    long countByUserId(Integer userid);

    @Query(value = "SELECT coalesce(SUM(p.premium),0) from Prospect p WHERE user_id = ?1", nativeQuery = true)
        double getTotalPremiumByUserId(Integer userid);

    @Query(value = "SELECT coalesce(SUM(p.commission),0) from Prospect p WHERE user_id = ?1", nativeQuery = true)
        double getTotalCommissionByUserId(Integer userid);

    @Query(value = "SELECT coalesce(COUNT(p.quote_date),0) from Prospect p WHERE user_id = ?1", nativeQuery = true)
    double getQuoteDateByUserId(Integer userid);

    @Query(value = "SELECT coalesce(COUNT(p.sold_date),0) from Prospect p WHERE user_id = ?1", nativeQuery = true)
    double getSoldDateByUserId(Integer userid);

    List<Prospect>findByUserId(Integer userId);




}

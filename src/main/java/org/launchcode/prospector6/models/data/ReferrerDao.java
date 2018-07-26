package org.launchcode.prospector6.models.data;


import org.launchcode.prospector6.models.Referrer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ReferrerDao extends CrudRepository<Referrer, Integer>{

    Referrer findByIdAndUserId(Integer id, Integer userid);
    List<Referrer> findByUserId(Integer userid);

}

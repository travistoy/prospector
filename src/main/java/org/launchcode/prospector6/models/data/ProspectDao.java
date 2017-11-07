package org.launchcode.prospector6.models.data;


import org.launchcode.prospector6.models.Prospect;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface ProspectDao extends CrudRepository<Prospect, Integer>{

    public long countByName(String name);

    public long countBySoldDate(LocalDate soldDate);

    public long countByQuoteDate(LocalDate quoteDate);

    List<Prospect> findByPremium(double premium);


}

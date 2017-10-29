package org.launchcode.prospector6.models.data;


import org.launchcode.prospector6.models.Prospect;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProspectDao extends CrudRepository<Prospect, Integer>{
}

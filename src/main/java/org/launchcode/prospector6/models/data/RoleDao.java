package org.launchcode.prospector6.models.data;


import org.launchcode.prospector6.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoleDao extends CrudRepository<Role, Integer> {
}

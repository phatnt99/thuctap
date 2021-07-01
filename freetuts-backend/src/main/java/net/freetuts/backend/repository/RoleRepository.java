package net.freetuts.backend.repository;

import net.freetuts.backend.entity.Role;

public interface RoleRepository extends GenericReposity<Role, Integer> {

	Role findByName(String name);

}

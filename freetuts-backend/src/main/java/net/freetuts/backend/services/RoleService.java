package net.freetuts.backend.services;

import net.freetuts.backend.entity.Role;

public interface RoleService extends GenericService<Role, Integer> {

	Role findByName(String name);
}

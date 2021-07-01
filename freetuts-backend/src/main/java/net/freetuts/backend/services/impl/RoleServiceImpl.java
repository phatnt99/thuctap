package net.freetuts.backend.services.impl;

import java.lang.invoke.MethodHandles;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.freetuts.backend.entity.Role;
import net.freetuts.backend.repository.RoleRepository;
import net.freetuts.backend.services.RoleService;

/**
 * The Class RoleServiceImpl.
 */
@Service
@Transactional
public class RoleServiceImpl extends GernericServiceImpl<Role, Integer>
		implements RoleService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MethodHandles.lookup().lookupClass());

	/** The role repository. */
	private RoleRepository roleRepository;

	/**
	 * Instantiates a new role service impl.
	 *
	 * @param roleRepository the role repository
	 */
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		super(roleRepository);
	}

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the role
	 */
	@Override
	public Role findByName(String name) {
		LOGGER.info("Find By Name : {}", name);
		return roleRepository.findByName(name);
	}

}

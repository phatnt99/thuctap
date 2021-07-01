package net.freetuts.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericReposity<T, ID> extends JpaRepository<T, ID> {

}

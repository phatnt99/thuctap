package net.freetuts.backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.freetuts.backend.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID>{

	List<Invoice> findAllByCourseIdOrderByCreatedAtDesc(UUID id);
}

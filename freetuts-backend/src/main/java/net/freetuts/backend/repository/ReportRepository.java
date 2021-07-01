package net.freetuts.backend.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import net.freetuts.backend.entity.Report;

@Repository
public interface ReportRepository extends GenericReposity<Report, UUID> {

}

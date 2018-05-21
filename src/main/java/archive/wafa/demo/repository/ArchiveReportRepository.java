package archive.wafa.demo.repository;

import archive.wafa.demo.model.ArchiveReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveReportRepository extends JpaRepository<ArchiveReport,Long> {
}

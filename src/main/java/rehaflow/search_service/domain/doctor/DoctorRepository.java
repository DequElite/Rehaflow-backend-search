package rehaflow.search_service.domain.doctor;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DoctorRepository extends ElasticsearchRepository<DoctorDocument, UUID> {
}

package rehaflow.search_service.domain.doctor_patient;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import rehaflow.search_service.domain.doctor.DoctorDocument;

import java.util.UUID;

@Repository
public interface DoctorPatientRepository extends ElasticsearchRepository<DoctorDocument, UUID> {
}

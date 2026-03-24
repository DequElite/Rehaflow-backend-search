package rehaflow.search_service.domain.hospital;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HospitalRepository extends ElasticsearchRepository<HospitalDocument, UUID> {
}

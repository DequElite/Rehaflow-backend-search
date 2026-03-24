package rehaflow.search_service.domain.protocol;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProtocolRepository extends ElasticsearchRepository<ProtocolDocument, UUID> {
}

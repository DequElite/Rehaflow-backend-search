package rehaflow.search_service.app.index;

import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;
import rehaflow.search_service.domain.search.IndexMapping;
import rehaflow.search_service.domain.search.SearchIndex;
import rehaflow.search_service.grpc.IndexDocumentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class IndexDocumentService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final ObjectMapper objectMapper;

    public void indexDocument(IndexDocumentRequest request) {

        if (!SearchIndex.isValid(request.getIndex())) {
            throw new IllegalArgumentException("Invalid index: " + request.getIndex());
        }

        IndexOperations indexOperations =
                elasticsearchOperations.indexOps(IndexMapping.INDEX_TO_CLASS.get(request.getIndex()));

        if (!indexOperations.exists()) {
            throw new RuntimeException("Index not found");
        }

        Class<?> docClass = IndexMapping.INDEX_TO_CLASS.get(request.getIndex());
        Object document = objectMapper.convertValue(request.getDataMap(), docClass);

        IndexQuery query = new IndexQueryBuilder()
                .withId(request.getBaseId())
                .withObject(document)
                .build();

        elasticsearchOperations.index(
                query,
                IndexCoordinates.of(request.getIndex())
        );
    }
}

package rehaflow.search_service.app.index;

import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.stereotype.Service;
import rehaflow.search_service.domain.search.IndexMapping;
import rehaflow.search_service.domain.search.SearchIndex;
import rehaflow.search_service.grpc.IndexDocumentRequest;
import rehaflow.search_service.grpc.IndexDocumentResponse;

@Service
@RequiredArgsConstructor
public class IndexDocumentService {

    private final ElasticsearchOperations elasticsearchOperations;

    public void indexDocument(IndexDocumentRequest request) {
        if (!SearchIndex.isValid(request.getIndex())) {
            throw new IllegalArgumentException("Invalid index: " + request.getIndex());
        }

        IndexOperations indexOperations =
                elasticsearchOperations.indexOps(IndexMapping.INDEX_TO_CLASS.get(request.getIndex()));
        if(!indexOperations.exists()) {
            throw new RuntimeException("Index not found");
        }

        Document document = Document.create();
        document.putAll(request.getDataMap());

        elasticsearchOperations.save(document, request.getIndex(), request.getDataMap().get("baseId"));
    }
}

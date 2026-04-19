package rehaflow.search_service.app.search;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import rehaflow.search_service.domain.search.IndexMapping;
import rehaflow.search_service.domain.search.SearchIndex;
import rehaflow.search_service.grpc.SearchRequest;
import rehaflow.search_service.grpc.SearchResponse;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

    private final ElasticsearchOperations elasticsearchOperations;

    public SearchResponse search(SearchRequest request) {
        if (!SearchIndex.isValid(request.getIndex())) {
            throw new IllegalArgumentException("Invalid index: " + request.getIndex());
        }

        IndexOperations indexOperations =
                elasticsearchOperations.indexOps(IndexMapping.INDEX_TO_CLASS.get(request.getIndex()));

        if (!indexOperations.exists()) {
            throw new RuntimeException("Index not found");
        }

        List<String> searchFields = IndexMapping.INDEX_TO_SEARCH_FIELDS.get(request.getIndex());

        Query mainQuery = Query.of(q -> q
                .multiMatch(mm -> mm
                        .query(request.getQuery())
                        .fields(searchFields)
                        .fuzziness("AUTO")
                )
        );

        List<Query> filters = request.getFiltersMap().entrySet().stream()
                .map(entry -> Query.of(q ->
                        q.term(t -> t.field(entry.getKey()).value(entry.getValue()))
                ))
                .toList();

        Query query = Query.of(q -> q
                .bool(b -> b
                        .must(mainQuery)
                        .filter(filters)
                )
        );

        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(query)
                .build();

        SearchHits<?> searchHits = elasticsearchOperations.search(
                nativeQuery,
                IndexMapping.INDEX_TO_CLASS.get(request.getIndex())
        );

        log.info("Search hits count: {}", searchHits.getTotalHits());

        List<String> ids = searchHits.stream()
                .map(SearchHit::getId)
                .toList();

        return SearchResponse.newBuilder()
                .setTotal(searchHits.getTotalHits())
                .addAllIds(ids)
                .build();
    }
}

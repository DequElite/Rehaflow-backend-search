package rehaflow.search_service.app.search;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;
import rehaflow.search_service.grpc.IndexDocumentResponse;
import rehaflow.search_service.grpc.SearchRequest;
import rehaflow.search_service.grpc.SearchResponse;
import rehaflow.search_service.grpc.SearchServiceGrpc;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class SearchController extends SearchServiceGrpc.SearchServiceImplBase {
    private final SearchService service;

    @Override
    public void search(
            SearchRequest request,
            StreamObserver<SearchResponse> responseObserver
    ){
        try {
            log.info("SEARCH req: {}", request);

            SearchResponse response = service.search(request);

            log.info("SEARCH res: {}", response);

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (IllegalArgumentException e) {

            log.error("SEARCH ERROR: ", e);

            responseObserver.onError(
                    Status.INVALID_ARGUMENT
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );

        } catch (Exception e) {

            log.error("SEARCH ERROR: ", e);

            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription("Internal server error")
                            .asRuntimeException()
            );
        }
    }
}

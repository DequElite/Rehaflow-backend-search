package rehaflow.search_service.app.search;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;
import rehaflow.search_service.grpc.IndexDocumentResponse;
import rehaflow.search_service.grpc.SearchRequest;
import rehaflow.search_service.grpc.SearchResponse;
import rehaflow.search_service.grpc.SearchServiceGrpc;

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
            SearchResponse response = service.search(request);

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (IllegalArgumentException e) {

            responseObserver.onError(
                    Status.INVALID_ARGUMENT
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );

        } catch (Exception e) {

            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription("Internal server error")
                            .asRuntimeException()
            );
        }
    }
}

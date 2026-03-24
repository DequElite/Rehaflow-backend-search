package rehaflow.search_service.app.index;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;
import rehaflow.search_service.grpc.IndexDocumentRequest;
import rehaflow.search_service.grpc.IndexDocumentResponse;
import rehaflow.search_service.grpc.IndexDocumentServiceGrpc;

@GrpcService
@RequiredArgsConstructor
public class IndexDocumentController extends IndexDocumentServiceGrpc.IndexDocumentServiceImplBase {
    private final IndexDocumentService service;

    @Override
    public void indexDocument(IndexDocumentRequest request,
                              StreamObserver<IndexDocumentResponse> responseObserver) {
        try {
            service.indexDocument(request);

            IndexDocumentResponse response = IndexDocumentResponse.newBuilder()
                    .setStatus(true)
                    .setSuccess(true)
                    .setError(false)
                    .build();

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

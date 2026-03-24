package rehaflow.search_service.domain.protocol;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import rehaflow.search_service.domain.search.IndexableDocument;
import rehaflow.search_service.domain.search.SearchIndex;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProtocolDto implements IndexableDocument {
    private String type;
    private String hospitalName;
    private String doctorName;
    private String protocolName;
    private String direction;
    private LocalDate createdAt;
    private String rehabilitationType;
    private String clinicalState;
    private Integer durationDays;
    private List<String> goals;
    private Integer phasesNumber;
    private String doctorId;
    private String hospitalId;
    private String baseId;

    @Override
    public String getIndexName(){
        return SearchIndex.PROTOCOLS.getIndex();
    }
}

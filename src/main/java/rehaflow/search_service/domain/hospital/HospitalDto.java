package rehaflow.search_service.domain.hospital;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import rehaflow.search_service.domain.search.IndexableDocument;
import rehaflow.search_service.domain.search.SearchIndex;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDto implements IndexableDocument {

    private String hospitalName;
    private String country;
    private String city;
    private String type;
    private String baseId;

    @Override
    public String getIndexName(){
        return SearchIndex.HOSPITALS.getIndex();
    }
}

package rehaflow.search_service.domain.doctor;

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
public class DoctorDto implements IndexableDocument {

    private String fullName;
    private String country;
    private String city;
    private String specialization;
    private String base_id;

    @Override
    public String getIndexName(){
        return SearchIndex.DOCTORS.getIndex();
    }
}

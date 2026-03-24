package rehaflow.search_service.domain.doctor_patient;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import rehaflow.search_service.domain.search.IndexableDocument;
import rehaflow.search_service.domain.search.SearchIndex;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorPatientDto implements IndexableDocument {
    private String fullName;
    private LocalDate birthdate;
    private String gender;
    private String baseId;

    @Override
    public String getIndexName(){
        return SearchIndex.DOCTOR_PATIENTS.getIndex();
    }
}

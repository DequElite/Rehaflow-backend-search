package rehaflow.search_service.domain.doctor_patient;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.UUID;

@Document(indexName = "doctor_patients")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorPatientDocument {
    @Id
    private UUID id;

    @Field(type = FieldType.Text)
    private String fullName;

    @Field(type = FieldType.Date)
    private LocalDate birthdate;

    @Field(type = FieldType.Keyword)
    private String gender;

    @Field(type = FieldType.Keyword)
    private String baseId;

    @Field(type = FieldType.Keyword)
    private String doctorId;
}

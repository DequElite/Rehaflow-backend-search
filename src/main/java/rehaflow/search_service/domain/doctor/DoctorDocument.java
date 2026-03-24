package rehaflow.search_service.domain.doctor;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Document(indexName = "doctors")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDocument {
    @Id
    private UUID id;

    @Field(type = FieldType.Text)
    private String fullName;

    @Field(type = FieldType.Keyword)
    private String country;

    @Field(type = FieldType.Keyword)
    private String city;

    @Field(type = FieldType.Text)
    private String specialization;

    @Field(type = FieldType.Keyword)
    private String baseId;

    @Field(type = FieldType.Keyword)
    private String hospitalId;
}

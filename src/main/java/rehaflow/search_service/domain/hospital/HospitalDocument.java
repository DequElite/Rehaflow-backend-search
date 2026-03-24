package rehaflow.search_service.domain.hospital;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.UUID;

@Document(indexName = "hospitals")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDocument {
    @Id
    private UUID id;

    @Field(type = FieldType.Text)
    private String hospitalName;

    @Field(type = FieldType.Keyword)
    private String country;

    @Field(type = FieldType.Keyword)
    private String city;

    @Field(type = FieldType.Text)
    private String type;

    @Field(type = FieldType.Keyword)
    private String baseId;

    @Field(type = FieldType.Keyword)
    private List<String> doctorsIds;
}

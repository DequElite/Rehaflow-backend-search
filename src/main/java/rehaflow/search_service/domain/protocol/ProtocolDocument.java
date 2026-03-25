package rehaflow.search_service.domain.protocol;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document(indexName = "protocols")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProtocolDocument {
    @Id
    private UUID baseId;

    @Field(type = FieldType.Keyword)
    private String type;

    @Field(type = FieldType.Keyword)
    private String hospitalName;

    @Field(type = FieldType.Keyword)
    private String doctorName;

    @Field(type = FieldType.Text)
    private String protocolName;

    @Field(type = FieldType.Text)
    private String direction;

    @Field(type = FieldType.Date)
    private LocalDate createdAt;

    @Field(type = FieldType.Text)
    private String rehabilitationType;

    @Field(type = FieldType.Keyword)
    private String clinicalState;

    @Field(type = FieldType.Integer)
    private Integer durationDays;

    @Field(type = FieldType.Text)
    private List<String> goals;

    @Field(type = FieldType.Integer)
    private Integer phasesNumber;

    @Field(type = FieldType.Keyword)
    private String doctorId;

    @Field(type = FieldType.Keyword)
    private String hospitalId;
}

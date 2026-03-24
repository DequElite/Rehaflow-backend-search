package rehaflow.search_service.domain.search;

import rehaflow.search_service.domain.doctor.DoctorDocument;
import rehaflow.search_service.domain.doctor_patient.DoctorPatientDocument;
import rehaflow.search_service.domain.hospital.HospitalDocument;
import rehaflow.search_service.domain.protocol.ProtocolDocument;

import java.util.Map;

public class IndexMapping {
    public static final Map<String, Class<?>> INDEX_TO_CLASS = Map.of(
            "doctors", DoctorDocument.class,
            "hospitals", HospitalDocument.class,
            "protocols", ProtocolDocument.class,
            "doctor_patients", DoctorPatientDocument.class
    );

}

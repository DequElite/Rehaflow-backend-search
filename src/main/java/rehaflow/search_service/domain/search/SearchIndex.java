package rehaflow.search_service.domain.search;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SearchIndex {
    DOCTORS("doctors"),
    HOSPITALS("hospitals"),
    PROTOCOLS("protocols"),
    DOCTOR_PATIENTS("doctor_patients");

    private final String index;

    public static boolean isValid(String value) {
        for (SearchIndex si : SearchIndex.values()) {
            if(si.getIndex().equals(value)) {
                return true;
            }
        }

        return false;
    }
}

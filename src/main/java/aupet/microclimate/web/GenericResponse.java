package aupet.microclimate.web;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@SuppressWarnings("PMD.UnusedPrivateField")
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse<T> {
    @NotNull
    private Integer errorCode;
    private String errorMessage;
    private T resultData;

    public GenericResponse(Integer errorCode, T resultData) {
        this.errorCode = errorCode;
        this.resultData = resultData;
    }

    public GenericResponse(T resultData) {
        this(0, resultData);
    }

    public static <T> GenericResponse<T> error(Integer errorCode, String errorMessage) {
        return new GenericResponse<>(errorCode, errorMessage, null);
    }
}

package aupet.microclimate.model.enums;

public enum StandartCode {
    HUMIDITY_STAND(45),
    TEMPERATURE_STAND(19),

    ;
    private final int code;

    StandartCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

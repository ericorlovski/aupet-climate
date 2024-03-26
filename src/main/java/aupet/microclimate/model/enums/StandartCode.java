package aupet.microclimate.model.enums;

public enum StandartCode {
    HUMIDITY_STAND(45),
    TEMPERATURE_STAND(19),

    ;
    private final double code;

    StandartCode(double code) {
        this.code = code;
    }

    public double getCode() {
        return code;
    }
}

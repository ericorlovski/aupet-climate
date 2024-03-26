package aupet.microclimate.model.enums;

public enum UniversityAuditorium {
    B_248_AUD("Б248")

    ;
    private final String desc;

    UniversityAuditorium(String code) {
        this.desc = code;
    }

    public String getDesc() {
        return desc;
    }
}

package br.deusmelivery.deusmelivery.suppliers.entity.DTO;

public class SuppliersComboDTO {
    private String label;
    private Long value;

    public SuppliersComboDTO(String label, Long value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}

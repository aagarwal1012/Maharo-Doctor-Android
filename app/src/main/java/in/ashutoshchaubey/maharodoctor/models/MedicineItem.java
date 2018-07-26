package in.ashutoshchaubey.maharodoctor.models;

public class MedicineItem {

    private String medicineName, days, quantity;
    private String medicineDescription;

    public MedicineItem(String medicineName, String days, String quantity) {
        this.medicineName = medicineName;
        this.days = days;
        this.quantity = quantity;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMedicineDescription() {
        return medicineDescription;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setMedicineDescription(String medicineDescription) {
        this.medicineDescription = medicineDescription;
    }
}

package in.ashutoshchaubey.maharodoctor.models;

/**
 * Created by ashutoshchaubey on 22/07/18.
 */

public class MedicineItem {

    private String medicineName;
    private int[] dosage;
    private String medicineDescription;

    public MedicineItem(String name, int[] dosage, String desc){
        medicineDescription = desc;
        this.dosage = dosage;
        medicineName = name;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public int[] getDosage() {
        return dosage;
    }

    public String getMedicineDescription() {
        return medicineDescription;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setDosage(int[] dosage) {
        this.dosage = dosage;
    }

    public void setMedicineDescription(String medicineDescription) {
        this.medicineDescription = medicineDescription;
    }
}

package in.ashutoshchaubey.maharodoctor;

/**
 * Created by ashutoshchaubey on 22/07/18.
 */

public class medicine {

    private String medicineName;
    private int[] dosage;
    private String medicineDescription;

    public medicine(String name, int[] dosage, String desc){
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

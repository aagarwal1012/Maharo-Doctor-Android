package in.ayush.mharodoctor.models.getAppointmentData.Output;

public class Info{
    String remarks;
    Medicine[] medicines;

    public Info() {
    }

    public Info(String remarks, Medicine[] medicines) {
        this.remarks = remarks;
        this.medicines = medicines;
    }

    public Medicine[] getMedicines() {
        return medicines;
    }

    public void setMedicines(Medicine[] medicines) {
        this.medicines = medicines;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
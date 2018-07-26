package in.ayush.mharodoctor.models.getAppointmentData.Output;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class AppointmentData {

    String name, doctor, time;
    int verified, completed, idsitings;
    String info;

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdsitings() {
        return idsitings;
    }

    public void setIdsitings(int idsitings) {
        this.idsitings = idsitings;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Info getInfo() {
        JSONObject jsonObject = null;
        Info info1 = null;
        try {
            if (info != null){
                jsonObject = new JSONObject(info);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        info1 = gson.fromJson(info, Info.class);
        return info1;
    }

}


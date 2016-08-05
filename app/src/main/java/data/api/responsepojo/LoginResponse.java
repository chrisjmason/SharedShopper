package data.api.responsepojo;

public class LoginResponse {
    private Boolean error;
    private String message;
    private String apikey;
    private String dataid;

    public LoginResponse(Boolean error, String message, String apikey, String dataid){
        this.error = error;
        this.message = message;
        this.apikey = apikey;
        this.dataid = dataid;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getDataid() {
        return dataid;
    }

    public void setDataid(String dataid) {
        this.dataid = dataid;
    }
}

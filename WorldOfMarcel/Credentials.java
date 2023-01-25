package WorldOfMarcel;

public class Credentials {
    private String email , password ;
    Credentials(String email , String password){
        setEmail(email) ;
        setPassword(password) ;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

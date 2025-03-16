package posttest1;

class User {
    private String nama;
    private String role;
    private String password;

    User(String nama, String role, String password) {
        this.nama = nama;
        this.role = role;
        this.password = password;
    }
    public String getNama() {
        return nama;
    }
    public String getRole() {
        return role;
    }
    public String getPassword() {
        return password;
    }


}

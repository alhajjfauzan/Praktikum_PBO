package posttest5;

public final class User extends Akun { 
    private final String nama;
    private String role;
    private String password;

    public User(String nama, String role, String password) {
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

    @Override
    public void displayInfo() { 
        System.out.println("Nama: " + nama);
        System.out.println("Role: " + role);
    }

    public final void showGreeting() { 
        System.out.println("Selamat datang, " + nama + "!");
    }
}


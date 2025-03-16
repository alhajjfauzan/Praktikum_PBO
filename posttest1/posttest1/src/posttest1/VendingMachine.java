
package posttest1;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VendingMachine {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Minuman> minumanList = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();
    
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        vm.menu();
    }
    void menu() {
        int peran;
        do {
            System.out.println("\n=== Selamat datang di Pemesanan Vending Machine ===");
            System.out.println("1. Masuk sebagai Admin");
            System.out.println("2. Masuk sebagai Pelanggan");
            System.out.println("3. Daftar sebagai Pelanggan");
            System.out.println("4. Keluar");
            System.out.print("Pilih peran: ");
            try {
                peran = scanner.nextInt();
                scanner.nextLine();
                switch (peran) {
                    case 1: if (login("admin")) admin(); break;
                    case 2: if (login("pelanggan")) pengguna(); break;
                    case 3: register(); break;
                    case 4: System.out.println("Terima kasih telah menggunakan vending machine!"); break;
                    default: System.out.println("Pilihan tidak valid, coba lagi.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine();
                peran = 0;
            }
        } while (peran != 4);
    }

    boolean login(String role) {
        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();

        if (role.equals("admin") && username.equals("admin") && password.equals("admin")) {
            System.out.println("Login sebagai Admin berhasil!");
            return true;
        }
        else if (role.equals("pelanggan")) {
        for (User user : users) {
            if (user.getNama().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login sebagai Pelanggan berhasil!");
            return true;
        }
    }
    System.out.println("Username atau password salah!");
}
        System.out.println("Login gagal. Periksa username/password.");
        return false;
    }

   void register() {
    System.out.print("Masukkan username baru: ");
    String username = scanner.nextLine();

    boolean usernameTerdaftar = false;
    for (User user : users) {
        if (user.getNama().equals(username)) {
            usernameTerdaftar = true;
            break;
        }
    }
    if (usernameTerdaftar) {
        System.out.println("Username sudah terdaftar!");
    } else {
        System.out.print("Masukkan password baru: ");
        String password = scanner.nextLine();
        
        users.add(new User(username,"Pelanggan", password)); 
        System.out.println("Registrasi berhasil! Silakan login sebagai pelanggan.");
    }
}

   void admin() {
        int pilihan;
        boolean kembali = false;

        do {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Tambah minuman");
            System.out.println("2. Lihat daftar minuman");
            System.out.println("3. Ubah minuman");
            System.out.println("4. Hapus minuman");
            System.out.println("5. Cek Akun");
            System.out.println("6. Kembali ke menu utama");
            System.out.print("Pilih opsi: ");
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1:
                        tambahMinuman();
                        break;
                    case 2:
                        lihatMinuman();
                        break;
                    case 3:
                        ubahMinuman();
                        break;
                    case 4:
                        hapusMinuman();
                        break;
                    case 5:
                        cekAkun();
                        break;
                    case 6:
                        System.out.println("Kembali ke menu utama...");
                        kembali = true;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Coba lagi.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka. Coba lagi.");
                scanner.nextLine();
            }

        } while (!kembali);
    }
    void cekAkun(){
         System.out.println("\n=== Daftar Akun Terdaftar ===");
    if (users.isEmpty()) {
        System.out.println("Belum ada user yang terdaftar.");
    } else {
        int nomor = 1;
        for (User user : users) {
            System.out.println(nomor + ". Nama: " + user.getNama() +
                               " | Role: " + user.getRole() +
                               " | Password: " + user.getPassword());
            nomor++;
        }
    }
        
    }
    void tambahMinuman() {
        System.out.print("Masukkan nama minuman: ");
        String nama = scanner.nextLine();
        int harga;

        while (true) {
            System.out.print("Masukkan harga minuman: ");
            try {
                harga = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input harus angka! Coba lagi.");
                scanner.nextLine();
            }
        }

        int stok;
        while (true) {
            System.out.print("Masukkan stok minuman: ");
            try {
                stok = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input harus angka! Coba lagi.");
                scanner.nextLine();
            }
        }

        minumanList.add(new Minuman(nama, harga, stok));
        System.out.println("Minuman berhasil ditambahkan.");
    }

    void lihatMinuman() {
        System.out.println("Daftar minuman:");
        if (minumanList.isEmpty()) {
            System.out.println("Belum ada minuman tersedia.");
        } else {
            for (int i = 0; i < minumanList.size(); i++) {
                System.out.println((i + 1) + ". " + minumanList.get(i));
            }
        }
    }

    void ubahMinuman() {
        int indeks;
        while (true) {
            System.out.print("Masukkan nomor minuman yang ingin diubah: ");
            try {
                indeks = scanner.nextInt() - 1;
                scanner.nextLine();
                if (indeks >= 0 && indeks < minumanList.size()) {
                    break;
                } else {
                    System.out.println("Nomor tidak valid. Coba lagi.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus angka yang valid! Coba lagi.");
                scanner.nextLine();
            }
        }

        System.out.print("Masukkan nama baru: ");
        String namaBaru = scanner.nextLine();

        int hargaBaru;
        while (true) {
            System.out.print("Masukkan harga baru: ");
            try {
                hargaBaru = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input harus angka! Coba lagi.");
                scanner.nextLine();
            }
        }

        int stokBaru;
        while (true) {
            System.out.print("Masukkan stok baru: ");
            try {
                stokBaru = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input harus angka! Coba lagi.");
                scanner.nextLine();
            }
        }

        minumanList.set(indeks, new Minuman(namaBaru, hargaBaru, stokBaru));
        System.out.println("Minuman berhasil diubah.");
    }

    void hapusMinuman() {
        while (true) {
            System.out.print("Masukkan nomor minuman yang ingin dihapus: ");
            try {
                int indeksHapus = scanner.nextInt() - 1;
                scanner.nextLine();

                if (indeksHapus >= 0 && indeksHapus < minumanList.size()) {
                    System.out.println("Anda yakin ingin menghapus minuman: " + minumanList.get(indeksHapus).getNama() + "? (y/n)");
                    String konfirmasi = scanner.nextLine().trim().toLowerCase();
                    if (konfirmasi.equals("y")) {
                        minumanList.remove(indeksHapus);
                        System.out.println("Minuman berhasil dihapus.");
                    } else {
                        System.out.println("Penghapusan dibatalkan.");
                    }
                    break;
                } else {
                    System.out.println("Nomor tidak valid. Coba lagi.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus angka yang valid! Coba lagi.");
                scanner.nextLine();
            }
        }
    }
    
void pengguna() {
    int  pilihan = 0;
    boolean kembali;
    do {
        kembali = false;
        System.out.println("\n=== Menu Pelanggan ===");
        System.out.println("1. Lihat daftar minuman");
        System.out.println("2. Beli minuman");
        System.out.println("3. Kembali ke menu utama");
        System.out.print("Pilih opsi: ");
        try {
            pilihan = scanner.nextInt();
            switch (pilihan) {
                case 1:
                    lihatMinuman();
                    break;
                case 2:
                    beliMinuman();             
                case 3:
                    System.out.println("Kembali ke menu utama...");
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Coba lagi.");
                    kembali = true;
            }
        } catch (InputMismatchException e) {
            System.out.println("Input harus berupa angka!");
            scanner.nextLine();
            kembali = true;
        }
    } while (kembali || pilihan != 3);
    System.out.println("Terima kasih telah menggunakan Vending Machine!");
    scanner.close();
    }
void beliMinuman() {
    int beliIndex;
    while (true) {
        System.out.print("Masukkan nomor minuman yang ingin dibeli: ");
        try {
            beliIndex = scanner.nextInt() - 1;
            if (beliIndex >= 0 && beliIndex < minumanList.size()) {
                break;
            } else {
                System.out.println("Nomor minuman tidak valid! Coba lagi.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Input harus angka yang valid! Coba lagi.");
            scanner.nextLine();
        }
    }
    Minuman minumanTerpilih = minumanList.get(beliIndex);
    if (minumanTerpilih.getStok() <= 0) {
        System.out.println("Maaf, stok " + minumanTerpilih.getNama() + " habis.");
        return; 
    }
    System.out.println("\n=== Konfirmasi Pesanan ===");
    System.out.println("Minuman: " + minumanTerpilih.getNama());
    System.out.println("Harga: " + minumanTerpilih.getHarga());
    System.out.println("Stok Tersisa: " + minumanTerpilih.getStok());
    System.out.print("Konfirmasi pembelian? (y/n): ");
    String konfirmasi = scanner.next();
    if (!konfirmasi.equalsIgnoreCase("y")) {
        System.out.println("Pembelian dibatalkan.");
        return;
    }
    int uang;
    while (true) {
        System.out.print("Masukkan uang Anda: ");
        try {
            uang = scanner.nextInt();
            if (uang >= minumanTerpilih.getHarga()) {
                break;
            } else {
                System.out.println("Uang Anda kurang! Coba lagi.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Input harus angka yang valid! Coba lagi.");
            scanner.nextLine(); 
        }
    }
    System.out.println("Berhasil membeli " + minumanTerpilih.getNama() + "!");
    minumanTerpilih.kurangiStok(1);
    int kembalian = uang - minumanTerpilih.getHarga();
    System.out.println("Kembalian: " + kembalian);

    if (kembalian > 0) {
        System.out.println("Silakan ambil kembalian Anda: " + kembalian);
    }
    System.out.println("Terima kasih telah berbelanja!");
}

}
package posttest6;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalTime;

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
                    case 1: if (login("admin")) admin(); 
                        break;
                    case 2: if (login("pelanggan")) pengguna(); 
                        break;
                    case 3: register(); 
                        break;
                    case 4: System.out.println("Terima kasih telah menggunakan vending machine!"); 
                        break;
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
            
            users.add(new User(username, "Pelanggan", password)); 
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
                        pilihanTambah();
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

    void pilihanTambah() {
    int tipe = 0;
    boolean valid = false;

    do {
        try {
            System.out.println("\n=== Tambah Minuman ===");
            System.out.println("1. Minuman Diskon");
            System.out.println("2. Minuman Promo (Happy Hour)");
            System.out.println("3. Minuman normal");
            System.out.print("Pilih tipe minuman: ");
            tipe = scanner.nextInt();
            scanner.nextLine(); 

            switch (tipe) {
                case 1:
                    tambahMinuman_Diskon();
                    valid = true;
                    break;
                case 2:
                    tambahMinuman_Promo();
                    valid = true;
                    break;
                case 3:
                    tambahMinuman_Normal();
                    valid = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih 1, 2 atau 3.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Input harus berupa angka! Silakan coba lagi.");
            scanner.nextLine(); 
        }
    } while (!valid);
}

   public static void tambahMinuman_Normal() {
        System.out.print("Masukkan nama minuman: ");
        String nama = scanner.nextLine();
        int harga;
        while (true) {
            System.out.print("Masukkan harga minuman: ");
            try {
                harga = scanner.nextInt();
                scanner.nextLine(); 
                if (harga < 0) {
                    System.out.println("Harga tidak boleh negatif!");
                } else {
                    break;
                }
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
                scanner.nextLine(); 
                if (stok < 0) {
                    System.out.println("Stok tidak boleh negatif!");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus angka! Coba lagi.");
                scanner.nextLine();
            }
        }
        minumanList.add(new MinumanBiasa(nama, harga, stok));
        System.out.println("Minuman normal berhasil ditambahkan!");
    }
   void tambahMinuman_Promo() {
    System.out.print("Masukkan nama minuman: ");
    String nama = scanner.nextLine();
    
    System.out.print("Masukkan deskripsi minuman: ");
    String deskripsi = scanner.nextLine();
    int harga;
    while (true) {
        System.out.print("Masukkan harga minuman: ");
        try {
            harga = scanner.nextInt();
            scanner.nextLine(); 
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
            scanner.nextLine(); 
            break;
        } catch (InputMismatchException e) {
            System.out.println("Input harus angka! Coba lagi.");
            scanner.nextLine();
        }
    }

    int jamMulai;
    while (true) {
        System.out.print("Masukkan jam mulai promo (format 24 jam, contoh: 15): ");
        try {
            jamMulai = scanner.nextInt();
            scanner.nextLine(); 
            if (jamMulai >= 1 && jamMulai <= 24) {
                break;
            } else {
                System.out.println("Jam harus antara 1 sampai 24.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Input harus angka! Coba lagi.");
            scanner.nextLine();
        }
    }

    int jamAkhir;
    while (true) {
        System.out.print("Masukkan jam akhir promo (1-24, harus > " + jamMulai + "): ");
        try {
            jamAkhir = scanner.nextInt();
            scanner.nextLine(); 
            if (jamAkhir >= 1 && jamAkhir <= 24) {
                if (jamAkhir > jamMulai) {
                    break;
                } else {
                    System.out.println("Error: Jam akhir harus setelah jam mulai (" + jamMulai + ")");
                }
            } else {
                System.out.println("Error: Jam harus antara 1 sampai 24.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Input harus angka!");
            scanner.nextLine(); 
        }
    }

    int persenPromo;
    while (true) {
        System.out.print("Masukkan diskon dalam persen: ");
        try {
            persenPromo = scanner.nextInt();
            scanner.nextLine(); 
            break;
        } catch (InputMismatchException e) {
            System.out.println("Input harus angka! Coba lagi.");
            scanner.nextLine();
        }
    }

    minumanList.add(new Promo(nama, deskripsi, harga, stok, persenPromo,
        LocalTime.of(jamMulai, 0), LocalTime.of(jamAkhir, 0)));
    System.out.println("Minuman promo berhasil ditambahkan!");
}

void tambahMinuman_Diskon() {
    System.out.print("Masukkan nama minuman: ");
    String nama = scanner.nextLine();
    
    System.out.print("Masukkan deskripsi minuman: ");
    String deskripsi = scanner.nextLine();

    int harga;
    while (true) {
        System.out.print("Masukkan harga minuman: ");
        try {
            harga = scanner.nextInt();
            scanner.nextLine(); 
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
            scanner.nextLine(); 
            break;
        } catch (InputMismatchException e) {
            System.out.println("Input harus angka! Coba lagi.");
            scanner.nextLine();
        }
    }

    int persenDiskon;
    while (true) {
        System.out.print("Masukkan persentase diskon (contoh: 10 untuk 10%): ");
        try {
            persenDiskon = scanner.nextInt();
            scanner.nextLine(); 
            break;
        } catch (InputMismatchException e) {
            System.out.println("Input harus angka! Coba lagi.");
            scanner.nextLine();
        }
    }

    int minimalBeli;
    while (true) {
        System.out.print("Masukkan minimal pembelian untuk diskon: ");
        try {
            minimalBeli = scanner.nextInt();
            scanner.nextLine(); 
            break;
        } catch (InputMismatchException e) {
            System.out.println("Input harus angka! Coba lagi.");
            scanner.nextLine();
        }
    }

    minumanList.add(new Diskon(nama, deskripsi, harga, stok, persenDiskon, minimalBeli));
    System.out.println("Minuman berhasil ditambahkan dengan diskon.");
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

    Minuman minuman = minumanList.get(indeks);

    System.out.print("Masukkan nama baru: ");
    String namaBaru = scanner.nextLine();
    minuman.setNama(namaBaru);
    
    System.out.print("Masukkan deskripsi baru: ");
    String deskripsiBaru = scanner.nextLine();
    minuman.setDeskripsi(deskripsiBaru);
    
    int hargaBaru;
    while (true) {
        System.out.print("Masukkan harga baru: ");
        try {
            hargaBaru = scanner.nextInt();
            scanner.nextLine();
            break;
        } catch (InputMismatchException e) {
            System.out.println("Input harus angka! Coba lagi.");
            scanner.nextLine();
        }
    }
    minuman.setHarga(hargaBaru);

    int stokBaru;
    while (true) {
        System.out.print("Masukkan stok baru: ");
        try {
            stokBaru = scanner.nextInt();
            scanner.nextLine();
            break;
        } catch (InputMismatchException e) {
            System.out.println("Input harus angka! Coba lagi.");
            scanner.nextLine();
        }
    }
    minuman.setStok(stokBaru);

    if (minuman instanceof Diskon) {
        Diskon minumanDiskon = (Diskon) minuman;

        int persenDiskonBaru;
        while (true) {
            System.out.print("Masukkan persentase diskon baru (contoh: 10 untuk 10%): ");
            try {
                persenDiskonBaru = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input harus angka! Coba lagi.");
                scanner.nextLine();
            }
        }
        minumanDiskon.setPersenDiskon(persenDiskonBaru);
        int minimalBeliBaru;
        while (true) {
            System.out.print("Masukkan minimal pembelian baru untuk diskon: ");
            try {
                minimalBeliBaru = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input harus angka! Coba lagi.");
                scanner.nextLine();
            }
        }
        minumanDiskon.setMinimalBeli(minimalBeliBaru);
    }
    else if (minuman instanceof Promo) {
        Promo minumanPromo = (Promo) minuman;
        int persenPromoBaru;
        while (true) {
            System.out.print("Masukkan persentase promo baru (contoh: 20 untuk 20%): ");
            try {
                persenPromoBaru = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input harus angka! Coba lagi.");
                scanner.nextLine();
            }
        }
        minumanPromo.setPersenDiskon(persenPromoBaru);
        int jamMulaiBaru;
        while (true) {
            System.out.print("Masukkan jam mulai promo baru (1-24, contoh: 15): ");
            try {
                jamMulaiBaru = scanner.nextInt();
                scanner.nextLine();
                if (jamMulaiBaru >= 1 && jamMulaiBaru <= 24) {
                    break;
                } else {
                    System.out.println("Jam harus antara 1-24! Coba lagi.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus angka! Coba lagi.");
                scanner.nextLine();
            }
        }

        int jamAkhirBaru;
        while (true) {
            System.out.print("Masukkan jam akhir promo baru (1-24, contoh: 17): ");
            try {
                jamAkhirBaru = scanner.nextInt();
                scanner.nextLine();
                if (jamAkhirBaru >= 1 && jamAkhirBaru <= 24) {
                    if (jamAkhirBaru > jamMulaiBaru) {
                        break;
                    } else {
                        System.out.println("Jam akhir harus setelah jam mulai (" + jamMulaiBaru + ")! Coba lagi.");
                    }
                } else {
                    System.out.println("Jam harus antara 1-24! Coba lagi.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus angka! Coba lagi.");
                scanner.nextLine();
            }
        }
        minumanPromo.setMulaiPromo(LocalTime.of(jamMulaiBaru, 0));
        minumanPromo.setAkhirPromo(LocalTime.of(jamAkhirBaru, 0));
    }

    System.out.println("Minuman berhasil diubah.");
}
    
    void lihatMinuman() {
    System.out.println("\n=== Daftar Minuman ===");
    if (minumanList.isEmpty()) {
        System.out.println("Belum ada minuman tersedia.");
    } else {
        for (int i = 0; i < minumanList.size(); i++) {
            System.out.println((i + 1) + ". " + minumanList.get(i));
            }
        }
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
                    System.out.println("Nomor tidak sesuai. Coba lagi.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus angka! Coba lagi.");
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
                        System.out.println("Pilihan tidak sesuai. Coba lagi.");
                        kembali = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine();
                kembali = true;
            }
        } while (kembali || pilihan != 3);
        System.out.println("Terima kasih telah menggunakan Vending Machine!");
    }

   public static void beliMinuman() {
    int beliIndex;

    while (true) {
        System.out.print("\nMasukkan nomor minuman yang ingin dibeli: ");
        try {
            beliIndex = scanner.nextInt() - 1;
            if (beliIndex >= 0 && beliIndex < minumanList.size()) {
                break;
            } else {
                System.out.println("Nomor minuman tidak tersedia! Coba lagi.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Input harus angka, Coba lagi.");
            scanner.nextLine();
        }
    }

    Minuman minumanTerpilih = minumanList.get(beliIndex);
    try {
            minumanTerpilih.getClass().getMethod("getHargaTotal", int.class);
            minumanTerpilih.getClass().getMethod("kurangiStok", int.class);
        } catch (NoSuchMethodException e) {
            System.out.println("Error: Minuman tidak memiliki metode yang diperlukan!");
            return;
    }

    int jumlah;

    while (true) {
        System.out.print("Masukkan jumlah yang ingin dibeli: ");
        try {
            jumlah = scanner.nextInt();

            if (jumlah > 0) {
                break;
            } else {
                System.out.println("Jumlah harus lebih dari 0. Silakan coba lagi.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Input harus berupa angka. Silakan coba lagi.");
            scanner.nextLine();  
        }
    }

    if (minumanTerpilih.getStok() < jumlah) {
        System.out.println("Maaf, stok " + minumanTerpilih.getNama() + " tidak cukup.");
        return;
    }
    int totalHarga = minumanTerpilih.getHargaTotal(jumlah);
    if (minumanTerpilih instanceof Diskon) {
        Diskon minumanDiskon = (Diskon) minumanTerpilih;
        if (minumanDiskon.isDiskon(jumlah)) {
            int hargaAwal = minumanTerpilih.getHarga() * jumlah;
            System.out.println("\n🎉 Selamat! Anda mendapat diskon " + minumanDiskon.getPersenDiskon() + "%!");
            System.out.println("Harga sebelum diskon: Rp" + hargaAwal);
            System.out.println("Harga setelah diskon: Rp" + totalHarga);
        }
    }
    System.out.println("Total yang harus dibayar: Rp" + totalHarga);
    int uang;
    while (true) {
    System.out.print("Masukkan uang Anda: ");
    try {
        uang = scanner.nextInt();

        if (uang > 0) {
            break;
        } else {
            System.out.println("Jumlah uang harus lebih dari 0");
        }

    } catch (InputMismatchException e) {
        System.out.println("Input harus berupa angka. Silakan coba lagi.");
        scanner.nextLine(); 
    }
}
    if (uang < totalHarga) {
        System.out.println("Uang Anda kurang! Coba lagi.");
        return;
    }
    System.out.println("Berhasil membeli " + jumlah + " " + minumanTerpilih.getNama() + "!");
    minumanTerpilih.kurangiStok(jumlah);
    System.out.println("Kembalian: Rp" + (uang - totalHarga));
    System.out.println("Terima kasih telah berbelanja!");
}
}
package posttest4;

public class Minuman {
    private String nama;
    private int harga;
    protected int stok;
    private String deskripsi;

    public Minuman(String nama, int harga, int stok) {
        this(nama, harga, stok, "Tidak ada deskripsi tersedia");
    }
    
    public Minuman(String nama, int harga, int stok, String deskripsi) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
       this.deskripsi = (deskripsi == null || deskripsi.trim().isEmpty()) 
    ? "-Tidak ada deskripsi-" 
    : deskripsi;
    }
    
    public String getNama() {
        return nama;
    }
    
    public int getHarga() {
        return harga;
    }
    
    public int getStok() {
        return stok;
    }
    
    public String getDeskripsi() {
        return deskripsi;
    }
    
    public void setNama(String nama) {
        if (nama != null && !nama.trim().isEmpty()) {
            this.nama = nama;
        }
    }

    public void setHarga(int harga) {
        if (harga >= 0) {
            this.harga = harga;
        }
    }

    public void setStok(int stok) {
        if (stok >= 0) {
            this.stok = stok;
        }
    }
    
    public void setDeskripsi(String deskripsi) {
        if (deskripsi != null) {
            this.deskripsi = deskripsi;
        }
    }
    
    public boolean isDiskon(int jumlahBeli) {
        return false;
    }
    
    public int getPersenDiskon() {
        return 0;
    }

    public int getHargaTotal(int jumlahBeli) {
        if (jumlahBeli <= 0) return 0;
        return harga * jumlahBeli;
    }
    
    public void kurangiStok(int jumlah) {
        if (jumlah > 0 && stok >= jumlah) {
            stok -= jumlah;
        }
    }
    
    @Override
    public String toString() {
        return String.format("%s | Harga: Rp%,d | Stok: %d | Deskripsi: %s", 
                            nama, harga, stok, deskripsi);
    }
}
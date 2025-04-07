package posttest3;

public class Minuman {
    private String nama;
    private int harga;
    protected int stok;

    public Minuman(String nama, int harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
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
    public boolean isDiskon(int jumlahBeli) {
        return false;
    }
    public int getPersenDiskon() {
        return 0;
    }

    public int getHargaTotal(int jumlahBeli) {
        return harga * jumlahBeli;
    }
    public void kurangiStok(int jumlah) {
        if (stok >= jumlah) {
            stok -= jumlah;
        }
    }
public void setNama(String nama) {
    this.nama = nama;
}

public void setHarga(int harga) {
    this.harga = harga;
}

public void setStok(int stok) {
    this.stok = stok;
}
    @Override
    public String toString() {
        return nama + " | Harga: Rp" + harga + " | Stok: " + stok;
    }
}
package posttest1;

class Minuman {
    private String nama;
    private int harga;
    private int stok;

    Minuman(String nama, int harga, int stok) {
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

    public void kurangiStok(int jumlah) {
        if (stok >= jumlah) {
            stok -= jumlah;
        }
    }
    @Override
    public String toString() {
        return nama + " | Harga: Rp" + harga + " | Stok: " + stok;
    }
}
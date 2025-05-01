package posttest6;

import java.time.LocalTime;

public class Promo extends Minuman {
    private int persenDiskon;
    private LocalTime mulaiPromo;
    private LocalTime akhirPromo;

    public Promo(String nama, String deskripsi, int harga, int stok, int persenDiskon, LocalTime mulaiPromo, LocalTime akhirPromo) {
        super(nama, harga, stok, deskripsi);
        this.persenDiskon = persenDiskon;
        this.mulaiPromo = mulaiPromo;
        this.akhirPromo = akhirPromo;
    }

    public void setPersenDiskon(int persenDiskon) {
        this.persenDiskon = persenDiskon;
    }

    public void setMulaiPromo(LocalTime mulaiPromo) {
        this.mulaiPromo = mulaiPromo;
    }

    public void setAkhirPromo(LocalTime akhirPromo) {
        this.akhirPromo = akhirPromo;
    }

    public boolean isDalamWaktuPromo() {
        LocalTime sekarang = LocalTime.now();
        return !sekarang.isBefore(mulaiPromo) && !sekarang.isAfter(akhirPromo);
    }

    @Override
    public boolean isDiskon(int jumlahBeli) {
       
        return isDalamWaktuPromo();
    }

    @Override
    public int getPersenDiskon() {
               return isDalamWaktuPromo() ? persenDiskon : 0;
    }

    @Override
    public int getHargaTotal(int jumlahBeli) {
        if (jumlahBeli <= 0) return 0;

        int total = getHarga() * jumlahBeli;
        if (isDiskon(jumlahBeli)) {
            int diskon = total * getPersenDiskon() / 100;
            return total - diskon;
        }

        return total;
    }

    @Override
    public String toString() {
        return super.toString() + " | Promo: " + persenDiskon + "% (" +
               mulaiPromo + " - " + akhirPromo + ")";
    }
}


package posttest3;
import java.time.LocalTime;

public class Promo extends Minuman {
    private int persenDiskon;
    private LocalTime mulaiPromo;
    private LocalTime akhirPromo;

    public Promo(String nama, int harga, int stok, int persenDiskon, LocalTime mulaiPromo, LocalTime akhirPromo) {
        super(nama, harga, stok);
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
    public int getHargaTotal(int jumlahBeli) {
        if (isDalamWaktuPromo()) {
            return (getHarga() * jumlahBeli * (100 - persenDiskon)) / 100;
        }
        return super.getHargaTotal(jumlahBeli);
    }
    @Override
    public String toString() {
        return super.toString() + " | Promo: " + persenDiskon + "% ("
               + mulaiPromo + " - " + akhirPromo + ")";
    }
}

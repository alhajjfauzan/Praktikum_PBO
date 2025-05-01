package posttest6;

public class Diskon extends Minuman implements Diskonable{
    private int persenDiskon; 
    private int minimalBeli;  

    public Diskon(String nama, String deskripsi, int harga, int stok, int persenDiskon, int minimalBeli) {
        super(nama, harga, stok, deskripsi);
        this.persenDiskon = persenDiskon;
        this.minimalBeli = minimalBeli;
    }

    public int getPersenDiskon() {
        return persenDiskon;
    }

    public void setPersenDiskon(int persenDiskon) {
        this.persenDiskon = persenDiskon;
    }

    public int getMinimalBeli() {
        return minimalBeli;
    }

    public void setMinimalBeli(int minimalBeli) {
        this.minimalBeli = minimalBeli;
    }

    public boolean isDiskon(int jumlahBeli) {
        return jumlahBeli >= minimalBeli;
    }

    @Override
    public int getHargaTotal(int jumlahBeli) {
        if (isDiskon(jumlahBeli)) {
            return (getHarga() * jumlahBeli * (100 - persenDiskon)) / 100;
        }
        return super.getHargaTotal(jumlahBeli);
    }

    @Override
    public String toString() {
        return super.toString() + " | Diskon: " + persenDiskon + "% (Min beli: " + minimalBeli + ")";
    }
}





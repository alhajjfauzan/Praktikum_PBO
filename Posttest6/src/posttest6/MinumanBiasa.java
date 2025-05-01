package posttest6;

public class MinumanBiasa extends Minuman {
    public MinumanBiasa(String nama, int harga, int stok) {
        super(nama, harga, stok);
    }

        @Override
    public boolean isDiskon(int jumlahBeli) {
        return false;
    }

    @Override
    public int getPersenDiskon() {
        return 0;
    }
}
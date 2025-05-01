
package posttest6;

public interface Diskonable {
    int getPersenDiskon();
    void setPersenDiskon(int persenDiskon);

    int getMinimalBeli();
    void setMinimalBeli(int minimalBeli);

    boolean isDiskon(int jumlahBeli);

    int getHargaTotal(int jumlahBeli); 
}


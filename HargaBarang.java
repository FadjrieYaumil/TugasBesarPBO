import java.util.HashMap;
import java.util.Map;

class HargaBarang {
    private Map<String, Double> hargaBarangMap;

    public HargaBarang() {
        hargaBarangMap = new HashMap<>();
    
        hargaBarangMap.put("Nasi Goreng", 12000.0);
        hargaBarangMap.put("Mie Goreng", 13000.0);
        hargaBarangMap.put("Jeruk", 7000.0);
    }

    public double getHarga(String namaBarang) {
        return hargaBarangMap.getOrDefault(namaBarang, 0.0);
    }
}

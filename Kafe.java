import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class Kafe {
    private ArrayList<MenuKafe> pesanan;
    private HargaBarang hargaBarang;
    private Diskon diskon;

    public Kafe() {
        pesanan = new ArrayList<>();
        hargaBarang = new HargaBarang();
        diskon = new DiskonHarga(15.0);
    }

    public void tambahPesanan(MenuKafe menu) {
        pesanan.add(menu);
    }

    public void tampilkanStruk() {
        System.out.println("Pesanan:");
        for (MenuKafe menu : pesanan) {
            System.out.println(menu.getNama() + "\t: " + menu.getHarga());
        }

        System.out.println("\nDiskon harga:");
        for (MenuKafe menu : pesanan) {
            System.out.println(menu.getNama() + "\t: " + diskon.hitungDiskon(menu.getHarga()));
        }

        double totalHarga = hitungTotalHarga();
        double totalDiskon = diskon.hitungDiskon(totalHarga);

        System.out.println("\nTotal Harga\t: " + totalHarga);
        System.out.println("Total Diskon\t: " + totalDiskon);
        System.out.println("Harga Setelah Diskon\t: " + (totalHarga - totalDiskon));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String tanggalPembelian = dateFormat.format(new Date());
        System.out.println("\nTanggal Pembelian\t: " + tanggalPembelian);

        DatabaseManager.createTable();
        for (MenuKafe menu : pesanan) {
            DatabaseManager.createPesanan(menu.getNama(), menu.getHarga());
        }

        System.out.println("Pesanan telah disimpan ke database.");
    }

    private double hitungTotalHarga() {
        double totalHarga = 0.0;
        for (MenuKafe menu : pesanan) {
            totalHarga += menu.getHarga();
        }
        return totalHarga;  }
    }
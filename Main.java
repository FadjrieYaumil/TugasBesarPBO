public class Main {
    public static void main(String[] args) {
        
        MenuKafe nasiGoreng = new MenuKafe("Nasi Goreng", 12000.0);
        MenuKafe mieGoreng = new MenuKafe("Mie Goreng", 13000.0);
        MenuKafe jeruk = new MenuKafe("Jeruk", 7000.0);

        Kafe hanasta = new Kafe();
        hanasta.tambahPesanan(nasiGoreng);
        hanasta.tambahPesanan(mieGoreng);
        hanasta.tambahPesanan(jeruk);

       hanasta.tampilkanStruk();
    }
}
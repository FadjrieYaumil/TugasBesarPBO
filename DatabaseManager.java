import java.sql.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/kafe"  ; 
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void createTable() {
        try (Connection connection = connect(); Statement statement = connection.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS pesanan (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "nama_barang VARCHAR(255) NOT NULL," +
                    "harga DOUBLE NOT NULL)";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createPesanan(String namaBarang, double harga) {
        try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement("INSERT INTO pesanan (nama_barang, harga) VALUES (?, ?)")) {
            statement.setString(1, namaBarang);
            statement.setDouble(2, harga);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void readPesanan() {
        try (Connection connection = connect(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM pesanan");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Nama Barang: " + resultSet.getString("nama_barang") +
                        ", Harga: " + resultSet.getDouble("harga"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePesanan(int id, double newHarga) {
        try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement("UPDATE pesanan SET harga = ? WHERE id = ?")) {
            statement.setDouble(1, newHarga);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletePesanan(int id) {
        try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement("DELETE FROM pesanan WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Pesanan");
            System.out.println("2. Lihat Pesanan");
            System.out.println("3. Update Pesanan");
            System.out.println("4. Hapus Pesanan");
            System.out.println("5. Keluar");

            System.out.print("Pilih operasi (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Masukkan Nama Barang: ");
                    String namaBarang = scanner.nextLine();
                    System.out.print("Masukkan Harga: ");
                    double harga = scanner.nextDouble();
                    createPesanan(namaBarang, harga);
                    break;
                case 2:
                    readPesanan();
                    break;
                case 3:
                    System.out.print("Masukkan ID Pesanan yang akan diubah: ");
                    int idUpdate = scanner.nextInt();
                    System.out.print("Masukkan Harga Baru: ");
                    double newHarga = scanner.nextDouble();
                    updatePesanan(idUpdate, newHarga);
                    break;
                case 4:
                    System.out.print("Masukkan ID Pesanan yang akan dihapus: ");
                    int idDelete = scanner.nextInt();
                    deletePesanan(idDelete);
                    break;
                case 5:
                System.out.println("");
                System.out.println("Pesanan Telah DiUpdate: ");
                System.out.println("");
                readPesanan();
                System.out.println("");
                System.out.println("Program selesai.");

                 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String tanggalPembelian = dateFormat.format(new Date());
        System.out.println("\nTanggal Pembelian\t: " + tanggalPembelian);
        System.out.println("");
        System.out.println("Pesanan telah disimpan ke database.");

                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih kembali.");
                    break;
            }

        }
    }
    
  
    public static void readSinglePesanan(int id) {
        try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM pesanan WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Nama Barang: " + resultSet.getString("nama_barang") +
                        ", Harga: " + resultSet.getDouble("harga"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



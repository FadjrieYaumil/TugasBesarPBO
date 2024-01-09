class DiskonHarga implements Diskon {
    private double diskonPersen;

    public DiskonHarga(double diskonPersen) {
        this.diskonPersen = diskonPersen;
    }

    @Override
    public double hitungDiskon(double totalHarga) {
        return totalHarga * (diskonPersen / 100);
    }
}
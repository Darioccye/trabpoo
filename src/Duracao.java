class Duracao {
    private int min;
    private int segundos;

    public Duracao(int min, int segundos) {
        this.min = min;
        this.segundos = segundos;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    @Override
    public String toString() {
        return "Duracao{" + "min=" + min + ", segundos=" + segundos + '}';
    }



}
package by.bsac.practical7.individual.Main;

public class PlaceOfWork extends Taxes{ // место работы

    public PlaceOfWork() {
        super();
    }

    public PlaceOfWork(double taxrate, double sum) {
        super(taxrate, sum);
    }

    @Override
    public String toString() {
        return "Место работы{" +
                "налоговая ставка=" + getTaxrate() +
                ", сумма=" + getSum() +
                '}';
    }
}

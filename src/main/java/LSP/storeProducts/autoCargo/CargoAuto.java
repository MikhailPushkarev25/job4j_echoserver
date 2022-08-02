package LSP.storeProducts.autoCargo;

import java.util.ArrayList;
import java.util.List;

public class CargoAuto implements DistributionAuto {

    private List<ViewAuto> autos = new ArrayList<>();

    private Auto car = new Auto();


    @Override
    public List<ViewAuto> get() {
        return autos;
    }

    @Override
    public void dest(ViewAuto auto) {
        if (auto.getNumPark() > 1 ||  car.get().size() != 1) {
            autos.add(auto);
            System.out.println("Грузовое авто заняло свое парковочное место!");
        }
    }
}

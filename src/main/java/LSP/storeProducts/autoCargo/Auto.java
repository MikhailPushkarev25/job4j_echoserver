package LSP.storeProducts.autoCargo;

import java.util.ArrayList;
import java.util.List;

public class Auto implements DistributionAuto {

    List<ViewAuto> autos = new ArrayList<>();


    @Override
    public List<ViewAuto> get() {
        return autos;
    }

    @Override
    public void dest(ViewAuto auto) {
        if (auto.getNumPark() == 1) {
            autos.add(auto);
            System.out.println("Легковое авто заняло свое парковочное место!");
        }
    }
}

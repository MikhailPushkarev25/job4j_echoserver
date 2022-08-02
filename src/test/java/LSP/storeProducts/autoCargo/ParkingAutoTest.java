package LSP.storeProducts.autoCargo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class ParkingAutoTest {

    @Test
    public void whenAuto() {
        ViewAuto auto = new ViewAuto("bmw", "sedan", 1);
       List<DistributionAuto> autos = new ArrayList<>();
       Auto car = new Auto();
       autos.add(car);
       ParkingAuto park = new ParkingAuto(autos);
       park.setPark(auto);
       assertThat(car.get(), is(List.of(auto)));
    }

    @Test
    public void whenCargoAuto() {
        ViewAuto auto = new ViewAuto("bmw", "sedan", 0);
        ViewAuto autoCargo = new ViewAuto("KAMAZ", "cargo", 2);
        ViewAuto autoCargo2 = new ViewAuto("MAZ", "cargo", 1);
        List<DistributionAuto> autos = new ArrayList<>();
        CargoAuto cargo = new CargoAuto();
        Auto car = new Auto();
        autos.add(cargo);
        autos.add(car);
        ParkingAuto park = new ParkingAuto(autos);
        park.setPark(autoCargo);
        park.setPark(auto);
        park.setPark(autoCargo2);
        assertThat(cargo.get(), is(List.of(autoCargo, auto, autoCargo2)));
    }
}
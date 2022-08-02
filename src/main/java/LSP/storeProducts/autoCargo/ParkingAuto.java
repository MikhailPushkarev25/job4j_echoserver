package LSP.storeProducts.autoCargo;

import java.util.List;

public class ParkingAuto {

    private List<DistributionAuto> park;

    public ParkingAuto(List<DistributionAuto> park) {
        this.park = park;
    }

    public void setPark(ViewAuto auto) {
        for (DistributionAuto car : park) {
            car.dest(auto);
        }
    }
}

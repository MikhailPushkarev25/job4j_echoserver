package LSP.storeProducts.autoCargo;

import java.util.List;

public interface DistributionAuto {

    List<ViewAuto> get();

    void dest(ViewAuto auto);
}

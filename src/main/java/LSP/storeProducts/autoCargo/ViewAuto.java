package LSP.storeProducts.autoCargo;

import java.util.Objects;

public class ViewAuto {

    private String name;
    private String view;
    private int numPark;

    public ViewAuto(String name, String view, int numPark) {
        this.name = name;
        this.view = view;
        this.numPark = numPark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public int getNumPark() {
        return numPark;
    }

    public void setNumPark(int numPark) {
        this.numPark = numPark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewAuto viewAuto = (ViewAuto) o;
        return numPark == viewAuto.numPark && Objects.equals(name, viewAuto.name) && Objects.equals(view, viewAuto.view);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, view, numPark);
    }

    @Override
    public String toString() {
        return "ViewAuto{" +
                "name='" + name + '\'' +
                ", view='" + view + '\'' +
                ", numPark=" + numPark +
                '}';
    }
}

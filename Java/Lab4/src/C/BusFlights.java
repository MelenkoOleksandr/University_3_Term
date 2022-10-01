package C;

import java.util.ArrayList;

public class BusFlights {
    ArrayList<ArrayList<Boolean>> busflights = new ArrayList<>();
    ArrayList<ArrayList<Integer>> prices = new ArrayList<>();

    public boolean checkRoadExists(int source, int dest, ArrayList<Integer> visited) {
        int next = -1;
        while (visited.size() != busflights.size()) {
            if (busflights.get(source).get(dest)) {
                return true;
            }
            for (int i = 0; i < busflights.size(); i++) {
                if ( i != source ) {

                }
            }
            visited.add(source);
        }
        return true;
    }


    public void addCity() {
        int cities = busflights.size();
        ArrayList<Boolean> newConnections = new ArrayList<Boolean>();
        for (int i = 0; i < cities; i++) {
            newConnections.set(i, false);
        }

        busflights.add(newConnections);

        for (int i = 0; i < busflights.size(); i++) {
            busflights.get(i).set(busflights.size() - 1, false);
        }

        int cityIndex = busflights.size() - 1;
        busflights.get(cityIndex).set(cityIndex, true);
    }

    public void deleteCity(int cityIndex) {
        busflights.remove(cityIndex);
        for (ArrayList<Boolean> busflight : busflights) {
            busflight.remove(busflight.size() - 1);
        }
    }

    public void addRoad() {
        if (busflights.size() > 1) {
            int sourceIndex =  (int) Math.floor(Math.random() * (busflights.size()));
            int destIndex =  (int) Math.floor(Math.random() * (busflights.size()));
            busflights.get(sourceIndex).set(destIndex, true);
            busflights.get(destIndex).set(sourceIndex, true);
        }
    }

    public void deleteRoad() {
        if (busflights.size() > 1) {
            int sourceIndex =  (int) Math.floor(Math.random() * (busflights.size()));
            int destIndex =  (int) Math.floor(Math.random() * (busflights.size()));
            busflights.get(sourceIndex).set(destIndex, false);
            busflights.get(destIndex).set(sourceIndex, false);
        }
    }

}

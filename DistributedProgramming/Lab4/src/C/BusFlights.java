package C;

import java.util.ArrayList;

public class BusFlights {
    ArrayList<ArrayList<Boolean>> busflights = new ArrayList<>();
    ArrayList<ArrayList<Integer>> prices = new ArrayList<>();

    public void showFligths() {
        System.out.println("Roads: ");
        for (int i = 0; i < busflights.size(); i++) {
            for (int j = 0; j < busflights.get(i).size(); j++) {
                System.out.print(busflights.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

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
        if (busflights.size() == 0) {
            ArrayList<Boolean> newConnections = new ArrayList<Boolean>();
            newConnections.add(0, true);
            busflights.add(newConnections);
        } else {
            int cities = busflights.size();
            ArrayList<Boolean> newConnections = new ArrayList<Boolean>();
            for (int i = 0; i < cities; i++) {
                newConnections.add(i, false);
            }

            busflights.add(newConnections);

            for (int i = 0; i < busflights.size(); i++) {
                busflights.get(i).add(busflights.size() - 1, false);
            }

            int cityIndex = busflights.size() - 1;
            busflights.get(cityIndex).set(cityIndex, true);
        }
        showFligths();
    }

    public void deleteCity() {
        if (busflights.size() > 0) {
            int cityIndex =  (int) Math.floor(Math.random() * (busflights.size()));
            busflights.remove(cityIndex);
            for (ArrayList<Boolean> busflight : busflights) {
                busflight.remove(busflight.size() - 1);
            }
            showFligths();
        }
    }

    public void addRoad() {
        if (busflights.size() > 1) {
            int sourceIndex =  (int) Math.floor(Math.random() * (busflights.size()));
            int destIndex =  (int) Math.floor(Math.random() * (busflights.size()));
            busflights.get(sourceIndex).set(destIndex, true);
            busflights.get(destIndex).set(sourceIndex, true);
            showFligths();
        }
    }

    public void deleteRoad() {
        if (busflights.size() > 1) {
            int sourceIndex =  (int) Math.floor(Math.random() * (busflights.size()));
            int destIndex =  (int) Math.floor(Math.random() * (busflights.size()));
            busflights.get(sourceIndex).set(destIndex, false);
            busflights.get(destIndex).set(sourceIndex, false);
            showFligths();
        }
    }

}

package utils;

import devices.Device;
import devices.DeviceComparator;

import java.util.ArrayList;

public class DeviceSorter {
    public static void sortDevices(ArrayList<Device> devices) {
        devices.sort(new DeviceComparator());
    }
}

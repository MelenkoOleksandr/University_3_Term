package devices;

enum PortTypes {
    USB,
    COM,
    LPT
}

public class DeviceTypes {
    private Boolean peripheral;
    private Integer energyConsumption;
    private Boolean cooler;
    private String componentGroup;
    private PortTypes port;
}

package devices;

public class DeviceTypes {
    private Boolean peripheral;
    private Integer energyConsumption;
    private Boolean cooler;
    private String componentGroup;
    private PortTypes port;

    public DeviceTypes() {
    }

    public DeviceTypes(Boolean peripheral, Integer energyConsumption, Boolean cooler, String componentGroup, PortTypes port) {
        this.peripheral = peripheral;
        this.energyConsumption = energyConsumption;
        this.cooler = cooler;
        this.componentGroup = componentGroup;
        this.port = port;
    }

    public Boolean isPeripheral() {
        return peripheral;
    }

    public void setPeripheral(Boolean peripheral) {
        this.peripheral = peripheral;
    }

    public Integer getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(Integer energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public Boolean isCooler() {
        return cooler;
    }

    public void setCooler(Boolean cooler) {
        this.cooler = cooler;
    }

    public String getComponentGroup() {
        return componentGroup;
    }

    public void setComponentGroup(String componentGroup) {
        this.componentGroup = componentGroup;
    }

    public PortTypes getPort() {
        return port;
    }

    public void setPort(PortTypes port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "DeviceTypes{" + "peripheral=" + peripheral + ", energyConsumption=" + energyConsumption + ", cooler=" + cooler + ", componentGroup=" + componentGroup + ", port=" + port + '}';
    }

    public boolean equals(DeviceTypes obj) {
        return this.peripheral.equals(obj.peripheral) && this.energyConsumption.equals(obj.energyConsumption) && this.cooler.equals(obj.cooler) && this.componentGroup.equals(obj.componentGroup) && this.port.equals(obj.port);
    }
}

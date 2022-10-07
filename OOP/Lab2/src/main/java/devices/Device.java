package devices;

public class Device {
    private String id;
    private String name;
    private String origin;
    private Integer price;
    private Boolean critical;
    private DeviceTypes types;

    public Device(String id, String name, String origin, Integer price, Boolean critical, DeviceTypes types) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.price = price;
        this.critical = critical;
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean isCritical() {
        return critical;
    }

    public void setCritical(Boolean critical) {
        this.critical = critical;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeviceTypes getTypes() {
        return types;
    }

    public void setTypes(DeviceTypes types) {
        this.types = types;
    }
}

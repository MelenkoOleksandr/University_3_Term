package devices;

public class Device {
    private String id;
    private String name;
    private String origin;
    private Integer price;
    private Boolean critical;
    private DeviceTypes types = new DeviceTypes();

    public Device() {
    }

    public Device(String id, String name, String origin, Integer price, Boolean critical, DeviceTypes types) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.price = price;
        this.critical = critical;
        this.types = types;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Device.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Device other = (Device) obj;
        if ((id == null) || (other.id != null) && !id.equals(other.id)) {
            return false;
        }
        if ((name == null) || (other.name != null) && !name.equals(other.name)) {
            return false;
        }
        if ((origin == null) || (other.origin != null) && !origin.equals(other.origin)) {
            return false;
        }
        if (price != other.price && (price == null || !price.equals(other.price))) {
            return false;
        }
        if (critical != other.critical && (critical == null || !critical.equals(other.critical))) {
            return false;
        }
        if (types != other.types && (types == null || !types.equals(other.types))) {
            return false;
        }
        return true;
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

    @Override
    public String toString() {
        return "Device{" + "id=" + id + ", name=" + name + ", origin=" + origin + ", price=" + price + ", critical=" + critical + ", types=" + types + '}';
    }
}

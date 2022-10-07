package library;

import java.util.Objects;

public class Singer {
    private String name;

     public Singer(String name) {
      this.name = name;
     }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (!Objects.equals(name, "")) {
            this.name = name;
        }
    }

    @Override
    public String toString() {
        return "library.Singer: " + this.name;
    }
}

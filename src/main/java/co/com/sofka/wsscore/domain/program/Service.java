package co.com.sofka.wsscore.domain.program;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Service {
    private final String id;
    private final String name;
    private List<String> categories;

    public Service(String id, String name) {
        this.categories = new ArrayList<>();
        this.name = Objects.requireNonNull(name);
        this.id = Objects.requireNonNull(id);
    }

    public void addCategory(String category){
        categories.add(category);
    }

    public  List<String> categories() {
        return categories;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equals(id, service.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

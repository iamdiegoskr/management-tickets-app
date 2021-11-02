package co.com.sofka.tickets.domain.program;

import java.util.Objects;

public class LevelService {
    private final String id;
    private final String level;
    private final String description;

    public LevelService(String id, String level, String description) {
        this.id = id;
        this.level = Objects.requireNonNull(level);
        this.description = Objects.requireNonNull(description);
    }

    public String getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }
}

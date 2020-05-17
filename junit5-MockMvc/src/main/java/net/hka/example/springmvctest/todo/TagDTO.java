package net.hka.example.springmvctest.todo;

/**
 * Contains the information of a single tag that's
 * can be added to a todo item.
 */
public class TagDTO {

    private Long id;
    private String name;

    public TagDTO() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TagDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

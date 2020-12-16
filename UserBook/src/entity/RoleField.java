package entity;

public class RoleField implements UserField{
    private String title;

    public RoleField(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }
}

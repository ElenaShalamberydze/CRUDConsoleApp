package entity;

public class PhoneField implements UserField{
    private String title;

    public PhoneField(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }
}

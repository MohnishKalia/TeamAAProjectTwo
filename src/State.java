public interface State {
    public void insertMember(long wiscID, String name, Member.SchoolYear yearInSchool) throws IllegalArgumentException;
    public Member getMember(long wiscID) throws IllegalArgumentException;
    public void save();

    @Override
    public String toString();
}

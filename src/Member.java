import java.time.LocalDateTime;

public interface Member {
    public enum SchoolYear {FRESHMAN, SOPHOMORE, JUNIOR, SENIOR, OTHER}
    
    public long getWiscId();
    public String getName();
    public SchoolYear getSchoolYear();
    public LocalDateTime getDateTime();
    
    @Override
    public String toString();
}

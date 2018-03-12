package baseapp.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User
{

    int userId;
    String role;


    public CustomUser (int userId, String username, String password, 
            String role, boolean enabled,
            boolean accountNonExpired, boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities)
    {
        super (username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.role = role;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    public int getUserId ()
    {
        return userId;
    }


    public String getRole ()
    {
        return role;
    }


    public void setUserId (int userId)
    {
        this.userId = userId;
    }


    public void setRole (String role)
    {
        this.role = role;
    }

    
}

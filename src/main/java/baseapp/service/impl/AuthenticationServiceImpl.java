package baseapp.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import baseapp.dao.UserMapper;
import baseapp.domain.User;
import baseapp.domain.UserExample;
import baseapp.dto.CustomUser;

@Service
public class AuthenticationServiceImpl implements UserDetailsService
{
    @Autowired
    UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername (String username)
            throws UsernameNotFoundException
    {
        String role = null;
        String origin = null;

        User user = null;
        UserExample uEx = new UserExample ();
        UserExample.Criteria uCrit = uEx.createCriteria ();
        uCrit.andUsernameEqualTo (username.toLowerCase ())
                .andFlagAktifEqualTo (true);
        List<User> userList = userMapper.selectByExample (uEx);
        if (userList != null && !userList.isEmpty ()) {
            user = userList.get (0);
        }
        
        if(user == null)
            throw new UsernameNotFoundException (
                "Invalid username or password");

        GrantedAuthority authority = new SimpleGrantedAuthority (user.getRole ());

        // construct custom user
        CustomUser userSpring = null;

        if (user != null) {
            userSpring = new CustomUser (user.getId (), user.getUsername (),
                    user.getPassword (), user.getRole(), true,
                    true, true, true, Arrays.asList (authority));
        }

        return userSpring;
    }

}

package teksystems.casestudy.security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.database.dao.UserRoleDAO;
import teksystems.casestudy.database.entity.User;
import teksystems.casestudy.database.entity.UserRole;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserRoleDAO userRoleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username '" + username + "' not found in database");
        }

        //MODIFIED
//        List<UserRole> userRoles = userRoleDao.findByUserId(user.getUserId());
        String userRole = user.getUserRole();

        // check the account status
//        boolean accountIsEnabled = false;

//        accountIsEnabled = user.isActive();
        // spring security configs
        boolean accountIsEnabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        // setup user roles
        // List<Permission> permissions = userDao.getPermissionsByEmail(username);
        // Collection<? extends GrantedAuthority> springRoles = buildGrantAuthorities(permissions);

        //MODIFIED
        //Collection<? extends GrantedAuthority> springRoles = buildGrantAuthorities(userRoles);
        Collection<? extends GrantedAuthority> springRoles = buildGrantAuthorities(userRole);


        String password = user.getPassword();

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                accountIsEnabled, accountNonExpired, credentialsNonExpired, accountNonLocked, springRoles);
    }
    //MODIFIED
//    private Collection<? extends GrantedAuthority> buildGrantAuthorities(List<UserRole> userRoles) {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        for (UserRole role : userRoles) {
//            authorities.add(new SimpleGrantedAuthority(role.getUserRole()));
//        }
//
//        return authorities;
//
//    }
    private Collection<? extends GrantedAuthority> buildGrantAuthorities(String userRole) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(userRole));

        return authorities;
    }

}

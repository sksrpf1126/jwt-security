package back.auth.review.security;


import back.auth.review.dto.user.response.UserAuthResponse;
import back.auth.review.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        UserAuthResponse userAuthResponse = userMapper.selectUserMenus(Integer.parseInt(userId));

        Member findMember = Member.builder()
                            .userId(userAuthResponse.getUserId())
                            .accountId(userAuthResponse.getAccountId())
                            .email(userAuthResponse.getEmail())
                            .role(userAuthResponse.getRole())
                            .build();

        List<String> menuAuths = userAuthResponse.getMenuAuths();
        menuAuths.add(userAuthResponse.getRole().toString());

        return new WbUserDetails(findMember, setAuthorities(menuAuths));
    }

    // Role + MenuAuth
    private Collection<GrantedAuthority> setAuthorities(List<String> menuAuths) {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        menuAuths.forEach((menuAuth) -> authorities.add(new SimpleGrantedAuthority(menuAuth)));

        return authorities;
    }
}

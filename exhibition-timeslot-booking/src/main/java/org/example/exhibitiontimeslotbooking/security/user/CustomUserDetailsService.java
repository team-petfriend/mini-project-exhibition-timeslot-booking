package org.example.exhibitiontimeslotbooking.security.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exhibitiontimeslotbooking.entity.user.User;
import org.example.exhibitiontimeslotbooking.repository.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserPrincipalMapper userPrincipalMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username == null || username.trim().isEmpty()) {
            throw new UsernameNotFoundException("Invalid username");
        }

        String loginId = username.trim();

        // 🔥 실무 기준: username 기반 조회
        // 이메일 로그인 정책이면: userRepository.findByEmail(loginId)
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));

        // 🔥 엔티티 → UserPrincipal 변환
        return userPrincipalMapper.map(user);
    }
}

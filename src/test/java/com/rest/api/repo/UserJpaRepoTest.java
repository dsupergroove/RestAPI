package com.rest.api.repo;

import com.rest.api.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserJpaRepoTest {

    @Autowired
    private UserJpaRepo userJpaRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void whenFindByUid_thenRetrunUser() {
        String uid = "id";
        String name = "id";

        userJpaRepo.save(
                User.builder()
                        .uid(uid)
                        .password(passwordEncoder.encode("1234"))
                        .name(name)
                .roles(Collections.singletonList("ROLE_USER"))
                .build()
        );

        Optional<User> user = userJpaRepo.findByUid(uid);
        assertNotNull(user);
        assertTrue(user.isPresent());
        assertEquals(user.get().getName(), name);
        assertThat(user.get().getName(), is(name));
    }

}
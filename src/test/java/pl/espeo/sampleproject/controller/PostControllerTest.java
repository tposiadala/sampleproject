package pl.espeo.sampleproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import pl.espeo.sampleproject.model.Post;
import pl.espeo.sampleproject.repository.PostRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "idiot", password = "idiot")
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional
    void shouldGetSinglePost() throws Exception {
        //given
        Post newPost = new Post();
        newPost.setTitle("What kind of shit?");
        newPost.setContent("New Shit");
        newPost.setCreated(LocalDateTime.now());

        postRepository.save(newPost);

        //when
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/posts/" + newPost.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        //then
        Post post = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Post.class);
        assertThat(post).isNotNull();
        assertThat(post.getId()).isEqualTo(newPost.getId());
        assertThat(post.getTitle()).isEqualTo(newPost.getTitle());
        assertThat(post.getContent()).isEqualTo(newPost.getContent());
    }

    @Test
    @WithAnonymousUser
    void shouldGetSinglePost401() throws Exception {

        mockMvc.perform(get("/api/v1/posts/1"))
                .andDo(print())
                .andExpect(status().is(401))
                .andReturn();
    }

    @Test
    void shouldGetSinglePost404() throws Exception {

        mockMvc.perform(get("/api/v1/posts/1234567890"))
                .andDo(print())
                .andExpect(status().is(404))
                .andReturn();
    }

    @Test
    void shouldGetSinglePost400() throws Exception {

        mockMvc.perform(get("/api/v1/posts/1234567890a"))
                .andDo(print())
                .andExpect(status().is(400))
                .andReturn();
    }
}
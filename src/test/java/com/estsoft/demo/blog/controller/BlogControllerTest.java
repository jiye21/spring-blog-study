package com.estsoft.demo.blog.controller;

import com.estsoft.demo.blog.domain.Article;
import com.estsoft.demo.blog.dto.AddArticleRequest;
import com.estsoft.demo.blog.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        blogRepository.deleteAll();
    }

    @Test
    void saveArticle() throws Exception {
        // given: Object -> json (ObjectMapper 사용해서 직렬화)
        AddArticleRequest request = new AddArticleRequest("제목", "내용");
        String requestBody = objectMapper.writeValueAsString(request);

        // when: POST /api/articles (API 요청)
        ResultActions resultActions = mockMvc.perform(post("/api/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        // then:
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(request.getTitle()))
                .andExpect(jsonPath("$.content").value(request.getContent()));
    }

    @Test
    public void findAllArticles() throws Exception {
        // given: article 값 저장
        Article savedArticle = Article.builder()
                .title("저장하려는 제목")
                .content("저장하려는 내용")
                .build();
        blogRepository.save(savedArticle);

        // when:
        ResultActions resultActions = mockMvc.perform(get("/api/articles"));

        // then:
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(savedArticle.getTitle()))
                .andExpect(jsonPath("$[0].content").value(savedArticle.getContent()));

        List<Article> list = blogRepository.findAll();
    }

    @Test
    public void findArticle() throws Exception {
        // given: Article 저장, id값 꺼내옴
        Article article = blogRepository.save(new Article("제목123", "내용123"));
        Long id = article.getId();

        // when: API 호출 코드
        ResultActions resultActions = mockMvc.perform(get("/api/articles/{id}", id));

        // then: 값 검증 - given절에서 setting한 값, when절 결과값 비교하여 검증
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value(article.getTitle()))
                .andExpect(jsonPath("$.content").value(article.getContent()));
    }

    @Test
    public void deleteArticle() throws Exception {
        // given: article 저장, getId
        Article article = blogRepository.save(new Article("제목11", "내용11"));
        Long id = article.getId();

        // when: DELETE api 호출
        ResultActions resultActions = mockMvc.perform(delete("/api/articles/{id}", id));

        // then: status 코드 200 ok 검증, article 전체 조회시 빈 리스트 검증
        resultActions.andExpect(status().isOk());

        List<Article> list = blogRepository.findAll();
        org.assertj.core.api.Assertions.assertThat(list).isEmpty();
        org.assertj.core.api.Assertions.assertThat(list.size()).isEqualTo(0);
    }
}
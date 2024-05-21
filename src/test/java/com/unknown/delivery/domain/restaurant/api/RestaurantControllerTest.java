package com.unknown.delivery.domain.restaurant.api;

import com.unknown.delivery.domain.restaurant.dao.RestaurantRepository;
import com.unknown.delivery.domain.restaurant.entity.Restaurant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@Transactional
class RestaurantControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    @Transactional
    void beforeEach() {
        Restaurant restaurant = Restaurant.builder()
                .name("test 매장")
                .build();

        restaurantRepository.save(restaurant);
    }

    @AfterEach
    @Transactional
    void afterEach() {
        restaurantRepository.deleteAll();
    }

    @Test
    @DisplayName("매장 목록 조회 성공")
    void t1() throws Exception {
        // when
        ResultActions resultActions = mvc
                .perform(get("/v1/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print());

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(RestaurantController.class))
                .andExpect(handler().methodName("getRestaurants"))
                .andExpect(jsonPath("$", instanceOf(List.class)))
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].id", notNullValue()))
                .andExpect(jsonPath("$[0].name", notNullValue()));
    }
}
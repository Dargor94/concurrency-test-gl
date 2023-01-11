package org.gl.franciscomasera.concurrency.controller;

import org.jsmart.zerocode.core.domain.LoadWith;
import org.jsmart.zerocode.core.domain.TargetEnv;
import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void taskRace() throws Exception {
        int poolSize = 40;
        var res = mvc.perform(

                MockMvcRequestBuilders.post("http://localhost:9001/task/race/".concat(String.valueOf(poolSize)))

        ).andReturn();

        Assertions.assertEquals(res.getResponse().getStatus(), 200);
    }
}
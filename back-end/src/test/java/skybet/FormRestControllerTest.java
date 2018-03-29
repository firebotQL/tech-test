/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package skybet;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import skybet.form.domain.Record;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FormRestControllerTest {

    @Autowired
    private MockMvc mvc;

    private Gson gsonBuilder = new GsonBuilder().create();

    private Record testRecord = new Record("random-uuid", "Martin", "Gosling");

    private Record[] defaultTestList = new Record[] {
            new Record("b93a4784-b979-1771-4cf6-3469832ee02a", "Jeff", "Stelling"),
            new Record("7d6e30b2-1b54-d2f1-2585-ce2b3e1d6419","Chris","Kamara"),
            new Record("e799d57e-e1b9-0d66-89e7-9eb1bcb5e7f4","Alex","Hammond"),
            new Record("634780cc-ff30-029c-292e-271e52db5f70","Jim","White"),
            new Record("df41df92-b3c8-5d07-0dd0-71e776eb49e8","Natalie","Sawyer")
    };

    @Before
    public void setup() throws Exception {
        mvc.perform(post("/record")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gsonBuilder.toJson(defaultTestList)));
    }

    @Test
    public void testIfReceivingOKandCorrectContentType() throws Exception {
        mvc.perform(get("/record").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void testIfNewRecordInsertWorks() throws Exception {
        defaultTestList = createBiggerList();

        final MockHttpServletRequestBuilder putBuilder = post("/record")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gsonBuilder.toJson(defaultTestList));

        mvc.perform(putBuilder)
                .andExpect(status().isOk());

        mvc.perform(get("/record").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(defaultTestList.length)))
                .andExpect(content().string(gsonBuilder.toJson(defaultTestList)));
    }

    @Test
    public void testIfRecordIsUpdated() throws Exception {
        int rowTestNumber = 2;
        String newTestingName = "Joshua";
        defaultTestList[rowTestNumber].setFirstName(newTestingName);

        final MockHttpServletRequestBuilder putBuilder = put("/record")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gsonBuilder.toJson(defaultTestList[rowTestNumber]));

        mvc.perform(putBuilder)
                .andExpect(status().isOk());

        mvc.perform(get("/record").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(defaultTestList.length)))
                .andExpect(jsonPath("$[" + rowTestNumber + "].firstName").value(equalTo(newTestingName)));
    }



    private Record[] createBiggerList() {
        Record[] newList = new Record[6];
        System.arraycopy(defaultTestList, 0, newList, 0, defaultTestList.length);
        newList[5] = testRecord;
        return newList;
    }
}

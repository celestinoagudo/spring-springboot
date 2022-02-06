package com.example.demo.student;

import static java.time.Month.JANUARY;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;


@WebMvcTest
@ExtendWith(MockitoExtension.class)
@DisplayName("StudentController Integration Tests")
class StudentControllerTest
{
	private StudentController integrationUnderTest;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService studentService;

	public static String asJsonString(final Object obj)
	{
		try
		{
			// with 3.0 (or with 2.10 as alternative)
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			ObjectMapper mapper = JsonMapper.builder() // or different mapper for other format
				.addModule(new ParameterNamesModule()).addModule(new Jdk8Module())
				.addModule(new JavaTimeModule())
				// and possibly other configuration, modules, then:
				.build();

			mapper.setDateFormat(dateFormat);
			return mapper.writeValueAsString(obj);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}

	}

	@Test
	@DisplayName("It should add new Student")
	void itShouldAddNewStudent() throws Exception
	{
		//given
		StudentRequestModel requestModel =
			new StudentRequestModel(LocalDate.of(1998, JANUARY, 1), "test1@gmail.com", "Test 1");
		requestModel.setAge(requestModel.getAge());

		//when
		mockMvc
			.perform(post("/api/v1/student").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(asJsonString(requestModel)))
			.andDo(print()).andExpect(status().isOk());

		Student student = new Student(requestModel);

		//then
		verify(studentService).addNewStudent(student);

	}

	@Test
	@DisplayName("It should delete Student by Id")
	void itShouldDeleteStudent() throws Exception
	{

		//given
		Long id = 1L;

		//When
		mockMvc.perform(delete(String.format("/api/v1/student/%s", id))).andDo(print())
			.andExpect(status().isOk());

		//then
		verify(studentService).deleteStudent(id);

	}

	@Test
	@DisplayName("It should get Students")
	void itShouldGetStudents() throws Exception
	{
		//given
		Student student = new Student(LocalDate.of(1998, JANUARY, 1), "test1@gmail.com", "Test 1");
		Student student1 = new Student(LocalDate.of(1998, JANUARY, 1), "test2@gmail.com", "Test 2");
		String expected = asJsonString(Arrays.asList(student, student1));

		//when
		when(integrationUnderTest.getStudents()).thenReturn(Arrays.asList(student, student1));

		//then
		mockMvc.perform(get("/api/v1/student")).andDo(print()).andExpect(status().isOk())
			.andExpect(content().string(equalTo(expected)));

		verify(studentService).getStudents();

	}

	@Test
	@DisplayName("It should update Student")
	void itShouldUpdateStudent() throws Exception
	{
		//given
		StudentRequestModel studentRequestModel = new StudentRequestModel(
			LocalDate.of(1998, JANUARY, 1), "test1@gmail.com", 1L, "Test 1");
		studentRequestModel.setAge(studentRequestModel.getAge());

		//when
		mockMvc
			.perform(put("/api/v1/student").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(asJsonString(studentRequestModel)))
			.andDo(print()).andExpect(status().isOk());

		Student student = new Student(studentRequestModel);

		//then
		verify(studentService).updateStudent(student);

	}

	@BeforeEach
	void setup()
	{
		integrationUnderTest = new StudentController(studentService);

	}

}

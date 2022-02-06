package com.example.demo.student;

import static java.time.Month.JANUARY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@DisplayName("StudentService Unit Tests")
@ExtendWith(MockitoExtension.class)
class StudentServiceTest
{

	@Mock
	private StudentRepository studentRepository;

	private StudentService unitUnderTest;

	@Test
	@DisplayName("It should get all Students")
	void itShouldGetAllStudents()
	{
		//when
		unitUnderTest.getStudents();

		//then
		verify(studentRepository).findAll();

	}

	@BeforeEach
	void setup()
	{
		unitUnderTest = new StudentService(studentRepository);

	}

	@Nested
	@ExtendWith(MockitoExtension.class)
	@DisplayName("StudentService.addNewStudent(Student) Unit Tests")
	class AddNewStudentTests
	{

		@Test
		@DisplayName("It should add new Student ")
		void itShouldAddNewStudent()
		{
			//given
			Student student =
				new Student(LocalDate.of(1998, JANUARY, 1), "test1@gmail.com", "Test1");

			//when
			unitUnderTest.addNewStudent(student);

			//then
			ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

			verify(studentRepository).save(studentArgumentCaptor.capture());

			Student captured = studentArgumentCaptor.getValue();

			assertThat(captured).isEqualTo(student);

		}

		@Test
		@DisplayName("It should throw IllegalArgumentException when dateOfBirth is null")
		void itShouldThrowExceptionWhenDOBIsNull()
		{
			//given
			Student student = new Student(null, "test1@gmail.com", "Test 1");

			//when
			ThrowingCallable addNewStudent = () -> unitUnderTest.addNewStudent(student);

			//then
			assertThatThrownBy(addNewStudent).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Attempting to insert an invalid payload");

		}

		@Test
		@DisplayName("It should throw IllegalArgumentException when email is null or empty ")
		void itShouldThrowExceptionWhenEmailIsNullOrEmpty()
		{
			//given
			Student student = new Student(LocalDate.of(1998, JANUARY, 1), null, "Test 1");
			Student student2 = new Student(LocalDate.of(1998, JANUARY, 1), "", "Test 1");

			//when
			ThrowingCallable addNewStudent1 = () -> unitUnderTest.addNewStudent(student);
			ThrowingCallable addNewStudent2 = () -> unitUnderTest.addNewStudent(student2);

			//then
			assertAll(
				() -> assertThatThrownBy(addNewStudent1)
					.isInstanceOf(IllegalArgumentException.class)
					.hasMessageContaining("Attempting to insert an invalid payload"),
				() -> assertThatThrownBy(addNewStudent2)
					.isInstanceOf(IllegalArgumentException.class)
					.hasMessageContaining("Attempting to insert an invalid payload"));

		}

		@Test
		@DisplayName("It should throw IllegalArgumentException when name is null or empty ")
		void itShouldThrowExceptionWhenNameIsNullOrEmpty()
		{
			//given
			Student student = new Student(LocalDate.of(1998, JANUARY, 1), "test1@gmail.com", null);
			Student student2 = new Student(LocalDate.of(1998, JANUARY, 1), "test1@gmail.com", "");

			//when
			ThrowingCallable addNewStudent1 = () -> unitUnderTest.addNewStudent(student);
			ThrowingCallable addNewStudent2 = () -> unitUnderTest.addNewStudent(student2);

			//then
			assertAll(
				() -> assertThatThrownBy(addNewStudent1)
					.isInstanceOf(IllegalArgumentException.class)
					.hasMessageContaining("Attempting to insert an invalid payload"),
				() -> assertThatThrownBy(addNewStudent2)
					.isInstanceOf(IllegalArgumentException.class)
					.hasMessageContaining("Attempting to insert an invalid payload"));

		}

		@Test
		@DisplayName("It should throw IllegalArgumentException when parameter is null")
		void itShouldThrowExceptionWhenParameterIsNull()
		{
			//given
			Student student = null;

			//when
			ThrowingCallable addNewStudent = () -> unitUnderTest.addNewStudent(student);

			//then
			assertThatThrownBy(addNewStudent).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Attempting to insert an invalid payload");

		}

		@Test
		@DisplayName("It should throw an IllegalArgumentException when the email used is already taken ")
		void itShouldThrowIllegalArgumentExceptionWhenEmailIsTaken()
		{
			//given
			Student student =
				new Student(LocalDate.of(1998, JANUARY, 1), "test1@gmail.com", "Test1");

			given(studentRepository.findStudentByEmail(student.getEmail()))
				.willReturn(Optional.of(student));

			//when
			ThrowingCallable addNewStudent = () -> unitUnderTest.addNewStudent(student);

			//then
			assertThatThrownBy(addNewStudent).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining(
					String.format("Email: %s is already taken", student.getEmail()));

			verify(studentRepository, never()).save(any());

		}

		@BeforeEach
		void setup()
		{
			unitUnderTest = new StudentService(studentRepository);

		}

	}

	@Nested
	@ExtendWith(MockitoExtension.class)
	@DisplayName("StudentService.deleteStudent(Long) Unit Tests")
	class DeleteStudent
	{
		@Test
		@DisplayName("It should delete the Student ")
		void itShouldDeleteStudent()
		{
			//given
			Student student =
				new Student(LocalDate.of(1998, JANUARY, 1), "test1@gmail.com", "Test 1");

			given(studentRepository.findById(student.getId())).willReturn(Optional.of(student));

			//when
			unitUnderTest.deleteStudent(student.getId());

			//then
			verify(studentRepository).delete(student);

		}

		@Test
		@DisplayName("It should throw IllegalArgumentException when the id is not associated to any Student ")
		void itShouldThrowExceptionWhenDeletingNonExistentStudent()
		{
			//given
			Long id = 2L;

			//when
			Executable executable = () -> unitUnderTest.deleteStudent(id);

			//then
			assertAll(() -> assertThrows(IllegalArgumentException.class, executable));

		}

		@BeforeEach
		void setup()
		{
			unitUnderTest = new StudentService(studentRepository);

		}

	}

	@Nested
	@ExtendWith(MockitoExtension.class)
	@DisplayName("StudentService.updateStudent(Student) Unit Tests")
	class UpdateStudent
	{

		@Test
		@DisplayName("It should throw IllegalArgumentException when the id passed is not associated to any Student ")
		void itShouldThrowIllegalArgumentExceptionWhenIdIsNotAssociatedToStudent()
		{
			//given
			Student student =
				new Student(LocalDate.of(1998, JANUARY, 1), "test1@gmail.com", 1L, "Test 1");

			//when
			ThrowingCallable updateStudent = () -> unitUnderTest.updateStudent(student);

			//then
			assertThatThrownBy(updateStudent).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining(
					String.format("Student with id %s does not exist", student.getId()));

		}

		@Test
		@DisplayName("It should throw IllegalArgumentException when the parameter is null ")
		void itShouldThrowIllegalArgumentExceptionWhenParamterIsNull()
		{
			//given
			Student student = null;

			//when
			Executable executable = () -> unitUnderTest.updateStudent(student);

			//then
			assertAll(() -> assertThrows(IllegalArgumentException.class, executable));

		}

		@Test
		@DisplayName("It should update the Student email ")
		void itShouldUpdateTheStudentEmail()
		{
			//given
			Student student =
				new Student(LocalDate.of(1998, JANUARY, 1), "test1@gmail.com", 1L, "Test 1");

			Student payload =
				new Student(LocalDate.of(1998, JANUARY, 1), "test1Updated@gmail.com", 1L, "Test 1");

			given(studentRepository.findById(payload.getId())).willReturn(Optional.of(student));

			//when
			unitUnderTest.updateStudent(payload);

			//then
			assertThat(student.getEmail()).isEqualTo(payload.getEmail());

		}

		@Test
		@DisplayName("It should update the Student name ")
		void itShouldUpdateTheStudentName()
		{
			//given
			Student student =
				new Student(LocalDate.of(1998, JANUARY, 1), "test1@gmail.com", 1L, "Test 1");

			Student payload = new Student(LocalDate.of(1998, JANUARY, 1), "test1@gmail.com", 1L,
				"Test 1 Updated");

			given(studentRepository.findById(payload.getId())).willReturn(Optional.of(student));

			//when
			unitUnderTest.updateStudent(payload);

			//then
			assertThat(student.getName()).isEqualTo(payload.getName());

		}

		@BeforeEach
		void setup()
		{
			unitUnderTest = new StudentService(studentRepository);

		}

	}

}

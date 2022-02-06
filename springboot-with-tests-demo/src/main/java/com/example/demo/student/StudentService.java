package com.example.demo.student;

import static com.example.demo.student.StudentServiceHelper.isNullOrEmpty;
import static com.example.demo.student.StudentServiceHelper.isSatisfied;

import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService
{
	private StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository)
	{
		this.studentRepository = studentRepository;

	}

	public void addNewStudent(final Student student)
	{
		Predicate<Student> notNullOrEmpty =
			newRecord -> (newRecord != null) && !isNullOrEmpty(newRecord.getName())
				&& !isNullOrEmpty(newRecord.getEmail()) && (newRecord.getDateOfBirth() != null);

		if (!isSatisfied(notNullOrEmpty, student))
		{
			throw new IllegalArgumentException("Attempting to insert an invalid payload");
		}

		Runnable saveRecord = () -> studentRepository.save(student);
		Consumer<Student> throwExceptionForAlreadyTakenEmail = existingStudent -> {
			throw new IllegalArgumentException(
				String.format("Email: %s is already taken", existingStudent.getEmail()));
		};

		studentRepository.findStudentByEmail(student.getEmail())
			.ifPresentOrElse(throwExceptionForAlreadyTakenEmail, saveRecord);

	}

	public void deleteStudent(final Long id)
	{
		Runnable throwExceptionForDeletingNonExistentStudent = () -> {
			throw new IllegalArgumentException(
				String.format("Student with id %s is non-existent.", id));
		};

		Consumer<Student> deleteStudent =
			existingStudent -> studentRepository.delete(existingStudent);

		studentRepository.findById(id).ifPresentOrElse(deleteStudent,
			throwExceptionForDeletingNonExistentStudent);

	}

	public List<Student> getStudents()
	{
		return studentRepository.findAll();

	}

	@Transactional
	public void updateStudent(final Student payload)
	{
		if (!isSatisfied(Objects::nonNull, payload))
		{
			throw new IllegalArgumentException("The passed payload is not valid");
		}

		final Student existingStudent = studentRepository.findById(payload.getId())
			.orElseThrow(() -> new IllegalArgumentException(
				String.format("Student with id %s does not exist", payload.getId())));

		BiPredicate<Student, Student> notSameByName =
			(first, second) -> !Objects.equals(first.getName(), second.getName());

		if (isSatisfied(notSameByName, existingStudent, payload))
		{
			existingStudent.setName(payload.getName());
		}

		BiPredicate<Student, Student> notSameByEmail =
			(first, second) -> !Objects.equals(first.getEmail(), second.getEmail());

		if (isSatisfied(notSameByEmail, existingStudent, payload))
		{
			existingStudent.setEmail(payload.getEmail());
		}

	}

}

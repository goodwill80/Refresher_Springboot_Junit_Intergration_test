package com.ltp.gradesubmission;

import com.ltp.gradesubmission.pojo.Grade;
import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.service.GradeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

// Step 1 - Import junit pom from maven repo
// Step 2 - Set up ServiceTest class in test folder
// Step 3 - Set up a mock and injectMock
// Step 4 - test the business logic in service class

@RunWith(MockitoJUnitRunner.class) // Run all unit tests inside this class
public class GradeServiceTest {

    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks // this creates a gradeService Object and auto-wire the gradeRepository into it
    private GradeService gradeService;

    // 3 steps to write a unit test
    // a. Arrange: Mock the data needed to carry out the test
    // b. Act: Call the method that you want to test
    // c. Assert: Check if it is behaving correctly

    @Test
    public void getGradesFromRepoTest() {
        // 1. Arrange
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(
                new Grade("Harry", "Potion", "C-"),
                new Grade("Hermione", "Arithmetician", "A+")
        ));
        // 2. Act
        List<Grade> result = gradeService.getGrades();
        // 3. Assert
        assertEquals("Harry", result.get(0).getName());
        assertEquals("Arithmetician", result.get(1).getSubject());
    }

    @Test
    public void gradeIndexTest() {
        Grade grade = new Grade("Harry", "Potion", "C-");
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);


        int valid = gradeService.getGradeIndex(grade.getId());
        int notFound = gradeService.getGradeIndex("123");

        assertEquals(0, valid);
        assertEquals(Constants.NOT_FOUND, notFound);
    }

    @Test
    public void returnGradeByIdTest() {
        Grade grade = new Grade("Harry", "Potion", "C-");
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        String id = grade.getId();
        Grade result = gradeService.getGradeById(id);

        assertEquals(grade, result);

    }


}

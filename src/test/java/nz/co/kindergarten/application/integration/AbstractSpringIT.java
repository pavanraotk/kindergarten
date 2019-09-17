package nz.co.kindergarten.application.integration;

import nz.co.kindergarten.application.database.TeacherRepository;
import nz.co.kindergarten.application.integration.configuration.IntegrationTestConfiguration;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
@Import(IntegrationTestConfiguration.class)
public abstract class AbstractSpringIT {

    @Autowired
    protected TeacherRepository teacherRepository;


    @After
    public void tearDown() {
        teacherRepository.deleteAll();
    }
}

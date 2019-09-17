package nz.co.kindergarten.application.database.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

import static javax.persistence.GenerationType.SEQUENCE;

@Table(name = "teacher", schema = "kindergarten")
@Entity
@Data
@Accessors(fluent = true)
public class Teacher {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "kindergarten.teacher_id_seq")
    private Long id;

    private String firstName;
    private String lastName;
    private String emailId;
    private Long mobileNumber;
    private String address;
    private String qualifications;
    private Boolean active;

    @CreationTimestamp
    private Instant created;

    @UpdateTimestamp
    private Instant modified;
}

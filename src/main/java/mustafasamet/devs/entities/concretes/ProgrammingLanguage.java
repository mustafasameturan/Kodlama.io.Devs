package mustafasamet.devs.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name="programming_languages")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProgrammingLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "programmingLanguage", cascade = CascadeType.DETACH)
    private List<ProgrammingTechnology> programmingTechnology;
}

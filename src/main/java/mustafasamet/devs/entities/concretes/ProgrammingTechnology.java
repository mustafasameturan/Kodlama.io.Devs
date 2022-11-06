package mustafasamet.devs.entities.concretes;

import lombok.*;

import javax.persistence.*;

@Table(name = "programming_technologies")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProgrammingTechnology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "programming_language_id")
    private ProgrammingLanguage programmingLanguage;

    @Column(name = "name")
    private String name;
}

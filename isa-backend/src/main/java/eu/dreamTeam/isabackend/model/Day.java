package eu.dreamTeam.isabackend.model;

import eu.dreamTeam.isabackend.model.enums.DayName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DayName day;
    @ManyToMany(mappedBy = "days")
    private Set<WorkTime> workTimes;
}

package com.assesment.avaloq.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roll_configuration")
@Getter
@Setter
public class RollConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private Integer diceNumber;

    @Column
    @NotNull
    private Integer diceSide;

    @OneToMany(mappedBy = "configuration")
    private List<Simulation> simulations = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof RollConfiguration))
            return false;

        RollConfiguration other = (RollConfiguration) o;

        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}

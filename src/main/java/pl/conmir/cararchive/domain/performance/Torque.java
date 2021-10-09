package pl.conmir.cararchive.domain.performance;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Torque {

    private int value;

    private Torque(int value){
        if (!isCorrect(value))
            throw new IllegalArgumentException("Power cannot be less than 0");

        this.value = value;
    }

    public static Torque of(int value){
        return new Torque(value);
    }

    private boolean isCorrect(int value){
        return value > 0;
    }

}

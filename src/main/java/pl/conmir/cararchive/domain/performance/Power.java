package pl.conmir.cararchive.domain.performance;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Power {

    private int value;

    private Power(int value){
        if (!isCorrect(value))
            throw new IllegalArgumentException("Power cannot be less than 0");

        this.value = value;
    }

    public static Power of(int value){
        return new Power(value);
    }

    private boolean isCorrect(int value){
        System.out.println(value);
        return value > 0;
    }
}

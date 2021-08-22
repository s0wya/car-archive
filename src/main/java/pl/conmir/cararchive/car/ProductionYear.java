package pl.conmir.cararchive.car;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductionYear {

    private int value;

    private ProductionYear(int value){
        if (!isCorrect(value))
            throw new IllegalArgumentException("Make number cannot be blank!");

        this.value = value;
    }

    public static ProductionYear of(int value){
        return new ProductionYear(value);

    }

    private boolean isCorrect(int value){
        return String.valueOf(value).length() == 4;
    }

}

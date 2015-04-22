package models.card.top;

/**
 * Created by Brandt Newton on 3/17/2015.
 */
public class MooseInBedroom extends TopCard {
    public MooseInBedroom() {
        super(CardClass.BEDROOM, "mooseinbedroom.png", 1);
    }

    @Override
    public boolean isDefensive() {
        return false;
    }
}

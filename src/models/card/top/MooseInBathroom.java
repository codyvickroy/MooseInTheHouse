package models.card.top;

/**
 * Created by Brandt Newton on 3/17/2015.
 */
public class MooseInBathroom extends TopCard {
    public MooseInBathroom() {
        super(CardClass.BATHROOM, "moose_in_bathroom.png", 1);
    }

    @Override
    public boolean isDefensive() {
        return false;
    }
}

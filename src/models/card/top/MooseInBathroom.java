package models.card.top;

/**
 * Created by Brandt Newton on 3/17/2015.
 */
public class MooseInBathroom extends TopCard {
    public MooseInBathroom() {
        super(CardClass.BATHROOM, "asdf");
    }

    @Override
    public boolean isDefensive() {
        return false;
    }
}

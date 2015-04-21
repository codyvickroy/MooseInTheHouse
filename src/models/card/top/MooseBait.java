package models.card.top;

/**
 * Created by Brandt Newton on 3/17/2015.
 */
public class MooseBait extends TopCard {
    public MooseBait() {
        super(CardClass.MOOSE, "moosetrap.png", 6);
    }

    @Override
    public boolean isDefensive() {
        return true;
    }
}

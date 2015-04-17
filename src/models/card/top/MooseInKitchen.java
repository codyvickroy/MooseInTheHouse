package models.card.top;

/**
 * Created by Brandt Newton on 3/17/2015.
 */
public class MooseInKitchen extends TopCard {
    public MooseInKitchen() {
        super(CardClass.KITCHEN, "asdf", 1);
    }

    @Override
    public boolean isDefensive() {
        return false;
    }
}

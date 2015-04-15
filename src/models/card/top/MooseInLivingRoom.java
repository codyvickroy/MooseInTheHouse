package models.card.top;

/**
 * Created by Brandt Newton on 3/17/2015.
 */
public class MooseInLivingRoom extends TopCard {
    public MooseInLivingRoom() {
        super(CardClass.LIVINGROOM, "asdf");
    }


    @Override
    public boolean isDefensive() {
        return true;
    }
}

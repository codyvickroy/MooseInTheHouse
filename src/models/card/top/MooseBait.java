package models.card.top;

/**
 * Created by Brandt Newton on 3/17/2015.
 */
public class MooseBait extends TopCard {
    public MooseBait() {
        super(null, "moose_bait.png", 6);
    }

    @Override
    public boolean isDefensive() {
        return true;
    }
    
    @Override
    public boolean isBait() {
        return true;
    }
}

package th.skyousuke.braveland.object.npc.partner;

import com.badlogic.gdx.math.MathUtils;
import th.skyousuke.braveland.object.npc.partner.partner1.Partner1;
import th.skyousuke.braveland.object.npc.partner.partner2.Partner2;
import th.skyousuke.braveland.object.npc.partner.partner3.Partner3;
import th.skyousuke.braveland.object.npc.partner.partner4.Partner4;
import th.skyousuke.braveland.object.npc.partner.partner5.Partner5;

public class PartnerUtils {

    private PartnerUtils() {
    }

    public static Partner makeRandomPartner() {
        int partnerNumber = MathUtils.random(1, 5);

        switch (partnerNumber) {
            case 1:
                return new Partner1();
            case 2:
                return new Partner2();
            case 3:
                return new Partner3();
            case 4:
                return new Partner4();
            case 5:
                return new Partner5();
        }
        throw new RuntimeException("Invalid partner number!");
    }


}

package th.skyousuke.braveland;

import th.skyousuke.braveland.object.AbstractCharacter;

public interface HotBoxListener {

    void onHit(HotBox hotBox, AbstractCharacter character);
}

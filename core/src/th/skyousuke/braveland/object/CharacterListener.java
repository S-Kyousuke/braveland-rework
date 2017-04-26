package th.skyousuke.braveland.object;

public interface CharacterListener {

    void onCharacterTakeDamage(AbstractCharacter character, float damage);

    void onCharacterCreateObject(AbstractCharacter character, AbstractLevelObject levelObject, float gravityModifier);

    float findAbsoluteDistanceToLeftWall(AbstractCharacter character);

    float findAbsoluteDistanceToRightWall(AbstractCharacter character);

    void onCharacterDead(AbstractCharacter character);

}

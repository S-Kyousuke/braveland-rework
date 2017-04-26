package th.skyousuke.braveland;

import com.badlogic.gdx.audio.Sound;
import th.skyousuke.braveland.utils.Assets;


public class Damage {

    public float value;
    public float knockBackSpeed;
    public ViewDirection knockBackDirection;
    public Sound hitSound;

    public Damage() {
        this(0);
    }

    public Damage(float value) {
        this(value, 0, ViewDirection.RIGHT);
    }

    public Damage(float value, float knockBackSpeed, ViewDirection knockBackDirection) {
        this(value, knockBackSpeed, knockBackDirection, Assets.instance.hit);
    }

    public Damage(float value, float knockBackSpeed, ViewDirection knockBackDirection, Sound hitSound) {
        this.value = value;
        this.knockBackSpeed = knockBackSpeed;
        this.knockBackDirection = knockBackDirection;
        this.hitSound = hitSound;
    }
}

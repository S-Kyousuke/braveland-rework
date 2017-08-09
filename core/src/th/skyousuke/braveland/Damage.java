package th.skyousuke.braveland;

import com.badlogic.gdx.audio.Sound;
import th.skyousuke.braveland.utils.Assets;


public class Damage {

    private float value;
    private float knockBackSpeed;
    private ViewDirection knockBackDirection;
    private Sound hitSound;

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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getKnockBackSpeed() {
        return knockBackSpeed;
    }

    public void setKnockBackSpeed(float knockBackSpeed) {
        this.knockBackSpeed = knockBackSpeed;
    }

    public ViewDirection getKnockBackDirection() {
        return knockBackDirection;
    }

    public void setKnockBackDirection(ViewDirection knockBackDirection) {
        this.knockBackDirection = knockBackDirection;
    }

    public Sound getHitSound() {
        return hitSound;
    }

    public void setHitSound(Sound hitSound) {
        this.hitSound = hitSound;
    }
}

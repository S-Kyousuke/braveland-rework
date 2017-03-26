package th.skyousuke.braveland;

public abstract class AiCharacter extends Character {

    private Character target;

    public AiCharacter(float width, float height) {
        super(width, height);
    }

    public boolean isTargetInAttackRange() {
        final int attackRange = 70;
        float targetCenterX = target.getBound().x + target.getBound().width / 2;
        float centerX = bound.x + bound.width / 2;
        return Math.abs(centerX - targetCenterX) <= attackRange;
    }

    public void setTarget(Character target) {
        this.target = target;
    }

    public void followTarget() {
        final float distanceThreshold = 70;
        if (isOnGround()) {
            float targetCenterX = target.getBound().x + target.getBound().width / 2;
            float centerX = bound.x + bound.width / 2;

            if (targetCenterX - centerX> distanceThreshold) {
                move(ViewDirection.RIGHT);
            } else if (targetCenterX - centerX < -distanceThreshold) {
                move(ViewDirection.LEFT);
            }
        }
    }

    public void lookAtTarget() {
        final float distanceThreshold = 30;
        float targetCenterX = target.getBound().x + target.getBound().width / 2;
        float centerX = bound.x + bound.width / 2;

        if (targetCenterX - centerX > distanceThreshold) {
            setViewDirection(ViewDirection.RIGHT);
        } else if (targetCenterX - centerX < -distanceThreshold) {
            setViewDirection(ViewDirection.LEFT);
        }
    }

    public Character getTarget() {
        return target;
    }
}

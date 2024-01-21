package be.pxl.h1.oefeningen.Oef5;

public class Spaceship {
    private String name;
    private int hits;
    private boolean shieldOn;

    public String getName() {
        return name;
    }

    public int getHits() {
        return hits;
    }

    public boolean isShieldOn() {
        return shieldOn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public void setShieldOn(boolean shieldOn) {
        this.shieldOn = shieldOn;
        if (shieldOn){
            System.out.println(name + " puts shield on");
        }else{
            System.out.println(name + " puts shield off");
        }
    }

    public void fire(Spaceship SpaceShip){
        System.out.println(name + " fires at " + SpaceShip.name);
        if (!SpaceShip.shieldOn){
            SpaceShip.hits++;
            if(SpaceShip.isDestroyed()){
                System.out.println(name + " is destroyed? " + isDestroyed());
                System.out.println(SpaceShip.name + " is destroyed? " + SpaceShip.isDestroyed());
            }
        }else{
            System.out.println(name + " fires at " + SpaceShip.name);
        }
    }

    public boolean isDestroyed(){
        if (hits == 5){
            return true;
        }else{
            return false;
        }
    }

}

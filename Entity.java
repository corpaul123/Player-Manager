public class Entity {
    private int init;
    private String name;
    private Integer hitP;

    public Entity(String name, int initiative){
        this.name = name;
        this.init = initiative;
        this.hitP = null;
    }

    public Entity(String name, int initiative, int hitPoints){
        this.name = name;
        this.init = initiative;
        this.hitP = hitPoints;
    }

    public int getInit(){
        return init;
    }

    public String getName(){
        return name;
    }

    public int getHP(){
        return hitP;
    }

    public String toString(){

        if(hitP != null){
            return name + " | init: " + init + " | HP: " + hitP;
        }
        else{
            return name + " | init: " + init;
        }
    }
}

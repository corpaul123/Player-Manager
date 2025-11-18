public class Entity {
    private int init;
    private String name;
    private Integer hitP;
    private Integer maxHP;
    private boolean isEnemy;

    public Entity(String name, int initiative){
        this.name = name;
        this.init = initiative;
        this.hitP = null;
    }

    public Entity(String name, int initiative, int hitPoints, boolean isEnemy){
        this.name = name;
        this.init = initiative;
        this.hitP = hitPoints;
        this.maxHP = hitPoints;
        this.isEnemy = isEnemy;
    }

    public int getInit(){
        return init;
    }

    public void setHP(int hitPoints){
        this.hitP = hitPoints;
    }

    public boolean isEnemy(){
        return isEnemy;
    }
    
    public void takeDamage(int damage){
        if(hitP!=null){
            this.hitP -= damage;
            if(this.hitP <= 0){
                this.hitP = 0;
            }
        }
    }

    public void moreHealth(int health){
        if(hitP != null){
            hitP = Math.min(hitP + health, maxHP);
        }
        
    }

    public String getName(){
        return name;
    }

    public int getHP(){
        return hitP;
    }

    public String toString(){

        if(hitP != null){
            return String.format("%-12s  | init: %3d | HP: %3d ", name, init, hitP);
            //return name + " | init: " + init + " | HP: " + hitP;
        }
        else{
            //return name + " | init: " + init;
            return String.format("%-12s  | init: %3d ", name, init);
        }
    }
}

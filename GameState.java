public class GameState {
    
    private int MAX_INIT = 40;
    private int ENEMY_COUNT = 0;

    public int getMaxInit(){
        return MAX_INIT;
    }

    public int getEn(){

        return ENEMY_COUNT;
    }

    public void incrementEn(){
        ++ENEMY_COUNT;
    }

    public void decrementEn(){
        ENEMY_COUNT--;
    }

}

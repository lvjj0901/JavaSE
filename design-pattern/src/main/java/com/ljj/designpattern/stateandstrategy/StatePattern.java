package stateandstrategy;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-09 1:46 p.m.
 * @Version 1.0
 */
public class StatePattern {
    public static void main(String[] args) {
        LightContext context = new LightContext(new OffState());
        System.out.println("press switch:");
        context.pressSwitch();
        System.out.println("press switch:");
        context.pressSwitch();
        System.out.println("press switch:");
        context.pressSwitch();
    }
}

interface State{
    void handle(LightContext sc);
}

class Onstate implements State{
    @Override
    public void handle(LightContext sc) {
        System.out.println("Light is on!");
        sc.setState(new OffState());
    }
}

class OffState implements State{
    @Override
    public void handle(LightContext sc) {
        System.out.println("Light is off!");
        sc.setState(new Onstate());
    }
}

class LightContext {
    private State state;
    public LightContext(State state){
        this.state = state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void pressSwitch(){
        state.handle(this);
    }
}

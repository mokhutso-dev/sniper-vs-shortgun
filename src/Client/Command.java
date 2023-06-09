package Client;
public abstract class Command {
    private final String name;
    private String argument; 

    public abstract boolean execute(Robot target);

    public Command(String name){
        this.name = name.trim().toLowerCase();
        this.argument = "";
    }

    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }

    public String getName() {                                                                           //<2>
        return name;
    }

    public String getArgument() {
        return this.argument;
    }


    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");

        switch (args[0]){
            case "quit":            
                return new Quit();
            case "forward":
                return new Forward(args[1]);
            case "back":
                return new Back(args[1]);
            case "right":
                return new Right();
            case "left":
                return new Left();    
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        
            }
        
    }
    
}
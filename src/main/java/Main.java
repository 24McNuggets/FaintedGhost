import me.fainted.Tutorial;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "faintedclient", version = "b1")
public class Main {

	// TODO: Learn Java xD
	
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	Tutorial.instance = new Tutorial();
    	Tutorial.instance.init();
    }
}

package toast.specialMobs.particle;

import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.world.World;

/**
 * Created by jtidwell on 3/28/2016.
 */
public class EntityPurpleFireFX extends EntityFlameFX {

    public EntityPurpleFireFX(World parWorld,
                              double parX, double parY, double parZ,
                              double parMotionX, double parMotionY, double parMotionZ) {

        super(parWorld, parX, parY, parZ, parMotionX, parMotionY, parMotionZ);
        setParticleTextureIndex(48);
        particleScale = 2.0F;
        setRBGColorF(0xC6, 0x46, 0xC6);
    }

}
